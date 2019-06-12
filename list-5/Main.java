import Graph.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static String bitStr;
    private static int k, i;
    private static Graph graph;

    private static void getByteString(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter bit string: ");

        // Try to get bit string
        try{
            bitStr = scan.next(Pattern.compile("[01]+"));
        }catch (InputMismatchException ex){
            System.err.println("Error: incorrect input, only characters in bit string can be 0 or 1");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void testEdmondsKarp(){
        // Run edmonds-karp
        EdmondsKarp ek = new EdmondsKarp(0, graph.getNodeNum()-1, graph);
        long tic = System.currentTimeMillis();
        System.out.println("Max flow: "+ek.getMaxFlow());
        long toc = System.currentTimeMillis();
        System.err.println("Time: "+(toc-tic)+" ms\nAug paths: "+ek.getAugPaths());
    }

    public static void main(String[] args) {
        if(args.length > 0){
            if(args[0].equals("--size") && args.length == 2){
                k = Integer.parseInt(args[1]);

                if(k>16 || k<1) throw new RuntimeException("Graph dimension should be number from 1 to 16, you entered "+k);

                graph = new HyperCube(k);
                testEdmondsKarp();
            }else if(args[0].equals("--size") && args[2].equals("--degree") && args.length == 4){
                k = Integer.parseInt(args[1]);
                i = Integer.parseInt(args[3]);

                if(k>16 || k<1) throw new RuntimeException("Size should be number from 1 to 16, you entered "+k);
                if(i>k || i<1) throw new RuntimeException("Degree should be number smaller than size but greater than 0, you entered "+i);

                System.err.println("For k = "+k+" and i = "+i);
                graph = new BipartiteGraph(k, i);
                testEdmondsKarp();
            }else{
                throw new RuntimeException("Incorrect arguments");
            }
        }else{
            throw new RuntimeException("Program needs run arguments: --size <a>, where a is an integer between 1 and 16");
        }
    }
}
