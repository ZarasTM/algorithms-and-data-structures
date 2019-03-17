public class InsertionSort extends MySort {

    @Override
    public int[] sortAsc(int[] tab) {
        super.comp = 0;
        super.swap = 0;

        int toInsert;
        int inIdx;
        for(int outIdx=1; outIdx<tab.length; outIdx++){
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

        return tab;
    }

    @Override
    public int[] sortDesc(int[] tab) {
        super.comp = 0;
        super.swap = 0;

        int toInsert;
        int inIdx;
        for(int outIdx=1; outIdx<tab.length; outIdx++){
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

        return tab;
    }
}
