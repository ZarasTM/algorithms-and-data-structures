package Tree.RedBlackTree;

import Tree.Node;

public class RBNode<T> extends Node<T> {
    private RBNode<T> lChild;
    private RBNode<T> rChild;
    private RBNode<T> parent;

    // false = black, true = red
    private boolean color = false;

    public RBNode(T key) {
        super(key);
    }

    public RBNode(T key, RBNode<T> parent, RBNode<T> lChild, RBNode<T> rChild) {
        super(key);
        this.lChild = lChild;
        this.rChild = rChild;
        this.parent = parent;
    }

    // SETTERS & GETTERS
    public RBNode<T> getlChild() {
        return lChild;
    }

    void setlChild(Node<T> lChild) {
        this.lChild = (RBNode<T>) lChild;
    }

    public RBNode<T> getrChild() {
        return rChild;
    }

    void setrChild(Node<T> rChild) {
        this.rChild = (RBNode<T>) rChild;
    }

    public RBNode<T> getParent() {
        return  parent;
    }

    void setParent(Node<T> parent) {
        this.parent = (RBNode<T>) parent;
    }

    public boolean getColor() {
        return color;
    }

    void setColor(boolean color){
        this.color = color;
    }

    void changeColor(){
        color = !color;
    }
}
