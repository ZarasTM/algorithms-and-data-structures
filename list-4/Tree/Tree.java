package Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public abstract class Tree<T> {
    protected long size = 0;
    protected long comp = 0;
    protected long swap = 0;

    public abstract void insert(T key);

    public abstract void delete(T key);

    public abstract boolean search(T key);

    public abstract void inOrder();

    public long getSize() {
        return size;
    }

    public long getComp() {
        return comp;
    }

    public long getSwap() {
        return swap;
    }
}
