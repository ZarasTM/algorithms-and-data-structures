import Tree.*;
import Tree.BinarySearchTree.*;
import Tree.RedBlackTree.*;
import Tree.SplayTree.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void testTree(Tree<String> tree){
        tree.insert("b");
        tree.insert("e");
        tree.insert("h");
        tree.insert("g");
        tree.insert("f");
        tree.delete("h"); // del inside
        tree.insert("a");
        tree.insert("c");
        tree.insert("d");
        tree.insert("j");
        tree.insert("i");
        tree.delete("b"); // del root
        System.out.println(tree.search("x"));
        System.out.println(tree.search("j"));

        tree.inOrder();
    }

    public static List<String> load(String path){
        List<String> wordList = new ArrayList<String>();
        BufferedReader br;

        try {
            File file = new File(path);
            br = new BufferedReader(new FileReader(file));

            String line;
            String[] words;
            String rawStr;
            while((line = br.readLine()) != null){
                words = line.split(" ");
                for(String word : words){
                    rawStr = word.replaceAll("[^\\p{L}\\p{Nd}]+", "");
                    wordList.add(rawStr);
                }
            }
        }catch(Exception e){
            e.printStackTrace(System.err);
        }

        return wordList;
    }

    public static void main(String[] args) throws Exception {
        Tree<String> tree;

        // Time/Count variables
        long tic;
        long insertTime = 0;
        long deleteTime = 0;
        long searchTime = 0;
        long inorderTime = 0;
        long insertCount = 0;
        long deleteCount = 0;
        long searchCount = 0;
        long inorderCount = 0;

        if(args.length != 0){
            if(args[0].equals("--type") && args.length > 1){
                if(args[1].equals("bst")){
                    tree = new BST<String>();
                }else if(args[1].equals("rbt")){
                    tree = new RBT<String>();
                }else if(args[1].equals("splay")){
                    tree = new SplayBST<String>();
                }else{
                    throw new Exception("Error: Unknown tree type passed \""+args[1]+"\"");
                }
            }else{
                System.err.println("Using red-black trees as default");
                tree = new RBT<String>();
            }

            Scanner scan = new Scanner(System.in);
            String[] line;

            System.err.print("Enter number of commands: ");
            for(int count = scan.nextInt(); count > 0; count--){
                line = scan.nextLine().split(" ");
                if(line[0].equals("insert")){
                    insertCount++;
                    tic = System.currentTimeMillis();
                    tree.insert(line[1]);
                    insertTime += System.currentTimeMillis() - tic;
                }else if(line[0].equals("delete")){
                    deleteCount++;
                    tic = System.currentTimeMillis();
                    tree.delete(line[1]);
                    deleteTime += System.currentTimeMillis() - tic;
                }else if(line[0].equals("search")){
                    searchCount++;
                    tic = System.currentTimeMillis();
                    System.out.println(tree.search(line[1]));
                    searchTime += System.currentTimeMillis() - tic;
                }else if(line[0].equals("inorder")){
                    inorderCount++;
                    tic = System.currentTimeMillis();
                    tree.inOrder();
                    inorderTime += System.currentTimeMillis() - tic;
                }else if(line[0].equals("load")){
                    List<String> wordList = load(line[1]);
                    for(String word : wordList){
                        insertCount++;
                        tic = System.currentTimeMillis();
                        tree.insert(word);
                        inorderTime += System.currentTimeMillis() - tic;
                    }
                }else{
                    throw new Exception("Error: Unknown operation\""+line[0]+"\"");
                }
            }
        }else{
            throw new Exception("Error: No arguments passed");
        }

        System.err.println("Time of execution: "+(insertTime+deleteTime+searchTime+inorderTime)+"ms");
        System.err.println("Swaps: "+tree.getSwap()+
                "\nComp: "+tree.getComp()+
                "\nInsert: "+insertCount+"\t"+insertTime+"ms"+
                "\nDelete: "+deleteCount+"\t"+deleteTime+"ms"+
                "\nSearch: "+searchCount+"\t"+searchTime+"ms"+
                "\nInOrder: "+inorderCount+"\t"+inorderTime+"ms");
    }
}
