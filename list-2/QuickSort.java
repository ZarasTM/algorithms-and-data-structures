public class QuickSort extends MySort {
    protected short sortOrder = 0;

    @Override
    public int[] sortAsc(int[] tab) {
        super.comp = 0;
        super.swap = 0;

        // Set sort order
        sortOrder = 1;

        quickSort(tab, 0, tab.length-1);

        // Restart sort order
        sortOrder = 0;
        return tab;
    }

    @Override
    public int[] sortDesc(int[] tab) {
        super.comp = 0;
        super.swap = 0;

        // Set sort order
        sortOrder = -1;

        quickSort(tab, 0, tab.length-1);

        // Restart sort order
        sortOrder = 0;

        return tab;
    }

    protected void quickSort(int tab[], int start, int end) {
        if (start >= end) return;

        int partIdx=0;

        if(sortOrder == 1){
            partIdx = partitionAsc(tab, start, end);
        }else if(sortOrder == -1){
            partIdx = partitionDesc(tab, start, end);
        }else{
            System.err.println("Error in quicksort order");
            System.exit(1);
        }

        quickSort(tab, start, partIdx-1);
        quickSort(tab, partIdx+1, end);
    }

    protected int partitionAsc(int tab[], int start, int end) {
        /*
        int i = start - 1;
        int j = end + 1;
        int median = getMedian(tab, start, end);
        int piv = tab[median];

        // Get median in the middle
        if(median == start) swap(tab, start, (start+end)/2);
        else if(median == end) swap(tab, end, (start+end)/2);

        while(true) {
            while (tab[++i] < piv);
            while (tab[--j] > piv);

            if (i >= j) break;

            swap(tab, i, j);
        }
        return j;
        */

        int i = start-1;
        int median = getMedian(tab, start, end);
        int piv = tab[median];

        // Get median in the middle
        if(median == start) swap(tab, start, end);
        else if(median != end) swap(tab, end, (start+end)/2);

        for (int j = start; j < end; j++) {
            super.comp(tab[j], piv);
            if (tab[j] < piv) {
                i++;

                super.swap(tab, i, j);
            }
        }
        super.swap(tab, i+1, end);

        return i+1;
    }

    protected int partitionDesc(int tab[], int start, int end) {
        /*
        int i = start - 1;
        int j = end + 1;

        int median = getMedian(tab, start, end);
        int piv = tab[median];

        // Get median in the middle
        if(median == start) swap(tab, start, (start+end)/2);
        else if(median == end) swap(tab, end, (start+end)/2);

        while(true) {
            while (tab[++i] > piv);
            while (tab[--j] < piv);

            if (i >= j) break;

            swap(tab, i, j);
        }
        return j;
        */

        int i = start-1;
        int median = getMedian(tab, start, end);
        int piv = tab[median];

        // Get median in the middle
        if(median == start) swap(tab, start, end);
        else if(median != end) swap(tab, end, (start+end)/2);

        for (int j = start; j < end; j++) {
            super.comp(tab[j], piv);
            if (tab[j] > piv) {
                i++;

                super.swap(tab, i, j);
            }
        }

        super.swap(tab, i+1, end);
        return i+1;
    }

    // Returns index of median from first, last and middle element
    protected int getMedian(int tab[], int start, int end){
        int middle = (start+end)/2;

        comp++;
        if(tab[start] >= tab[end]){
            comp++;
            if(tab[start] >= tab[middle]){
                comp++;
                if(tab[middle] >= tab[end]){
                    return middle;
                }else{
                    return end;
                }
            }else{
                return start;
            }
        }else{
            comp++;
            if(tab[start] >= tab[middle]){
                return end;
            }else{
                comp++;
                if(tab[end] >= tab[middle]){
                    return middle;
                }else{
                    return end;
                }
            }
        }
    }
}
