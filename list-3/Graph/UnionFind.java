package Graph;

public class UnionFind {

    private int[] compSize; // Size of components
    private int[] id;   // Pointers to parents of index
    private int compNumber; // Number of components

    public UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");

        compSize = new int[size + 1];
        id = new int[size + 1];

        compNumber = size;

        for (int i = 1; i <= size; i++) {
            id[i] = i; // Link to itself (self root)
            compSize[i] = 1; // Each component is originally of size one
        }
    }

    // Find which set p belongs to
    public int find(int p) {

        if (p <= 0)
            throw new IllegalArgumentException("Index <= 0 is not allowed");

        // Get root
        int root = p;
        while (root != id[root])
            root = id[root];

        // Compress the path
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;

    }

    // Check if p and q are connected
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int getNumberOfComponents() {
        return compNumber;
    }

    // Unify sets containing p and q
    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        // Return if already unified
        if (root1 == root2) return;

        // Merge smaller set to the larger
        if (compSize[root1] < compSize[root2]) {
            compSize[root2] += compSize[root1];
            id[root1] = root2;
        } else {
            compSize[root1] += compSize[root2];
            id[root2] = root1;
        }

        compNumber--;
    }
}