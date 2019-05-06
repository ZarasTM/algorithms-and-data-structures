import PriorityQueue.*;
import Graph.*;

import java.util.Scanner;

public class Main {
    // LOOPS
    public static void priorityLoop(){
        Scanner scan = new Scanner(System.in);
        PriorityQueue pQueue = new PriorityQueue();

        // Number of operations
        for(int operationNum = scan.nextInt(); operationNum >= 0; operationNum--){
            String str = scan.nextLine();
            String[] operation = str.split(" ");

            if(operation[0].equals("insert") || operation[0].equals("i")){
                pQueue.insert(Integer.parseInt(operation[1]), Integer.parseInt(operation[2]));
            }else if(operation[0].equals("empty") || operation[0].equals("e")){
                System.out.println(pQueue.empty());
            }else if(operation[0].equals("top") || operation[0].equals("t")){
                if(pQueue.empty()){
                    System.out.println();
                }else{
                    System.out.println(pQueue.top().getVal());
                }
            }else if(operation[0].equals("pop") || operation[0].equals("p")){
                if(pQueue.empty()){
                    System.out.println();
                }else{
                    System.out.println(pQueue.pop().getVal());
                }
            }else if(operation[0].equals("priority")){
                pQueue.priority(Integer.parseInt(operation[1]), Integer.parseInt(operation[2]));
            }else if(operation[0].equals("print")){
                System.out.println(pQueue);
            }
        }
    }

    public static void dijkstraLoop(){
        Scanner scan = new Scanner(System.in);
        Graph graph = new Graph();

        // Get Vertices
        System.out.print("Enter number of vertices: ");
        for(int n = scan.nextInt(); n > 0; n--){
            graph.insertVertex();
        }

        int vertStartLabel;
        int vertEndLabel;
        double weight;

        // Get edges
        System.out.print("Enter nuber of edges: ");
        for(int m = scan.nextInt(); m > 0; m--){
            System.out.print("Enter edge: ");
            vertStartLabel = scan.nextInt();
            vertEndLabel = scan.nextInt();
            weight = scan.nextDouble();
            graph.createEdge(vertStartLabel, vertEndLabel, weight);
        }

        // Get start vertex and run algorithm
        System.out.print("Enter starting vertex label: ");
        int startVert = scan.nextInt();

        long tic = System.nanoTime();
        DijkstraAlg dijkstra = new DijkstraAlg(graph, startVert);
        long toc = System.nanoTime();

        dijkstra.printPaths();

        System.err.println("Working time: "+(toc-tic)*0.000001+" ms");
    }

    public static void mstLoop(String[] args){
        if(args.length < 1){
            System.err.println("Type of algorithm has not been entered (-p, -k)");
            return;
        }else if(!args[0].equals("-k") && !args[0].equals("-p")){
            System.err.println("Unknown parameter "+args[0]);
            return;
        }

        Scanner scan = new Scanner(System.in);
        Graph graph = new Graph();

        // Get Vertices
        System.out.print("Enter number of vertices: ");
        for(int n = scan.nextInt(); n > 0; n--){
            graph.insertVertex();
        }

        int vertStartLabel;
        int vertEndLabel;
        double weight;

        // Get edges
        System.out.print("Enter nuber of edges: ");
        for(int m = scan.nextInt(); m > 0; m--){
            System.out.print("Enter edge: ");
            vertStartLabel = scan.nextInt();
            vertEndLabel = scan.nextInt();
            weight = scan.nextDouble();
            graph.createEdge(vertStartLabel, vertEndLabel, weight);
        }

        MST mst;
        if(args[0].equals("-k")){
            mst = new KruskalMST(graph);
        }else if(args[0].equals("-p")){
            mst = new PrimMST(graph);
        }else{
            return;
        }

        System.out.println(mst);

    }

    public static void sccLoop(){
        Scanner scan = new Scanner(System.in);
        Graph graph = new Graph();

        // Get Vertices
        System.out.print("Enter number of vertices: ");
        for(int n = scan.nextInt(); n > 0; n--){
            graph.insertVertex();
        }

        int vertStartLabel;
        int vertEndLabel;

        // Get edges
        System.out.print("Enter nuber of edges: ");
        for(int m = scan.nextInt(); m > 0; m--){
            System.out.print("Enter edge: ");
            vertStartLabel = scan.nextInt();
            vertEndLabel = scan.nextInt();
            graph.createEdge(vertStartLabel, vertEndLabel);
        }

        long tic = System.nanoTime();
        graph.calcSCC();
        long toc = System.nanoTime();

        System.err.println("Working time: "+(toc-tic)*0.000001+" ms");
    }

    // TESTS
    public static void priorityTest(){
        PriorityQueue pQueue = new PriorityQueue();

        pQueue.insert(3, 3);
        pQueue.insert(4, 4);
        pQueue.insert(5, 5);
        System.out.println(pQueue.pop().getVal());
        pQueue.priority(5, 0.5);
        pQueue.insert(0, 0);
        pQueue.insert(1, 1);
        System.out.println(pQueue.pop().getVal());
        pQueue.insert(2, 2);
        pQueue.insert(6, 6);
        pQueue.insert(7, 7);
        System.out.println(pQueue.pop().getVal());
        pQueue.insert(8, 8);
        System.out.println(pQueue.pop().getVal());
        System.out.println(pQueue.pop().getVal());
        System.out.println(pQueue.pop().getVal());
        System.out.println(pQueue.pop().getVal());
        System.out.println(pQueue.pop().getVal());
        System.out.println(pQueue.pop().getVal());
    }

