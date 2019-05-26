package Tree.BinarySearchTree;

import Tree.Node;

public class BSTNode<T> extends Node<T> {
    private BSTNode<T> lChild;
    private BSTNode<T> rChild;
    private BSTNode<T> parent;

    public BSTNode(T key){
        super(key);
    }

    public BSTNode(T key, BSTNode<T> parent, BSTNode<T> lChild, BSTNode<T> rChild){
        super(key);
        this.lChild = lChild;
        this.rChild = rChild;
        this.parent = parent;
    }

    // SETTERS & GETTERS
    public BSTNode<T> getlChild() {
        return lChild;
    }

    void setlChild(Node<T> lChild) {
        this.lChild = (BSTNode<T>) lChild;
    }

    public BSTNode<T> getrChild() {
        return rChild;
    }

    void setrChild(Node<T> rChild) {
        this.rChild = (BSTNode<T>) rChild;
    }

    public BSTNode<T> getParent() {
        return  parent;
    }

    void setParent(Node<T> parent) {
        this.parent = (BSTNode<T>) parent;
    }
}
