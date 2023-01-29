/*
    Oh Hi Evaluator!
    I'm Sameer Gupta, Tag No.2021093.
    How are you?
    Okay Okay, Jeez!, Moving on with the program.
*/

// Imports
import java.util.Scanner;

// <----------------------------------------Main---------------------------------------------------->

public class Main 
{

    static Scanner choiceobj = new Scanner(System.in);

    // <--------------------------------Main Choice------------------------------------------->

    public static void MainChoice()
    {
        System.out.println("\n<--------------------------------Main Choice------------------------------------------->\n");
        System.out.println("1. Enter the Application\n2. Exit the Application");
        System.out.print("Input: ");
        int choice = choiceobj.nextInt();

        if (choice == 1)
        {
            EnterApplication();
        }
        else
        {
            ExitApplication();
        }
    }

    // <--------------------------------Enter Application------------------------------------------->

    public static void EnterApplication()
    {
        System.out.println("\n<--------------------------------Enter Application------------------------------------------->\n");
        System.out.println("\nEntering application...");

        System.out.println("Choose the mode you want to Enter in:");
        System.out.println("1. Enter as Student Mode\n2. Enter as Company Mode\n3. Enter as Placement Cell Mode\n4. Return to Main Application");
        System.out.print("Input: ");
        int mode = choiceobj.nextInt();
        switch(mode)
        {
            case 1:
                Student.menu();
                break;
            case 2:
                Company.menu();
                break;
            case 3:
                PlacementCell.menu();
                break;
            case 4:
                MainChoice();
                break;
            default:
                System.out.println("Invalid Input, Try again: ");
                break;
        }

    }

    // <--------------------------------Exit Application------------------------------------------->

    public static void ExitApplication()
    {
        System.out.println("\n<--------------------------------Exit Application------------------------------------------->\n");
        System.out.println("Thanks for using FutureBuilder! Not really, it was a *uhm uhm* assignment.");
        System.exit(0);
    }

    
    // <--------------------------------main------------------------------------------->

    public static void main(String[] args) 
    {
        System.out.println("\nWelcome to FutureBuilder");

        MainChoice();

        choiceobj.close();
    }
}

// <----------------------------------------End---------------------------------------------------->