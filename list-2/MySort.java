public abstract class MySort {
    protected long swap = 0;
    protected long comp = 0;

    // Methods for sorting in ascending/descending order
    abstract int[] sortAsc(int[] tab);
    abstract int[] sortDesc(int[] tab);

    // Method swapping elements of array tab on p'th and q'th position
    protected void swap(int[] tab, int p, int q){
        if(p==q) return;

        // Log changes
        //System.out.println("SWAP:  "+tab[p]+"\t<->\t"+tab[q]);

        swap++;
        int swapTemp = tab[p];
        tab[p] = tab[q];
        tab[q] = swapTemp;
    }

    // Method swapping elements of array tab on p'th and q'th position
    protected void comp(int p, int q){
        // Log changes
        //System.out.println("COMP:  "+p+"\tcomp\t"+q);

        comp++;
    }

    // Getters for swap and comp variables
    public long getSwap() {
        return swap;
    }

    public long getComp() {
        return comp;
    }
}
