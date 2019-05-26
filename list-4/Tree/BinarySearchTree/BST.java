package Tree.BinarySearchTree;

import Tree.Tree;
import Tree.Node;

public class BST<T> extends Tree<T>{
    BSTNode<T> root = null;

    // Constructor along with initialization of root
    public BST(T key){
        this.root = new BSTNode<T>(key);
    }

    // Constructor without initialization of root
    public BST(){
        this.root = null;
    }

    @Override
    public void insert(T key) {
        BSTNode<T> node = new BSTNode<T>(key);

        comp++;
        if(root == null){
            root = node;
            size++;
            return;
        }

        BSTNode<T> traverse = root;
        while(true){
            if(node.compareTo(traverse) < 0){
                comp=comp+2;
                if(traverse.getlChild() == null){
                    traverse.setlChild(node);
                    node.setParent(traverse);
                    size++;
                    return;
                }
                traverse = traverse.getlChild();
            }else if(node.compareTo(traverse) > 0){
                comp=comp+3;
                if(traverse.getrChild() == null){
                    traverse.setrChild(node);
                    node.setParent(traverse);
                    size++;
                    return;
                }
                traverse = traverse.getrChild();
            }else if(node.compareTo(traverse) == 0){
                comp=comp+3;
                return;
            }
        }
    }

    @Override
    public void delete(T key) {
        comp++;
        if(root == null) return;

        BSTNode<T> node = getNode(key);
        comp++;
        if(node == null) return;

        // For leafs
        comp++;
        if(node.getrChild() == null && node.getlChild() == null){
            // Just delete
            comp=comp+2;
            if(node.getParent().getlChild() == node){
                node.getParent().setlChild(null);
            }else if(node.getParent().getrChild() == node) {
                comp++;
                node.getParent().setrChild(null);
            }
            size--;
            return;
        }

        comp++;
        // For both children
        if(node.getrChild() != null && node.getlChild() != null){
            comp++;
            // Get next key node
            Node<T> rMin = getNextNode(node);
            T delKey = rMin.getKey();

            // Delete next key node
            delete(delKey);

            // Set node's key to deleted one
            node.setKey(delKey);
            swap++;
        }else{
            comp++;
            if(node.getParent() == null){
                comp++;
                if(node.getlChild() != null){
                    node.getlChild().setParent(null);
                    root = node.getlChild();
                }else{
                    node.getrChild().setParent(null);
                    root = node.getrChild();
                }
            }else if(node.getParent().getlChild() == node){ // If we are left child
                comp=comp+2;
                if(node.getlChild() != null){
                    node.getParent().setlChild(node.getlChild());
                    node.getlChild().setParent(node.getParent());
                }else{
                    node.getParent().setlChild(node.getrChild());
                    node.getrChild().setParent(node.getParent());
                }
            }else if(node.getParent().getrChild() == node) { // If we are right child
                comp=comp+3;
                if(node.getlChild() != null){
                    node.getParent().setrChild(node.getlChild());
                    node.getlChild().setParent(node.getParent());
                }else{
                    node.getParent().setrChild(node.getrChild());
                    node.getrChild().setParent(node.getParent());
                }
            }
            size--;
            swap++;
        }
    }

    @Override
    public boolean search(T key){
        BSTNode<T> traverse = root;
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

    private BSTNode<T> getNode(T key){
        BSTNode<T> reference = new BSTNode<T>(key);
        BSTNode<T> traverse = root;
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

    private BSTNode<T> getNextNode(BSTNode<T> node){
        node = node.getrChild();
        while(node != null && node.getlChild() == null){
            comp=comp+2;
            node = node.getlChild();
        }
        return node;
    }

    private void inOrder(BSTNode<T> node){
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
}
