public class HeapSort extends MySort {

    @Override
    public int[] sortAsc(int[] tab) {
        super.comp = 0;
        super.swap = 0;

        buildHeapAsc(tab);
        int sizeOfHeap=tab.length-1;
        for(int i=sizeOfHeap; i>0; i--) {
            super.swap(tab,0, i);
            sizeOfHeap = sizeOfHeap-1;
            heapifyAsc(tab, 0,sizeOfHeap);
        }
        return tab;
    }

    @Override
    public int[] sortDesc(int[] tab) {
        super.comp = 0;
        super.swap = 0;

        buildHeapDesc(tab);
        int sizeOfHeap=tab.length-1;
        for(int i=sizeOfHeap; i>0; i--) {
            super.swap(tab,0, i);
            sizeOfHeap=sizeOfHeap-1;
            heapifyDesc(tab, 0,sizeOfHeap);
        }
        return tab;
    }

    private void heapifyAsc(int tab[], int i, int n) {
        int left = 2*i+1;
        int right = 2*i+2;
        int max;

        if(left <= n){
            super.comp(tab[left], tab[i]);
            if(tab[left] > tab[i]){
                max=left;
            }else{
                max=i;
            }
        } else {
            max=i;
        }

        if(right <= n) {
            super.comp(tab[right], tab[max]);
            if(tab[right] > tab[max]){
                max = right;
            }
        }

        // If max is not current node, swap it with max of left and right child
        if(max!=i) {
            super.swap(tab,i, max);
            heapifyAsc(tab, max,n);
        }
    }

    private void heapifyDesc(int tab[], int i, int n) {
        int left = 2*i+1;
        int right = 2*i+2;
        int min;

        if(left <= n){
            super.comp(tab[left], tab[i]);
            if(tab[left] < tab[i]){
                min=left;
            }else{
                min=i;
            }
        } else {
            min=i;
        }

        if(right <= n) {
            super.comp(tab[right], tab[min]);
            if(tab[right] < tab[min]){
                min = right;
            }
        }

        if(min!=i) {
            swap(tab,i, min);
            heapifyDesc(tab, min, n);
        }
    }

    private void buildHeapAsc(int []tab) {
        for(int i=(tab.length-1)/2; i>=0; i--){
            heapifyAsc(tab, i,tab.length-1);
        }
    }

    private void buildHeapDesc(int []tab) {
        for(int i=(tab.length-1)/2; i>=0; i--){
            heapifyDesc(tab, i,tab.length-1);
        }
    }
}
