// Imports
import java.util.*;

/* 
CustomerFunctionalities
{
    browseProducts();
    browseDeals();
    addProductToCart();
    addDealToCart();
    viewCoupons();
    checkAccountBalance();
    viewCart();
    emptyCart();
    checkoutCart();
    upgradeStatus();
    addAmount();
    back();
}
*/

interface CustomerFunctionalities 
{
    public void addProductToCart(float productID);
    public void addDealToCart(int dealID);
    public void viewCoupons();
    public void checkAccountBalance();
    public void viewCart();
    public void emptyCart();
    public void checkoutCart();
    public void upgradeStatus();
    public void addAmount();
}

// <----------------------------------------Customer---------------------------------------------------->

public class Customer implements CustomerFunctionalities
{
    String name;
    String password;
    String email;
    int phone;
    String status;
    double wallet;
    int cartValue = 0;

    static ArrayList<Product> cart = new ArrayList<Product>();
    static ArrayList<Integer> coupons = new ArrayList<Integer>();

    static Scanner sc = new Scanner(System.in);

    public Customer(String name, String password, String email, String status, double wallet) 
    {
        this.name = name;
        this.password = password;
        this.email = email;
        this.status = status;
        this.wallet = wallet;
    }

    // <------------------------------------Getters---------------------------------------->

