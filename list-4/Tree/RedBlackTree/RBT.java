package Tree.RedBlackTree;

import Tree.Tree;

public class RBT<T> extends Tree<T> {
    RBNode<T> nil;
    RBNode<T> root;

    public RBT(){
        nil = new RBNode<T>(null);
        nil.setrChild(nil);
        nil.setlChild(nil);
        nil.setParent(nil);
        root = nil;
    }

    @Override
    public void insert(T key) {
        RBNode<T> z = new RBNode<T>(key, nil, nil, nil);
        RBNode<T> x = root;
        RBNode<T> y = nil;

        while(x != nil){
            y = x;
            comp++;
            if(z.compareTo(x) < 0){
                x = x.getlChild();
            }else{
                x = x.getrChild();
            }
        }

        z.setParent(y);

        if(y == nil){
            comp++;
            root = z;
        }else if(z.compareTo(y) < 0){
            comp=comp+2;
            y.setlChild(z);
        }else if(z.compareTo(y) > 0){
            comp=comp+3;
            y.setrChild(z);
        }else{
            comp=comp+3;
            return;
        }
        z.setlChild(nil);
        z.setrChild(nil);
        z.setColor(true);
        insertFix(z);
        size++;
    }

    @Override
    public void delete(T key) {
        RBNode<T> z = getNode(key);
        comp++;
        if(z == nil) return;

        RBNode<T> x;
        RBNode<T> y = z;
        boolean yOriginalColor = y.getColor();

        if(z.getlChild() == nil){
            comp++;
            x = z.getrChild();
            transplant(z, z.getrChild());
        }else if(z.getrChild() == nil){
            comp=comp+2;
            x = z.getlChild();
            transplant(z, z.getlChild());
        }else{
            comp=comp+2;
            y = getSubTreeMin(z.getrChild());
            yOriginalColor = y.getColor();
            x = y.getrChild();

            comp++;
            if(y.getParent() == z)
                x.setParent(y);
            else{
                transplant(y, y.getrChild());
                y.setrChild(z.getrChild());
                y.getrChild().setParent(y);
            }

            transplant(z, y);
            y.setlChild(z.getlChild());
            y.getlChild().setParent(y);
            y.setColor(z.getColor());
        }

        // If color is black
        if(!yOriginalColor) {
            deleteFix(x);
        }
        swap++;
        comp++;
        size--;
    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    @Override
    public boolean search(T key){
        RBNode<T> traverse = root;
        while(traverse != nil){
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

    private void insertFix(RBNode<T> z) {
        while(z.getParent().getColor()){
            comp++;
            if(z.getParent() == z.getParent().getParent().getlChild()){
                RBNode<T> y = z.getParent().getParent().getrChild();
                comp++;
                if(y.getColor()){
                    z.getParent().setColor(false);
                    y.setColor(false);
                    z.getParent().getParent().setColor(true);
                    z = z.getParent().getParent();
                }else{
                    comp++;
                    if(z == z.getParent().getrChild()){
                        z = z.getParent();
                        rotLeft(z);
                    }
                    z.getParent().setColor(false);
                    z.getParent().getParent().setColor(true);
                    rotRight(z.getParent().getParent());
                }
            }else{
                RBNode<T> y = z.getParent().getParent().getlChild();
                comp++;
                if(y.getColor()){
                    z.getParent().setColor(false);
                    y.setColor(false);
                    z.getParent().getParent().setColor(true);
                    z = z.getParent().getParent();
                }else{
                    comp++;
                    if(z == z.getParent().getlChild()){
                        z = z.getParent();
                        rotRight(z);
                    }

                    z.getParent().setColor(false);
                    z.getParent().getParent().setColor(true);
                    rotLeft(z.getParent().getParent());
                }

            }
        }
        root.setColor(false);
    }

    private void deleteFix(RBNode<T> node){
        // If not root and is black
        while(node != root && !node.getColor()){
            comp++;
            if(node == node.getParent().getlChild()){
                RBNode<T> w = node.getParent().getrChild();

                comp++;
                if(w.getColor()){
                    w.changeColor();

                    RBNode<T> temp = node.getParent();
                    temp.setColor(true);

                    rotLeft(node.getParent());
                    w = node.getParent().getrChild();
                }

                RBNode<T> wLeft = w.getlChild();
                RBNode<T> wRight = w.getrChild();


                // If left and right child is black
                if(!wLeft.getColor() && !wRight.getColor()){
                    comp++;
                    w.setColor(true);
                    node = node.getParent();
                    continue;
                }else if(!wRight.getColor()){
                    comp=comp+2;
                    wLeft.setColor(false);
                    w.setColor(true);
                    rotRight(w);
                    w = node.getParent().getrChild();
                }

                // If wRight color is red
                comp++;
                if(wRight.getColor()){
                    w.setColor(node.getParent().getColor());
                    node.getParent().setColor(false);
                    wRight.setColor(false);
                    rotLeft(node.getParent());
                    node = root;
                }
            }else{
                RBNode<T> w = node.getParent().getlChild();

                comp++;
                if(w.getColor()){
                    w.changeColor();

                    RBNode<T> temp = node.getParent();
                    temp.setColor(true);

                    rotRight(node.getParent());
                    w = node.getParent().getlChild();
                }

                RBNode<T> wLeft = w.getlChild();
                RBNode<T> wRight = w.getrChild();

                // If left and right child is black
                if(!wLeft.getColor() && !wRight.getColor()){
                    comp++;
                    w.setColor(true);
                    node = node.getParent();
                    continue;
                }else if(!wLeft.getColor()){
                    comp=comp+2;
                    wRight.setColor(false);
                    w.setColor(true);
                    rotLeft(w);
                    w = node.getParent().getlChild();
                }

                comp++;
                // If wRight color is red
                if(wLeft.getColor()){
                    w.setColor(node.getParent().getColor());
                    node.getParent().setColor(false);
                    wLeft.setColor(false);
                    rotRight(node.getParent());
                    node = root;
                }
            }
        }
        node.setColor(false);
    }

    private void transplant(RBNode<T> from, RBNode<T> to){
        if(from.getParent() == nil){
            comp++;
            root = to;
        }else if(from == from.getParent().getlChild()){
            comp=comp+2;
            from.getParent().setlChild(to);
        }else{
            comp=comp+2;
            from.getParent().setrChild(to);
        }

        to.setParent(from.getParent());
        swap++;
    }

    private RBNode<T> getNode(T key){
        RBNode<T> reference = new RBNode<T>(key);
        RBNode<T> traverse =  root;
        while(traverse != nil){
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

    private void rotLeft(RBNode<T> node){
        RBNode<T> right = node.getrChild(); // right = y
        node.setrChild(right.getlChild());

        comp++;
        if(right.getlChild() != nil){
            right.getlChild().setParent(node);
        }

        right.setParent(node.getParent());

        if(node.getParent() == nil){
            comp++;
            root = right;
        }else if(node == node.getParent().getlChild()){
            comp=comp+2;
            node.getParent().setlChild(right);
        }else{
            comp=comp+2;
            node.getParent().setrChild(right);
        }

        right.setlChild(node);
        node.setParent(right);
        swap++;
    }

    private void rotRight(RBNode<T> node){
        RBNode<T> left = node.getlChild();
        node.setlChild(left.getrChild());

        comp++;
        if(left.getrChild() != nil){
            left.getlChild().setParent(node);
        }

        left.setParent(node.getParent());
        if(node.getParent() == nil) {
            comp++;
            root = left;
        }else if(node == node.getParent().getrChild()){
            comp=comp+2;
            node.getParent().setrChild(left);
        }else{
            comp=comp+2;
            node.getParent().setlChild(left);
        }

        left.setrChild(node);
        node.setParent(left);
        swap++;
    }

    private RBNode<T> getSubTreeMin(RBNode<T> root){
        while(root.getlChild() != nil){
            comp++;
            root = root.getlChild();
        }
        return root;
    }

    private void inOrder(RBNode<T> node){
        comp++;
        if(node.getlChild() != nil){
            inOrder(node.getlChild());
        }

        System.out.print(node.getKey()+" ");

        comp++;
        if(node.getrChild() != nil){
            inOrder(node.getrChild());
        }
    }
}
