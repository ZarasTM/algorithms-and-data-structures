public class HybridSort extends QuickSort {

    @Override
    protected void quickSort(int tab[], int start, int end) {
        // If there are less than 16 elements go for Insertion sort
        if (start < end-15) {
            int partIdx=0;

            if(super.sortOrder == 1){
                partIdx = super.partitionAsc(tab, start, end);
            }else if(super.sortOrder == -1){
                partIdx = super.partitionDesc(tab, start, end);
            }else{
                System.err.println("Error in HybridSort order for QuickSort");
                System.exit(1);
            }

            quickSort(tab, start, partIdx-1);
            quickSort(tab, partIdx+1, end);
        }else{
            if(super.sortOrder == 1){
                insertSortAsc(tab, start, end);
            }else if(super.sortOrder == -1){
                insertSortDesc(tab, start, end);
            }else{
                System.err.println("Error in HybridSort order for InsertionSort");
                System.exit(1);
            }
        }
    }

    private void insertSortAsc(int[] tab, int start, int end){
        int toInsert;
        int inIdx;

        for(int outIdx=start+1; outIdx<end+1; outIdx++){
            toInsert = tab[outIdx];
            inIdx = outIdx-1;
            while(inIdx >= 0){
                super.comp(tab[inIdx], toInsert);
                if(tab[inIdx] > toInsert){
                    tab[inIdx+1] = tab[inIdx];
                }else{
                    break;
                }
                inIdx--;
            }
            tab[inIdx+1] = toInsert;
        }
    }

    private void insertSortDesc(int[] tab, int start, int end){
        int toInsert;
        int inIdx;
        for(int outIdx=start+1; outIdx<end+1; outIdx++){
            toInsert = tab[outIdx];
            inIdx = outIdx-1;
            while(inIdx >= 0){
                super.comp(tab[inIdx], toInsert);
                if(tab[inIdx] < toInsert){
                    tab[inIdx+1] = tab[inIdx];
                }else{
                    break;
                }
                inIdx--;
            }
            tab[inIdx+1] = toInsert;
        }
    }
}
