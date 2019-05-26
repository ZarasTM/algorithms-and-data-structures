package Tree;

public abstract class Node<T> implements Comparable<Node<T>> {
    protected T key;

    // CONSTRUCTORS
    public Node(T key){
        this.key = key;
    }

    // SETTERS & GETTERS
    public T getKey() {
        return key;
    }
    public void setKey(T key) {
        this.key = key;
    }

    // COMPARING OVERRIDE
    @Override
    public int compareTo(Node<T> o) {
        if(o.getKey() instanceof Comparable &&
                this.getKey() instanceof Comparable){
            return ((Comparable) this.getKey()).compareTo(o.getKey());
        }else{
            System.err.println("Error: Compared objects don't implement Comparable interface");
            System.exit(1);
        }
        return 0;
    }
}
