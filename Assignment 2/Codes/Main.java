/*
    Oh Hi there again!
    I'm Sameer Gupta, Tag No.2021093.
    Getting a little tired of this, sigh.
*/


// Imports
import java.util.*;

/*
MainMenu()
{
    EnterAsAdmin();
    ExploreTheProductCatalg();
    ShowAvailableDeals();
    EnterAsCustomer();
    Exit();
}
*/

// <----------------------------------------Main---------------------------------------------------->

public class Main 
{
    static ArrayList<Admin> admins = new ArrayList<Admin>();
    static ArrayList<Customer> customers = new ArrayList<Customer>();
    static ArrayList<Category> categories = new ArrayList<Category>();
    static ArrayList<Deals> deals = new ArrayList<Deals>();

    static Scanner sc = new Scanner(System.in);


    static void printallproducts()
    {
        System.out.println("\nCategories Present:");
        for (int i = 0; i < Main.categories.size(); i++) 
        {
            System.out.println("    " + (Main.categories.get(i).getCategoryID()) + " " + Main.categories.get(i).getCategoryName());
            System.out.println("        Products Present:");
            for (int j = 0; j < Main.categories.get(i).getProducts().size(); j++) 
            {
                System.out.println("            " + (Main.categories.get(i).getProducts().get(j).getProductID()) + " " + Main.categories.get(i).getProducts().get(j).getName());
            }
        };
    }

    static void printalldeals()
    {
        System.out.println("\nDeals Present:");
        for (int i = 0; i < Main.deals.size(); i++)
        {
            System.out.println("-> " + Main.deals.get(i).getDealID() + ". " + Main.deals.get(i).getProduct1() + "+" + Main.deals.get(i).getProduct2());
        }
    }

    // <--------------------------------Main Choice------------------------------------------->

    public static void MainMenu()
    {
        System.out.println("\nPlease choose any one of the following actions\n");
        System.out.println("-> 1. Enter as Admin");
        System.out.println("-> 2. Explore the Product Catalog");
        System.out.println("-> 3. Show Available Deals");
        System.out.println("-> 4. Enter as Customer");
        System.out.println("-> 5. Exit the application\n");

        int choice = sc.nextInt();

        switch(choice)
        {
            case 1:
                if (admins.size() == 0)
                {
                    System.out.println("No Admins are registered yet. Please register first");
                }
                EnterAsAdmin();
                break;
            case 2:
                if (categories.size() == 0)
                {
                    System.out.println("\nNo Categories were found. Try again later.");
                    MainMenu();
                }
                else
                {
                    System.out.println("\n<--------------Catalog------------->\n");
                    ExploreTheProductCatalog();
                    break;
                }
                
            case 3:
                if (deals.size() == 0)
                {
                    System.out.println("\nNo deals were found. Try again later.");
                    MainMenu();
                }
                else
                {
                    System.out.println("\nHere are our available deals:\n\n");
                    ShowAvailableDeals();
                    break;
                }
            case 4:
                EnterAsCustomer();
                break;
            case 5:
                System.out.println("Thank you for using our application. Have a nice day!");
                System.exit(0);
            default:
                System.out.println("Invalid Choice, Please try again.");
                MainMenu();
        }
    }

    // <---------------------------------EnterAsAdmin----------------------------------------->

    public static void EnterAsAdmin()
    {
        System.out.println("\n<------Welcome to the Admin Login Page------>\n");

        System.out.println("\nEnter Username:");
        sc.nextLine();
        String username = sc.nextLine();

        System.out.println("\nEnter Password:");
        String password = sc.nextLine();

        // Checking for the admins
        for (int i = 0; i < admins.size(); i++)
        {
            if (admins.get(i).getUsername().equals(username) && admins.get(i).getPassword().equals(password))
            {
                System.out.println("\nWelcome " + username + "!!\n");
                Admin.menu();
            }
        }

        System.out.println("\nInvalid Username or Password. Please Try Again.\n");
        MainMenu();
    }

    // <---------------------------------ExploreTheProductCatalog----------------------------------------->

    public static void ExploreTheProductCatalog()
    {
        for (int i = 0; i < categories.size(); i++)
        {
            System.out.println("\nCategory " + (i+1) + " => " + categories.get(i).getCategoryName() + ":");
            for (int j = 0; j < categories.get(i).getProducts().size(); j++)
            {
                System.out.println("-> Product " + categories.get(i).getProducts().get(j).getProductID() + ": " + categories.get(i).getProducts().get(j).getName());
            }
        }
        System.out.println("<-----------End of Catalog----------->\n");
        MainMenu();
    }

    // <---------------------------------ShowAvailableDeals----------------------------------------->

    public static void ShowAvailableDeals()
    {
        System.out.println("<---------------Welcome to the Deals Section-------------->\n");
        
        for (int i = 0; i < deals.size(); i++)
        {
            System.out.println("->" + deals.get(i).getProduct1().getName() + " and " + deals.get(i).getProduct2().getName() + " for $" + deals.get(i).getCombinedprice());
        }

        System.out.println("<----------------End of Deals Section------------------->\n");
        MainMenu();
    }

    // <---------------------------------EnterAsCustomer----------------------------------------->

    public static void EnterAsCustomer()
    {
        System.out.println("\n<------Welcome to the Customer Login Page------>\n");
        System.out.println("-> 1. Sign up");
        System.out.println("-> 2. Log in");
        System.out.println("-> 3. Back\n");
        int choice = sc.nextInt();

        switch(choice)
        {
            case 1:
                System.out.println("\nEnter Name:");
                sc.nextLine();
                String Sname = sc.nextLine();
                
                System.out.println("\nEnter Password:");
                String Spassword = sc.nextLine();
                
                System.out.println("\nEnter Age:");
                int Sage = sc.nextInt();

                System.out.println("\nEnter Email:");
                sc.nextLine();
                String Semail = sc.nextLine();

                System.out.println("\nEnter Phone Number:");
                int Sphone = sc.nextInt();

                customers.add(new Customer(Sname, Spassword, Semail, "Normal", 1000));

                System.out.println("\nCustomer added successfully\n");

                EnterAsCustomer();

            case 2:
                if (customers.size() == 0)
                {
                    System.out.println("No Customers are registered yet. Please register first.");
                    EnterAsCustomer();
                }
                else
                {
                    System.out.println("<----------------------------------------Welcome to customer page---------------------------------------->");
                    System.out.println("\nEnter Name:");
                    sc.nextLine();
                    String Lname = sc.nextLine();
    
                    System.out.println("\nEnter Password:");
                    String Lpassword = sc.nextLine();
    
                    for (int i = 0; i < customers.size(); i++)
                    {
                        if (customers.get(i).getName().equals(Lname) && customers.get(i).getPassword().equals(Lpassword))
                        {
                            System.out.println("\nWelcome " + Lname + "!!\n");
                            customers.get(i).menu();
                            // Customer.menu();
                        }
                    }

                    System.out.println("\nInvalid Name or Password. Please Try Again.\n");
                    EnterAsCustomer();
                }

            case 3:
                MainMenu();
        }
    }

    // <---------------------------------main----------------------------------------->

    public static void main(String[] args) 
    {
        System.out.println("\nWELCOME TO FLIPZONE");
        
        Admin admin = new Admin("admin", "admin");
        admins.add(admin);

        MainMenu();
    }
}

// <----------------------------------------End---------------------------------------------------->