    public String getName()
    {
        return this.name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getStatus()
    {
        return this.status;
    }

    public double getWallet()
    {
        return this.wallet;
    }

    // <---------------------------------Setters----------------------------------------->

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setWallet(double amount)
    {
        this.wallet = wallet + amount;
    }

    // <---------------------------------Helper Methods----------------------------------------->

    double getdeliverycharge()
    {
        double deliveryCharges = 0;
            if (status == "Elite")
            {
                deliveryCharges = 100;
            }
            else if (status == "Prime")
            {
                deliveryCharges = 100 + 0.02 * cartValue;
            }
            else if (status == "Regular")
            {
                deliveryCharges = 100 + 0.05 * cartValue;
            }
        return deliveryCharges;
    }

    double getSubscriptionsDiscount()
    {
        double discount = 0;
    
        if (status == "Elite")
        {
            discount = 0.1 * cartValue;
        }
        else if (status == "Prime")
        {
            discount = 0.05 * cartValue;
        }
        else if (status == "Regular")
        {
            discount = 0;

        }
        
        return discount;
    }
   
    int getDeliveryDays()
   {
        int days = 0;

        if (status == "Elite")
        {
            days = 2;
        }
        else if (status == "Prime")
        {
            Random r = new Random();
            int low = 3;
            int high = 6;
            days = r.nextInt(high-low) + low;
        }
        else if (status == "Normal")
        {
            Random r = new Random();
            int low = 7;
            int high = 10;
            days = r.nextInt(high-low) + low;
        }

        return days;
   }

    void generateCoupon()
   {
    if (status == "Elite" && cartValue > 5000)
    {
        Random r = new Random();
        int low = 3;
        int high = 4;
        int number_of_coupons = r.nextInt(high-low) + low;
        for (int i = 0; i < number_of_coupons; i++)
        {
            Random r1 = new Random();
            int low1 = 5;
            int high1 = 15;
            int coupon_value = r1.nextInt(high1-low1) + low1;
            coupons.add(coupon_value);
        }
    }
    else if (status == "Prime" && cartValue > 5000)
    {
        Random r = new Random();
        int low = 1;
        int high = 2;
        int number_of_coupons = r.nextInt(high-low) + low;
        for (int i = 0; i < number_of_coupons; i++)
        {
            Random r1 = new Random();
            int low1 = 5;
            int high1 = 15;
            int coupon_value = r1.nextInt(high1-low1) + low1;
            coupons.add(coupon_value);
        }
    }
   }

    void suprise()
   {
    if (status == "Elite")
    {
        Random r = new Random();
        int low = 1;
        int high = Main.categories.size();
        int surprise_product_categoryID = r.nextInt(high-low) + low;
        for (int i = 0; i < Main.categories.size(); i++)
        {
            if (Main.categories.get(i).getCategoryID() == surprise_product_categoryID)
            {
                Random r1 = new Random();
                int low1 = 1;
                int high1 = Main.categories.get(i).getProducts().size();
                int surprise_productID = r1.nextInt(high1-low1) + low1;
                for (int j = 0; j < Main.categories.get(i).getProducts().size(); j++)
                {
                    if (Main.categories.get(i).getProducts().get(j).getProductID() == surprise_productID)
                    {
                        System.out.println("Surprise Product Added: " + Main.categories.get(i).getProducts().get(j).getName());
                        cart.add(Main.categories.get(i).getProducts().get(j));
                        Main.categories.get(i).getProducts().get(j).setQuantity(Main.categories.get(i).getProducts().get(j).getQuantity() - 1);
                    }
                }
            }
        }
    }   
   }
    
    double maximum(double x, double y, double z)
   {
       return z > (x > y ? x : y) ? z : ((x > y) ? x : y);
   }

    // <---------------------------------menu----------------------------------------->

    void menu()
    {
        
        System.out.println("\nPlease choose any one of the following actions");
        System.out.println("-> 1. Browse Products");
        System.out.println("-> 2. Browse Deals");
        System.out.println("-> 3. Add Product to Cart");
        System.out.println("-> 4. Add Deal to Cart");
        System.out.println("-> 5. View Coupons");
        System.out.println("-> 6. Check Account Balance");
        System.out.println("-> 7. View Cart");
        System.out.println("-> 8. Empty Cart");
        System.out.println("-> 9. Checkout Cart");
        System.out.println("-> 10. Upgrade Status");
        System.out.println("-> 11. Add Amount");
        System.out.println("-> 12. Back\n");
        int choice = sc.nextInt();

        switch(choice)
        {
            case 1:
                Main.printallproducts();
                menu();
            case 2:
                Main.printalldeals();
                menu();
                break;
            case 3:
                Main.printallproducts();
                System.out.println("\nEnter the product ID:");
                float productID = sc.nextFloat();
                addProductToCart(productID);
                break;
            case 4:
                Main.printalldeals();
                System.out.println("\nEnter the deal ID:");
                int dealID = sc.nextInt();
                addDealToCart(dealID);
                break;
            case 5:
                viewCoupons();
                break;
            case 6:
                checkAccountBalance();
                break;
            case 7:
                viewCart();
                break;
            case 8:
                emptyCart();
                break;
            case 9:
                checkoutCart();
                break;
            case 10:
                upgradeStatus();
                break;
            case 11:
                addAmount();
                break;
            case 12:
                Main.EnterAsCustomer();
                break;
            default:
                System.out.println("Invalid choice\n");
                menu();
            }
    }

    // <---------------------------------add product to cart----------------------------------------->

    public void addProductToCart(float productID)
    {
        System.out.println("\nEnter the quantity:");
        int quantity = sc.nextInt();

        for (int i = 0; i < Main.categories.size(); i ++)
        {
            for (int j = 0; j < Main.categories.get(i).getProducts().size(); j++)
            {
                if (Main.categories.get(i).getProducts().get(j).getProductID() == productID)
                {
                    if (Main.categories.get(i).getProducts().get(j).getQuantity() >= quantity)
                    {
                        cart.add(Main.categories.get(i).getProducts().get(j));
                        cartValue += Main.categories.get(i).getProducts().get(j).getPrice() * quantity;
                        Main.categories.get(i).getProducts().get(j).setQuantity(Main.categories.get(i).getProducts().get(j).getQuantity() - quantity);
                        System.out.println("\nProduct added to cart");
                        menu();
                    }
                    else
                    {
                        System.out.println("\n Not enough quantity");
                        menu();
                    }
                }
            }
        }

        System.out.println("\nProduct not found\n");
        menu(); 
    }

    // <---------------------------------add deal to cart----------------------------------------->

    public void addDealToCart(int dealID)
    {
        for (Deals deal : Main.deals)
        {
            if (deal.getDealID() == dealID)
            {
                cart.add(deal.getProduct1());
                cart.add(deal.getProduct2());
                // decrease quantity of products in deal
                deal.getProduct1().setQuantity(deal.getProduct1().getQuantity() - 1);
                deal.getProduct2().setQuantity(deal.getProduct2().getQuantity() - 1);

                System.out.println("\nDeal added to cart\n");
                cartValue += deal.getCombinedprice();
                menu();
            }
        }
        System.out.println("\nDeal not found\n");
        menu();
    }

    // <---------------------------------view coupons----------------------------------------->

    public void viewCoupons()
    {
        System.out.println("\nYour coupons are:");
        for (int i = 0; i < coupons.size(); i++)
        {
            System.out.println("->" + coupons.get(i));
        }
        menu();
    }

    // <---------------------------------check account balance----------------------------------------->

    public void checkAccountBalance()
    {
        System.out.println("\nYour account balance is: " + getWallet());
        menu();
    }

    // <---------------------------------view cart----------------------------------------->

    public void viewCart()
    {
        System.out.println("\nYour cart contains the following items: ");
        for(int i = 0; i < cart.size(); i++)
        {
            System.out.println("\n-> Product: " + cart.get(i).getName() + " " + "\nPrice: " + cart.get(i).getPrice());
        }
        menu();
    }

    // There is no function to remove an item from cart :(

    // <---------------------------------empty cart----------------------------------------->

    public void emptyCart()
    {
        System.out.println("\nClearing cart...");
        cart.clear();
        System.out.println("\nYour cart is now empty");
        menu();
    }

    // <---------------------------------checkout cart----------------------------------------->

    public void checkoutCart()
    {
        if (cartValue <= wallet && cart.size() != 0)
        {
            double deliveryCharges = getdeliverycharge();
            // double subscriptiondiscount = getSubscriptionsDiscount();
            int days = getDeliveryDays();
            // int statusbaseddiscount = 0;
            double totalAmount = 0;

            System.out.println("\nYour order is placed successfully. Details:");
            
            // for (int i = 0; i < cart.size(); i++)
            // {
            //     System.out.println("\n-> ProductID: " + cart.get(i).getProductID() + " " + "Price: " + cart.get(i).getPrice());
            // }
            
            // System.out.println("\nAvailable coupons:");
            // Collections.sort(coupons);
            // for (int i = 0; i < coupons.size(); i++)
            // {
            //     System.out.println("-> " + coupons.get(i));
            // }
            
            // suprise();
            
            // for (int i = 0; i < cart.size(); i++)
            // {
            //     if (statusbaseddiscount < cart.get(i).categorydiscounts.get(status))
            //     {
            //         statusbaseddiscount = cart.get(i).categorydiscounts.get(status);
            //     }
            // }
            
            // if (coupons.size() == 0)
            // {
            //     totalAmount = cartValue - maximum(subscriptiondiscount, (double)Collections.max(coupons), (double)statusbaseddiscount) + deliveryCharges;
            // }
            // else
            // {
            //     generateCoupon();
            //     totalAmount = cartValue - maximum(subscriptiondiscount, 0, (double)statusbaseddiscount) + deliveryCharges;
            // }

            // totalAmount = cartValue - maximum(subscriptiondiscount, 0, (double)statusbaseddiscount) + deliveryCharges;
            totalAmount = cartValue + deliveryCharges;
            wallet -= totalAmount;
            
            System.out.println("\nTotal amount: " + (totalAmount));
            System.out.println("\nYour account balance is: " + wallet);
            System.out.println("\nYour order will arrive within " + days + " days");
            System.out.println("\nThank you for shopping with us");
        }
        else
        {
            System.out.println("\nInsufficient balance!! Please try again.\n");
        }
        menu();
    }

    // <---------------------------------upgrade status----------------------------------------->

    public void upgradeStatus()
    {
        System.out.println("\nCurrent Status: " + status);
        System.out.println("\nEnter the new status:");
        sc.nextLine();
        String newStatus = sc.nextLine();
        
        if (newStatus.equals("Prime"))
        {
            if (status.equals("Normal"))
            {
                if (wallet >= 200)
                {
                    wallet = wallet - 200;
                    System.out.println("\nMoney deducted from your account");
                    status = newStatus;
                    System.out.println("\nStatus upgraded to Prime");
                }
                else
                {
                    System.out.println("\nNot enough balance");
                }
            }
        }

        else if (newStatus.equals("Elite"))
        {
            if (status.equals("Normal"))
            {
                if (wallet >= 300)
                {
                    wallet = wallet - 300;
                    status = newStatus;
                    System.out.println("\nStatus upgraded to Elite");
                }
                else
                {
                    System.out.println("\nNot enough balance");
                }
            }
            else if (status.equals("Prime"))
            {
                if (wallet >= 100)
                {
                    wallet = wallet - 100;
                    status = newStatus;
                    System.out.println("\nStatus upgraded to Elite");
                }
                else
                {
                    System.out.println("\nNot enough balance");
                }
            }
        }
        else
        {
            System.out.println("\nInvalid status\n");
        }
        System.out.println("\nYour current status is: " + status);
        menu();
    }

    // <---------------------------------addAmount----------------------------------------->

    public void addAmount()
    {
        System.out.println("\nEnter the amount to be added: ");
        int amount = sc.nextInt();
        setWallet(amount);
        System.out.println("\nAmount added successfully");
        menu();
    }

    public static void main(String[] args) 
    {
        
    }
}