    public static void dijkstraTest(){
        Graph graph = new Graph();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();

        graph.createEdge(1, 2, 9.0);
        graph.createEdge(3, 2, 6.0);
        graph.createEdge(4, 3, 5.0);
        graph.createEdge(5, 4, 7.0);
        graph.createEdge(5, 1, 14.0);
        graph.createEdge(1, 6, 2.0);
        graph.createEdge(6, 5, 9.0);
        graph.createEdge(4, 6, 10.0);
        graph.createEdge(3, 6, 11.0);

        long tic = System.nanoTime();
        DijkstraAlg dij = new DijkstraAlg(graph, 1);
        long toc = System.nanoTime();

        dij.printPaths();

        System.err.println("Working time: "+(toc-tic)*0.000001+" ms");
    }

    public static void kruskalTest(){
        Graph graph = new Graph();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();

        graph.createEdge(1, 2, 9.0);
        graph.createEdge(3, 2, 6.0);
        graph.createEdge(4, 3, 5.0);
        graph.createEdge(5, 4, 7.0);
        graph.createEdge(5, 1, 14.0);
        graph.createEdge(1, 6, 2.0);
        graph.createEdge(6, 5, 9.0);
        graph.createEdge(4, 6, 10.0);
        graph.createEdge(3, 6, 11.0);

        MST mst = new KruskalMST(graph);

        System.out.println(mst);
    }

    public static void primTest(){
        Graph graph = new Graph();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();

        graph.createEdge(1, 2, 9.0);
        graph.createEdge(3, 2, 6.0);
        graph.createEdge(4, 3, 5.0);
        graph.createEdge(5, 4, 7.0);
        graph.createEdge(5, 1, 14.0);
        graph.createEdge(1, 6, 2.0);
        graph.createEdge(6, 5, 9.0);
        graph.createEdge(4, 6, 10.0);
        graph.createEdge(3, 6, 11.0);

        MST mst = new PrimMST(graph);

        System.out.println(mst);
    }

    public static void sccTest(){
        Graph graph = new Graph();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();
        graph.insertVertex();

        graph.createEdge(1, 2, 9.0);
        graph.createEdge(3, 2, 6.0);
        graph.createEdge(4, 3, 5.0);
        graph.createEdge(5, 4, 7.0);
        graph.createEdge(5, 1, 14.0);
        graph.createEdge(1, 6, 2.0);
        graph.createEdge(6, 5, 9.0);
        graph.createEdge(4, 6, 10.0);
        graph.createEdge(3, 6, 11.0);

        graph.calcSCC();
    }

    public static void main(String[] args) {
        boolean test = false;

        if(args.length == 0){
            System.err.println("ERROR: No argument passed. Pass one of the arguments to run program: \n" +
                    "\t-prior - to run priority queue program\n" +
                    "\t-dijkstra - to run dijkstra algorithm program\n" +
                    "\t-mst - to run min spanning tree program\n" +
                    "\t\t-p - to use Prim's algorithm" +
                    "\t\t-k - to use Kruskal's algorithm\n" +
                    "\t-scc - to run program finding strongly connected components in graph \n\n" +
                    "If you want to run test programs add \"--test\" or \"-t\" option");
            return;
        }else if(args[0].equals("--help") || args[0].equals("-h")){
            System.err.println("Pass one of the arguments to run program: \n" +
                    "\t-prior - to run priority queue program\n" +
                    "\t-dijkstra - to run dijkstra algorithm program\n" +
                    "\t-mst - to run min spanning tree program\n" +
                    "\t\t-p - to use Prim's algorithm" +
                    "\t\t-k - to use Kruskal's algorithm\n" +
                    "\t-scc - to run program finding strongly connected components in graph \n\n" +
                    "If you want to run test programs add \"--test\" or \"-t\" option");
            return;
        }else{
            for(String str : args){
                if(str.equals("--test") || str.equals("-t")) test = true;
            }
        }

        if(!test) {
            switch (args[0]) {
                case "-prior":
                    priorityLoop();
                    break;
                case "-dijkstra":
                    dijkstraLoop();
                    break;
                case "-mst":
                    mstLoop(args);
                    break;
                case "-scc":
                    sccLoop();
                    break;
                default:
                    System.err.println("ERROR: invalid argument \"" + args[0] + "\" passed");
                    break;
            }
        }else{
            switch (args[0]) {
                case "-prior":
                    System.err.println("Operations executed are: \n" +
                            "insert 3 3\n" +
                            "insert 4 4\n" +
                            "insert 5 5\n" +
                            "pop\n" +
                            "priority 5 0.5\n" +
                            "insert 0 0\n" +
                            "insert 1 1\n" +
                            "pop\n" +
                            "insert 2 2\n" +
                            "insert 6 6\n" +
                            "insert 7 7\n" +
                            "pop\n" +
                            "insert 8 8\n" +
                            "pop\n" +
                            "pop\n" +
                            "pop\n" +
                            "pop\n" +
                            "pop\n" +
                            "pop\n");
                    priorityTest();
                    break;
                case "-dijkstra":
                    dijkstraTest();
                    break;
                case "-mst":
                    System.out.println("Test run for Kruskal's algorithm");
                    kruskalTest();

                    System.out.println("Test run for Prim's algorithm");
                    primTest();
                    break;
                case "-scc":
                    sccTest();
                    break;
                default:
                    System.err.println("ERROR: invalid argument \"" + args[0] + "\" passed");
                    break;
            }
        }
    }
}
