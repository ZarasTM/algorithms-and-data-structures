import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;

public class Main {
    /*
     * Contains data for every algorithm: size, comp, swap, time
     * k repetitons of every alg in order: select, insert, heap, quick, hybrid
     */
    private static List<long[]> allData = new ArrayList<>();
    private static int order=0;
    private static int[] tab;
    private static MySort sortAlg = null;
    private static Scanner scan = new Scanner(System.in);

    public static int[] shuffleArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    private static void getData(int elNum, String alg){
        // Time variables
        long tic;
        long toc;

        int[] tmpTab = tab.clone();

        tic = System.nanoTime();
        sortAlg.sortAsc(tmpTab);
        toc = System.nanoTime();

        if(!isSortedAsc(tmpTab)){
            System.err.println("Tab is not sorted "+alg);
            System.exit(1);
        }

        allData.add(new long[]{
                elNum,
                sortAlg.getComp(),
                sortAlg.getSwap(),
                (toc - tic)
        });
    }

    private static void test(String path, int repeat){
        Random rand = new SecureRandom();
        int elNum = 100;

        while(elNum <= 10000) {
            for(int rep = repeat; rep>0; rep--) {
                tab = new int[elNum];
                for(int idx=0; idx<tab.length; idx++){
                    tab[idx] = rand.nextInt();
                }

                tab = shuffleArray(tab);

                // Select sort
                sortAlg = new SelectSort();
                getData(elNum, "SELECT");

                // Insertion sort
                sortAlg = new InsertionSort();
                getData(elNum, "INSERT");

                // Heap sort
                sortAlg = new HeapSort();
                getData(elNum, "HEAP");

                // Quick sort
                sortAlg = new QuickSort();
                getData(elNum, "QUICK");

                // Hybrid sort
                sortAlg = new HybridSort();
                getData(elNum, "HYBRID");
            }
            elNum = elNum + 100;
        }
        toFile(path, repeat);
    }

    // Prints the result to a file
    private static void toFile(String path, int k){
        StringBuilder strBuild = new StringBuilder("tab.size()\tcomp (average)\tswap (average)\ttime (average)");
        int count = 1;
        int idx = 0;

        long n;
        long sumComp;
        long sumSwap;
        long sumTime;

        while(count < allData.size()){
            n=0;
            sumComp = 0;
            sumSwap = 0;
            sumTime = 0;

            for(int control=0; control<k; control++) {
                n = allData.get(idx)[0];
                sumComp += allData.get(idx)[1];
                sumSwap += allData.get(idx)[2];
                sumTime += allData.get(idx)[3];
                //System.out.println(allData.get(idx)[3]);

                /*strBuild.append("\n")
                    .append(allData.get(idx)[0]).append("\t")
                    .append(allData.get(idx)[1]).append("\t")
                    .append(allData.get(idx)[2]).append("\t")
                    .append(allData.get(idx)[3]);*/

                if (idx + 5 > allData.size() - 1) {
                    idx = (idx + 6) % 5;
                } else {
                    idx = idx + 5;
                }
                count++;
            }

            strBuild.append("\n")
                    .append(n).append("\t")
                    .append(sumComp/k).append("\t")
                    .append(sumSwap/k).append("\t")
                    .append(sumTime/k);
        }

        try {
            Files.writeString(Paths.get(path), strBuild.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void toFile(String path, int[] tab){
        try {
            Files.writeString(Paths.get(path), Arrays.toString(tab));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method checking if given array is sorted
    private static boolean isSortedAsc(int[] tab){
        int prev = tab[0];

        // Check if sorted
        for(int elem : tab){
            if(prev > elem){
                return false;
            }
        }
        return true;
    }

    private static boolean isSortedDesc(int[] tab){
        int prev = tab[0];

        // Check if sorted
        for(int elem : tab){
            if(prev > elem){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Get arguments
        for(int arg=0; arg<args.length; arg++){
            // Get sorting type
            if(args[arg].equals("--type")){
                try{
                    switch (args[arg + 1]) {
                        case "select":
                            sortAlg = new SelectSort();
                            break;
                        case "insert":
                            sortAlg = new InsertionSort();
                            break;
                        case "heap":
                            sortAlg = new HeapSort();
                            break;
                        case "quick":
                            sortAlg = new QuickSort();
                            break;
                        case "mquick":
                            sortAlg = new HybridSort();
                            break;
                        default:
                            System.err.println("Algorithm \"" + args[arg + 1] + "\" is not valid");
                            System.exit(1);
                    }
                    arg++;
                }catch(IndexOutOfBoundsException e){
                    System.err.println("Not sufficient amount of arguments\n");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
            // Get order of sorting
            if(args[arg].equals("--desc")) order = -1;
            else if(args[arg].equals("--asc")) order = 1;

            // Handle --stat option
            if(args[arg].equals("--stat")){
                try{
                    test(args[arg+1], Integer.parseInt(args[arg+2]));
                    return;
                }catch(IndexOutOfBoundsException e){
                    System.err.println("Filepath or/and number of repetitions has not been entered\n");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        // Check if all arguments were given
        if(sortAlg == null){
            System.err.println("Sorting algorithm has not been correctly entered");
            System.exit(1);
        }else if(order != 1 && order != -1){
            System.err.println("Sorting order has not been correctly entered");
            System.exit(1);
        }

        // Get number of elements
        System.out.print("Enter number of elements: ");
        int tabLen = scan.nextInt();

        if(tabLen <= 0) {
            System.err.println(tabLen+" is too few elements to sort");
            System.exit(1);
        }

        // Get elements
        System.out.print("Enter elements: ");
        tab = new int[tabLen];

        for(int i = 0; i<tabLen; i++){
            tab[i] = scan.nextInt();
        }

        if(tabLen <= 25) System.out.println("Not sorted: "+ Arrays.toString(tab)+"\n");

        // Sort
        if(order == 1){
            tab = sortAlg.sortAsc(tab);
        }else{
            tab = sortAlg.sortDesc(tab);
        }

        if(order == 1){
            if(!isSortedAsc(tab)){
                if(tabLen < 25){
                    System.err.println("\nError while sorting: output array is not sorted: "+Arrays.toString(tab));
                }else{
                    System.err.println("Error while sorting: output array is not sorted");
                }
                System.exit(1);
            }
        }else if(order == -1){
            if(!isSortedDesc(tab)){
                if(tabLen < 25){
                    System.err.println("\nError while sorting: output array is not sorted: "+Arrays.toString(tab));
                }else{
                    System.err.println("Error while sorting: output array is not sorted");
                }
                System.exit(1);
            }
        }


        // Print sorted array to result.txt instead output if there are more than 25 elements
        if(tabLen <= 25) {
            System.out.println("\nSorted "+tab.length+" elements");
            System.out.println("Sorted: "+ Arrays.toString(tab));
        }else{
            toFile("./result.txt", tab);
        }
    }
}
