import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// A Generic class that contains Tree 
class Tree <T>
{
    private ArrayList <T> array;
    private int size;

    // Constructor
    Tree(int size)
    {
        this.size = size;
        array = new ArrayList <T> (size);
        
        for(int i = 0; i < size; i++)
        {
            array.add(null);
        }

    }

    // A method that inserts a value into the tree
    public void setValue(int index, int value)
    {
        try
        {
            array.set(index, (T)(Integer.valueOf(value)));

        }

        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Index out of bounds");
        }
    }

    // A method that returns the value at a given index
    public int getValue(int index)
    {
        return (int)array.get(index);
    }

    // A method that returns the size of the tree
    public int getHeight()
    {
        int count = 0;
        while ((1 << count) < size)
        {
            count++;
        }
        return count;
    }

    // To-String method
    public String toString()
    {
        String s = "";
        for (int i = 0; i < size; i++)
        {
            s += array.get(i) + " ";
        }
        return s;
    }
}

// A Generic class that contains the BuildTree Recursive Method
class BuildTree <T> implements Runnable
{
    private Tree <Integer> tree;
    private ArrayList <Integer> array;
    private int start;
    private int end;

    // Constructor
    BuildTree(Tree <Integer> tree, ArrayList <Integer> array, int start, int end)
    {
        this.tree = tree;
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // A method that builds the tree
    public void build(int index)
    {
        if (index >= end)
        {
            return;
        }
        
        tree.setValue(index, array.get(index));

        build(2 * index + 1);
        build(2 * index + 2);
    }

    @Override
    public void run()
    {
        build(start);
    }

    // To-String method
    public String toString()
    {
        return tree.toString();
    }
}

// A Generic class that contains the SearchTree algorithm
class SearchTree implements Runnable
{
    private Tree <Integer> tree;
    private int start;
    private int end;
    private int value;

    // Constructor
    SearchTree(Tree <Integer> tree, int start, int end, int value)
    {
        this.tree = tree;
        this.start = start;
        this.end = end;
        this.value = value;
    }

    // A method that searches the tree for a value
    public boolean search(int index)
    {
        if (index >= end)
        {
            return false;
        }

        if (tree.getValue(index) == value)
        {
            return true;
        }

        return search(2 * index + 1) || search(2 * index + 2);
    }

