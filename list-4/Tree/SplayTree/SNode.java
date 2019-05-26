package Tree.SplayTree;

import Tree.Node;

public class SNode<T> extends Node<T> {
    private SNode<T> lChild = null;
    private SNode<T> rChild = null;
    private SNode<T> parent = null;

    public SNode(T key) {
        super(key);
    }

    public SNode(T key, SNode<T> parent, SNode<T> lChild, SNode<T> rChild) {
        super(key);
        this.lChild = lChild;
        this.rChild = rChild;
        this.parent = parent;
    }

    // SETTERS & GETTERS
    public SNode<T> getlChild() {
        return lChild;
    }

    void setlChild(Node<T> lChild) {
        this.lChild = (SNode<T>) lChild;
    }

    public SNode<T> getrChild() {
        return rChild;
    }

    void setrChild(Node<T> rChild) {
        this.rChild = (SNode<T>) rChild;
    }

    public SNode<T> getParent() {
        return  parent;
    }

    void setParent(Node<T> parent) {
        this.parent = (SNode<T>) parent;
    }
}
