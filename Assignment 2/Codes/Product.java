// Import
import java.util.*;

// <----------------------------------------Product---------------------------------------------------->

public class Product 
{
    private float productID;
    private String productname;
    private int price;
    private int quantity;

    HashMap<String, Integer> categorydiscounts = new HashMap<String, Integer>();

    public Product(float productID, String name, int price, int quantity) 
    {
        this.productID = productID;
        this.productname = name;
        this.price = price;
        this.quantity = quantity;
    }

    // <------------------------------------Getters---------------------------------------->

    public float getProductID() 
    {
        return productID;
    }
    
    public String getName() 
    {
        return productname;
    }
    
    public int getPrice() 
    {
        return price;
    }
    
    public int getQuantity() 
    {
        return quantity;
    }
    
    // <------------------------------------Setters---------------------------------------->

    public void setProductID(float productID) 
    {
        this.productID = productID;
    }
    
    public void setName(String name) 
    {
        this.productname = name;
    }
    
    
    public void setPrice(int price) 
    {
        this.price = price;
    }
    
    
    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }
}

// <----------------------------------------End---------------------------------------------------->