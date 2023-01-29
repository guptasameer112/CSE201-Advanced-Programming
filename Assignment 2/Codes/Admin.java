// Imports
import java.util.*;

/*
AdminFunctionalities
{
    AddCategory();
    DeleteCategory();
    AddProduct();
    DeleteProduct();
    SetDiscount();
    AddGiveaway();
    Back();
}
*/

// <----------------------------------------Admin---------------------------------------------------->

public class Admin 
{
    String username;
    String password;

    static Scanner sc = new Scanner(System.in);

    // <---------------------------------Constructor----------------------------------------->

    public Admin(String username, String password) 
    {
        this.username = username;
        this.password = password;
    }

    // <---------------------------------Getters----------------------------------------->

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    // <---------------------------------Setters----------------------------------------->

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    // <---------------------------------menu----------------------------------------->

    public static void menu() 
    {
        System.out.println("\n<----------------Welcome to the Admin Menu---------------->\n");
        System.out.println("\nPlease choose any one of the following actions:");
        System.out.println("-> 1. Add Category");
        System.out.println("-> 2. Delete Category");
        System.out.println("-> 3. Add Product");
        System.out.println("-> 4. Delete Product");
        System.out.println("-> 5. Set Discount on Product");
        System.out.println("-> 6. Add Giveaway");
        System.out.println("-> 7. Back\n");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("\n<----------------Category Details---------------->\n");
                System.out.println("Number of Categories present: " + Main.categories.size());
                addCategory();
                break;
            case 2:
                if (Main.categories.size() == 0)
                {
                    System.out.println("There are no categories to delete. Please add a category first.");
                    menu();
                }
                else
                {
                    System.out.println("\nEnter Category ID");
                    int CategoryID = sc.nextInt();

                    
                    deleteCategory(CategoryID);
                    System.out.println("Number of Categories present after: " + Main.categories.size());
                    break;
                }
            case 3:
                if (Main.categories.size() == 0)
                {
                    System.out.println("There are no categories to add a product to. Please add a category first.");
                    addCategory();
                }
                else 
                {
                    Main.printallproducts();

                    System.out.println("\nEnter Category ID");
                    int categoryID = sc.nextInt();

                    addProduct(categoryID);
                    break;
                }
            case 4:
                if (Main.categories.size() == 0)
                {
                    System.out.println("There are no categories to delete a product from. Please add a category first.");
                    menu();
                }
                else
                {
                    Main.printallproducts();

                    deleteProduct();
                    System.out.println("Product Deleted Successfully!");

                    break;
                }
            case 5:
                setDiscount();
                break;
            case 6:
                addGiveaway();
                break;
            case 7:
                Main.MainMenu();
                break;
        }
    }

    // <---------------------------------Add Category----------------------------------------->

    public static void addCategory() 
    {
        System.out.println("\n<-------Add a Category----->");
        
        System.out.println("\n-> Enter Category ID:");
        int CategoryID = sc.nextInt();

        // check for duplicate IDs
        for (int i = 0; i < Main.categories.size(); i++)
        {
            if (CategoryID == Main.categories.get(i).getCategoryID())
            {
                System.out.println("Category ID already exists.");
                addCategory();
            }
        }
        
        System.out.println("\n-> Enter Category Name:");
        String name = sc.next();

        Category category = new Category(CategoryID, name);
        Main.categories.add(category);

        System.out.println("\nCategory Added Successfully");

        addProduct(CategoryID);
    }

    // <---------------------------------Delete Category----------------------------------------->

    public static void deleteCategory(int CategoryID) 
    {
        for (int i = 0; i < Main.categories.size(); i++) {
            if (Main.categories.get(i).getCategoryID() == CategoryID) {
                Main.categories.remove(i);
                System.out.println("\nCategory Deleted Successfully");
                menu();
            }
        }
        System.out.println("\nCategory Not Found");
        menu();
    }

    // <---------------------------------Add Product----------------------------------------->

    public static void addProduct(float categoryID) 
    {
        for (int i = 0; i < Main.categories.size(); i++) {
            if (Main.categories.get(i).getCategoryID() == categoryID) {
                System.out.println("\n<-----------Add a Product-------->");
                System.out.println("\nProduct Name:");
                sc.nextLine();
                String name = sc.nextLine();

                System.out.println("\nProduct ID:");
                float productID = sc.nextFloat();

                System.out.println("\nProduct Quantity:");
                int quantity = sc.nextInt();

                System.out.println("\nProduct Price:");
                int price = sc.nextInt();

                Product product = new Product(productID, name, price, quantity);

                Main.categories.get(i).addProduct(product);
                System.out.println("\nProduct Added Successfully\n");
                menu();
            }
        }
        menu();
    }

    // <---------------------------------Delete Product----------------------------------------->

    public static void deleteProduct() 
    {
        System.out.println("\n<---------Delete a Product------->\n");
        System.out.println("\nEnter Category ID:");
        int categoryID = sc.nextInt();

        System.out.println("\nEnter Product ID");
        float productID = sc.nextFloat();

        for (int i = 0; i < Main.categories.size(); i++) 
        {
            if (Main.categories.get(i).getCategoryID() == categoryID) 
            {
                for (int j = 0; j < Main.categories.get(i).getProducts().size(); j++) 
                {
                    if (Main.categories.get(i).getProducts().get(j).getProductID() == productID) 
                    {
                        Main.categories.get(i).getProducts().remove(j);
                        System.out.println("\nProduct Deleted Successfully\n");

                        if (Main.categories.get(i).getProducts().size() == 0) 
                        {
                            Scanner sc = new Scanner(System.in);
                            System.out.println("\nIf you want to add a product, enter 1 or if you want to delete a category, enter 0:");
                            int choice = sc.nextInt();

                            if (choice == 1) 
                            {
                                addProduct(categoryID);
                            } 
                            else if 
                            (choice == 0) 
                            {
                                Main.categories.remove(i);
                                System.out.println("Category Removed.");
                            }
                            else
                            {
                                System.out.println("Invalid Choice. Deleting Category.");
                                Main.categories.remove(i);
                            }
                        }
                        menu();
                    }
                }
            }
            else
            {
                System.out.println("Category not found!");
                menu();
            }
        }
    }

    // <---------------------------------Set Discount----------------------------------------->

    public static void setDiscount() 
    {
        System.out.println("\nEnter Category ID");
        int categoryID = sc.nextInt();

        System.out.println("\nEnter Product ID");
        float productID = sc.nextFloat();
        
        System.out.println("\nEnter Customer Categorywise discounts for the product:");
        System.out.println("\n-> Enter Discount for Regular Customers:");
        int regularDiscount = sc.nextInt();
        System.out.println("\n-> Enter Discount for Prime Customers:");
        int primeDiscount = sc.nextInt();
        System.out.println("\n-> Enter Discount for Elite Customers:");
        int eliteDiscount = sc.nextInt();
        
        for (int i = 0; i < Main.categories.size(); i++) 
        {
            if (Main.categories.get(i).getCategoryID() == categoryID) 
            {
                for (int j = 0; j < Main.categories.get(i).getProducts().size(); j++) 
                {
                    if (Main.categories.get(i).getProducts().get(j).getProductID() == productID) 
                    {
                        Main.categories.get(i).getProducts().get(j).categorydiscounts.put("Regular", regularDiscount);
                        Main.categories.get(i).getProducts().get(j).categorydiscounts.put("Prime", primeDiscount);
                        Main.categories.get(i).getProducts().get(j).categorydiscounts.put("Elite", eliteDiscount);
                        System.out.println("\nDiscounts Set Successfully\n");
                        menu();
                    }
                }
            }
        }

        System.out.println("\nProduct Not Found\n");
        menu();
    }

    // <---------------------------------Add Giveaway----------------------------------------->

    public static void addGiveaway() 
    {
        System.out.println("\n<------------Add a Giveaway----------->");

        Main.printallproducts();

        System.out.println("\nEnter Deal ID");
        int DealID = sc.nextInt();
        
        System.out.println("\nEnter ProductID1:");
        float productID1 = sc.nextFloat();

        System.out.println("\nEnter ProductID2:");
        float productID2 = sc.nextFloat();

        System.out.println("\nEnter Combined Price:");
        int combinedPrice = sc.nextInt();

        for (int i = 0; i < Main.categories.size(); i++) {
            for (int j = 0; j < Main.categories.get(i).getProducts().size(); j++) 
            {
                if (Main.categories.get(i).getProducts().get(j).getProductID() == productID1) 
                {
                    for (int k = 0; k < Main.categories.get(i).getProducts().size(); k++) 
                    {
                        if (Main.categories.get(i).getProducts().get(k).getProductID() == productID2) 
                        {
                            if (combinedPrice < Main.categories.get(i).getProducts().get(j).getPrice() + Main.categories.get(i).getProducts().get(k).getPrice()) 
                            {
                                Deals deal = new Deals(DealID, Main.categories.get(i).getProducts().get(j), Main.categories.get(i).getProducts().get(k), combinedPrice);
                                Main.deals.add(deal);
                                System.out.println("\nDeal Added Successfully\n");
                                menu();
                            }
                            else
                            {
                                System.out.println("\nCombined Price is greater than the sum of the prices of the products\n");
                                menu();
                            }
                        }
                    }
                }
            }
        }
    }


    // <---------------------------------main----------------------------------------->

    public static void main(String[] args) 
    {
        menu();
    }
}

// <---------------------------------End----------------------------------------->