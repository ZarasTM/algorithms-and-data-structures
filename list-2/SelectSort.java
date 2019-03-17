public class SelectSort extends MySort {

    @Override
    public int[] sortAsc(int[] tab) {
        super.comp = 0;
        super.swap = 0;

        for(int idx=0; idx<tab.length; idx++){
            super.swap(tab, idx, getNextMin(tab, idx));
        }

        return tab;
    }

    @Override
    public int[] sortDesc(int[] tab) {
        super.comp = 0;
        super.swap = 0;

        for(int idx=0; idx<tab.length; idx++){
            super.swap(tab, idx, getNextMax(tab, idx));
        }
        return tab;
    }

    // Returns index of min element after start
    private int getNextMin(int[] tab, int start){
        int minIdx = start;
        int prevMin = tab[start];

        for(int idx=start+1; idx<tab.length; idx++){
            super.comp(tab[idx], prevMin);
            if(tab[idx] < prevMin){
                prevMin = tab[idx];
                minIdx = idx;
            }
        }

        return minIdx;
    }

    // Returns index of max element after start
    private int getNextMax(int[] tab, int start){
        int maxIdx = start;
        int prevMax = tab[start];

        for(int idx=start+1; idx<tab.length; idx++){
            super.comp(tab[idx], prevMax);
            if(tab[idx] > prevMax){
                prevMax = tab[idx];
                maxIdx = idx;
            }
        }

        return maxIdx;
    }
}
