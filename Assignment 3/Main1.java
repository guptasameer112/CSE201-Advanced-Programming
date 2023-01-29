import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// A Generic class that contains the OddEvenSort algorithm
class OddEven <T>
{
    // A Generic method that sorts the array using the OddEvenSort algorithm
    public static <T> void oddEvenSort(ArrayList <T> array, int begin, int end)
    {
        boolean sorted = false;

        while (!sorted) 
        {
            sorted = true;

            // Sorts the odd indexed elements
            for (int i = begin + 1; i < end; i += 2) 
            {

                if ((int)array.get(i - 1) > (int)array.get(i))
                {
                    Collections.swap(array, i - 1, i);
                    sorted = false;
                }

            }

            // Sorts the even indexed elements
            for (int i = begin + 2; i < end; i += 2) 
            {
                if ((int)array.get(i - 1) > (int)array.get(i))
                {
                    Collections.swap(array, i - 1, i);
                    sorted = false;
                }
            }
        }
    }

    // To-String method
    public String toString() 
    {
        return "OddEven";
    }
}

public class Main1 implements Runnable
{
    private ArrayList <Integer> array;
    private int begin, end;
    
    // Constructor
    public Main1(ArrayList <Integer> array, int begin, int end)
    {
        this.array = array;
        this.begin = begin;
        this.end = end;
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        int[] NumberOfStudents = {1, 10, 100, 1000, 10000};
        
        // For each number of students 
        for (int i = 0; i < NumberOfStudents.length; i++)
        {
            ArrayList <Integer> array = new ArrayList<Integer>();

            // Generate a random array of numbers
            for (int j = 0; j < NumberOfStudents[i]; j++)
            {
                array.add((int)(Math.random() * 10));
            }

            ArrayList <Integer> array1 = new ArrayList<Integer>(array); 

            for (int j = 0; j < NumberOfStudents[i]; j++)
            {
                array1.add(array.get(j));
            }

            // Print the array before sorting
            // System.out.println("Before sorting: " + array);

            // Calculate the time taken to sort the array using the OddEvenSort algorithm without threads
            long startTime = System.nanoTime();
            OddEven.oddEvenSort(array, 0, NumberOfStudents[i]);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);

            // Print the array after sorting using the OddEvenSort algorithm without threads
            // System.out.println("Non Parallelized Sorting");
            // System.out.println("Array: " + array);
            

            // Create threads to sort the array using the OddEvenSort algorithm with parallelization
            // Sorts the first half of the array
            Thread t1 = new Thread(new Main1(array1, 0, NumberOfStudents[i] / 2));
            
            // Sorts the second half of the array
            Thread t2 = new Thread(new Main1(array, (NumberOfStudents[i] / 2), NumberOfStudents[i]));
            Thread t3 = new Thread(new Main1(array, 0, (NumberOfStudents[i] / 2)));
            
            // Calculate the time taken to sort the array using the OddEvenSort algorithm with threads
            long startTime1 = System.nanoTime();
            t1.start();
            t2.start();
            t3.start();
            t1.join();
            t2.join();
            t3.join();
            long endTime1 = System.nanoTime();
            long duration1 = (endTime1 - startTime1);

            // Print the array after sorting using the OddEvenSort algorithm with threads
            // System.out.println("Parallelized Sorting");
            // System.out.println("Array: " + array);

            // Check if the arrays are equal or not            
            // if (Arrays.equals(array1, array2))
            // {
            //     System.out.println("The arrays are equal");
            // }
            // else
            // {
            //     System.out.println("The arrays are not equal");
            // }

            // Write the results to a file
            try 
            {
                // Creating a file object
                File myObj = new File("output1.txt");
                if (myObj.createNewFile()) 
                {
                  System.out.println("File created: " + myObj.getName());
                }
            } 
            catch (IOException e) 
            {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            try 
            {
                // Creating a FileWriter object and writing the results to the file
                FileWriter myWriter = new FileWriter("output1.txt", true);
                myWriter.write("Number of Students: " + NumberOfStudents[i] + "\n");
                myWriter.write("Time taken for non-parallelized sorting: " + duration + " nanoseconds" + "\n");
                myWriter.write("Time taken for parallelized sorting: " + duration1 + " nanoseconds" + "\n");
                myWriter.write("Speedup: " + (double)duration / duration1 + "\n");
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

    @Override 
    public void run()
    {
        // Sorts the array using the OddEvenSort algorithm
        OddEven.oddEvenSort(array, begin, end);
    }
}