package Tree.SplayTree;

import Tree.Tree;

public class SplayBST<T> extends Tree<T> {
    SNode<T> root;

    @Override
    public void insert(T key) {
        SNode<T> node = new SNode<T>(key);
        SNode<T> z = root;
        SNode<T> p = null;

        while(z != null) {
            p = z;
            if(node.compareTo(p) > 0) {
                comp++;
                z = z.getrChild();
            }else if(node.compareTo(p) < 0){
                comp=comp+2;
                z = z.getlChild();
            }else{
                comp=comp+2;
                return;
            }
        }

        node.setParent(p);

        if(p == null) {
            comp++;
            root = node;
        }else if(node.compareTo(p) > 0) {
            comp=comp+2;
            p.setrChild(node);
        }else{
            comp=comp+2;
            p.setlChild(node);
        }

        Splay(node);
        size++;
    }

    @Override
    public void delete(T key) {
        SNode<T> node = getNode(key);
        comp++;
        if(node == null) return;

        Splay(node);

        if((node.getlChild() != null) && (node.getrChild() !=null)) {
            comp=comp+2;
            SNode<T> min = node.getlChild();

            while(min.getrChild() != null) {
                min = min.getrChild();
            }

            min.setrChild(node.getrChild());
            node.getrChild().setParent(min);
            node.getlChild().setParent(null);
            root = node.getlChild();
        }else if (node.getrChild() != null) {
            comp=comp+3;
            node.getrChild().setParent(null);
            root = node.getrChild();
        }else if( node.getlChild() !=null) {
            comp=comp+4;
            node.getlChild().setParent(null);
            root = node.getlChild();
        }else{
            comp=comp+4;
            root = null;
        }

        node.setParent(null);
        node.setlChild(null);
        node.setrChild(null);
        swap++;
        size--;
    }

    @Override
    public boolean search(T key){
        SNode<T> traverse = root;
        while(traverse != null){
            if(((Comparable) traverse.getKey()).compareTo(key) > 0){
                comp++;
                traverse = traverse.getlChild();
            }else if(((Comparable) traverse.getKey()).compareTo(key) < 0){
                comp=comp+2;
                traverse = traverse.getrChild();
            }else{
                comp=comp+2;
                return true;
            }
        }
        return false;
    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    private void Splay(SNode<T> node){
        while(node.getParent() != null) {
            SNode<T> p = node.getParent();
            SNode<T> gp = p.getParent();

            comp++;
            if(gp == null) {
                comp++;
                if(node == p.getlChild()) {
                    rotRight(p);
                }else{
                    rotLeft(p);
                }
            }else{
                comp++;
                if(node == p.getlChild()) {
                    comp++;
                    if(p == gp.getlChild()) {
                        rotRight(gp);
                        rotRight(p);
                    }else{
                        rotRight(node.getParent());
                        rotLeft(node.getParent());
                    }
                }else{
                    comp++;
                    if(p == gp.getlChild()) {
                        rotLeft(node.getParent());
                        rotRight(node.getParent());
                    }else{
                        rotLeft(gp);
                        rotLeft(p);
                    }
                }
            }
        }
        root = node;
    }

    private void rotLeft(SNode<T> node){
        SNode<T> right = node.getrChild();

        comp++;
        if(node.getParent() != null) {
            comp++;
            if(node == node.getParent().getlChild()) {
                node.getParent().setlChild(right);
            }else{
                node.getParent().setrChild(right);
            }
        }

        comp++;
        if(right.getlChild() != null) {
            right.getlChild().setParent(node);
        }

        right.setParent(node.getParent());
        node.setParent(right);
        node.setrChild(right.getlChild());
        right.setlChild(node);
        swap++;
    }

    private void rotRight(SNode<T> node){
        SNode<T> left = node.getlChild();

        comp++;
        if(node.getParent() != null) {
            comp++;
            if(node == node.getParent().getlChild()) {
                node.getParent().setlChild(left);
            }else{
                node.getParent().setrChild(left);
            }
        }

        comp++;
        if (left.getrChild() != null) {
            left.getrChild().setParent(node);
        }

        left.setParent(node.getParent());
        node.setParent(left);
        node.setlChild(left.getrChild());
        left.setrChild(node);
        swap++;
    }
    
    private void inOrder(SNode<T> node){
        comp++;
        if(node.getlChild() != null){
            inOrder(node.getlChild());
        }

        System.out.print(node.getKey()+" ");

        comp++;
        if(node.getrChild() != null){
            inOrder(node.getrChild());
        }
    }

    private SNode<T> getNode(T key){
        SNode<T> reference = new SNode<T>(key);
        SNode<T> traverse =  root;
        while(traverse != null){
            if(traverse.compareTo(reference) > 0){
                comp++;
                traverse = traverse.getlChild();
            }else if(traverse.compareTo(reference) < 0){
                comp=comp+2;
                traverse = traverse.getrChild();
            }else{
                comp=comp+2;
                return traverse;
            }
        }
        return null;
    }
}