    @Override
    public void run()
    {
        search(start);
    }
}


public class Main2
{
    public static void main(String[] args)  throws InterruptedException
    {
        int[] numberOfNodes = {10, 100, 1000000}; // The number of nodes in the tree
        int[] numberOfThreads = {1, 2, 4}; // The number of threads to use

        // Creates a file to write the results to
        try 
        {
            File myObj = new File("output2.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } 
        
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        // Loops through the number of nodes
        for (int i = 0; i < numberOfNodes.length; i++)
        {
            ArrayList <Integer> array = new ArrayList <Integer> (numberOfNodes[i]);

            // Fills the array with random numbers
            for (int j = 0; j < numberOfNodes[i]; j++)
            {
                array.add((int)(Math.random() * 2000000000 - 1000000000));
            }
            
            // Get a random value to search for
            int value = array.get((int)(Math.random() * numberOfNodes[i]));
            
            // Loops through the number of threads
            for (int j = 0; j < numberOfThreads.length; j++)
            {
                if (j == 0)
                {                    
                    Tree <Integer> tree = new Tree <Integer> (numberOfNodes[i]);
                    BuildTree <Integer> buildTree = new BuildTree <Integer> (tree, array, 0, numberOfNodes[i]);

                    Thread thread = new Thread(buildTree);
                    
                    // Calculates the time it takes to build the tree
                    long startTime = System.nanoTime();
                    thread.start();
                    thread.join();
                    long endTime = System.nanoTime();
                    
                    // search for an element present in the tree
                    SearchTree searchTree = new SearchTree(tree, 0, numberOfNodes[i], value);
                    
                    thread = new Thread(searchTree);
                    
                    // Calculates the time it takes to search the tree
                    startTime = System.nanoTime();
                    thread.start();
                    thread.join();
                    endTime = System.nanoTime();

                    // Writes the results to the file
                    try 
                    {
                        FileWriter myWriter = new FileWriter("output2.txt", true);
                        myWriter.write("Using 1 thread for " + numberOfNodes[i] + " nodes \n");
                        myWriter.write("Time taken using 1 thread: " + (endTime - startTime)/100000 + " milliseconds \n");
                        myWriter.write("Height of the tree: " + tree.getHeight());
                        myWriter.write("\nTime taken to search for " + value + " using 1 thread: " + (endTime - startTime)/100000 + " milliseconds \n");
                        myWriter.write("\n");
                        myWriter.close();
                    }
                    catch (IOException e) 
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    
                }

                else if (j == 1)
                {
                    
                    Tree <Integer> tree = new Tree <Integer> (numberOfNodes[i]);
                    tree.setValue(0, array.get(0));
                    
                    // Creates 2 threads to build the tree
                    BuildTree <Integer> buildTree1 = new BuildTree <Integer> (tree, array, 1, numberOfNodes[i]);
                    BuildTree <Integer> buildTree2 = new BuildTree <Integer> (tree, array, 2, numberOfNodes[i]);
                    
                    Thread thread1 = new Thread(buildTree1);
                    Thread thread2 = new Thread(buildTree2);
                    
                    // Calculates the time it takes to build the tree
                    long startTime = System.nanoTime();
                    thread1.start();
                    thread2.start();
                    thread1.join();
                    thread2.join();
                    long endTime = System.nanoTime();
                    
                    // search for an element present in the tree
                    SearchTree searchTree1 = new SearchTree(tree, 0, numberOfNodes[i] / 2, value);
                    SearchTree searchTree2 = new SearchTree(tree, numberOfNodes[i] / 2, numberOfNodes[i], value);
                    
                    thread1 = new Thread(searchTree1);
                    thread2 = new Thread(searchTree2);
                    
                    // Calculates the time it takes to search the tree
                    startTime = System.nanoTime();
                    thread1.start();
                    thread2.start();
                    thread1.join();
                    thread2.join();
                    endTime = System.nanoTime();

                    // Writes the results to the file
                    try 
                    {
                        FileWriter myWriter = new FileWriter("output2.txt", true);
                        myWriter.write("Using 2 threads for " + numberOfNodes[i] + " nodes \n");
                        myWriter.write("Time taken using 2 threads: " + (endTime - startTime)/100000 + " milliseconds \n");
                        myWriter.write("Height of the tree: " + tree.getHeight());
                        myWriter.write("\nTime taken to search for " + value + " using 2 threads: " + (endTime - startTime)/100000 + " milliseconds\n");
                        myWriter.write("\n");
                        myWriter.close();
                    }
                    catch (IOException e) 
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                }

                else if (j == 2)
                {
                    
                    Tree <Integer> tree = new Tree <Integer> (numberOfNodes[i]);
                    tree.setValue(0, array.get(0));
                    tree.setValue(1, array.get(1));
                    tree.setValue(2, array.get(2));
                    
                    // Creates 4 threads to build the tree
                    BuildTree <Integer> buildTree1 = new BuildTree <Integer> (tree, array, 3, numberOfNodes[i]);
                    BuildTree <Integer> buildTree2 = new BuildTree <Integer> (tree, array, 4, numberOfNodes[i]);
                    BuildTree <Integer> buildTree3 = new BuildTree <Integer> (tree, array, 5, numberOfNodes[i]);
                    BuildTree <Integer> buildTree4 = new BuildTree <Integer> (tree, array, 6, numberOfNodes[i]);

                    Thread thread1 = new Thread(buildTree1);
                    Thread thread2 = new Thread(buildTree2);
                    Thread thread3 = new Thread(buildTree3);
                    Thread thread4 = new Thread(buildTree4);
                    
                    // Calculates the time it takes to build the tree
                    long startTime = System.nanoTime();
                    thread1.start();
                    thread2.start();
                    thread3.start();
                    thread4.start();
                    thread1.join();
                    thread2.join();
                    thread3.join();
                    thread4.join();
                    long endTime = System.nanoTime();
                    
                    // search for an element present in the tree and print the time taken
                    SearchTree searchTree1 = new SearchTree(tree, 0, numberOfNodes[i] / 4, value);
                    SearchTree searchTree2 = new SearchTree(tree, numberOfNodes[i] / 4, numberOfNodes[i] / 2, value);
                    SearchTree searchTree3 = new SearchTree(tree, numberOfNodes[i] / 2, 3 * numberOfNodes[i] / 4, value);
                    SearchTree searchTree4 = new SearchTree(tree, 3 * numberOfNodes[i] / 4, numberOfNodes[i], value);
                    
                    thread1 = new Thread(searchTree1);
                    thread2 = new Thread(searchTree2);
                    thread3 = new Thread(searchTree3);
                    thread4 = new Thread(searchTree4);
                    
                    // Calculates the time it takes to search the tree
                    startTime = System.nanoTime();
                    thread1.start();
                    thread2.start();
                    thread3.start();
                    thread4.start();
                    thread1.join();
                    thread2.join();
                    thread3.join();
                    thread4.join();
                    endTime = System.nanoTime();

                    // Writes the results to the file
                    try 
                    {
                        FileWriter myWriter = new FileWriter("output2.txt", true);
                        myWriter.write("Using 4 threads for " + numberOfNodes[i] + " nodes \n");
                        myWriter.write("Time taken using 4 threads: " + (endTime - startTime)/100000 + " milliseconds\n");
                        myWriter.write("Height of the tree: " + tree.getHeight());
                        myWriter.write("\nTime taken to search for " + value + " using 4 threads: " + (endTime - startTime)/100000 + " milliseconds\n");
                        myWriter.write("\n");
                        myWriter.close();
                    }
                    catch (IOException e) 
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}