// Import
import java.util.*;

// <----------------------------------------Category---------------------------------------------------->

public class Category 
{
    private int CategoryID;
    private String CategoryName;
    private ArrayList<Product> products = new ArrayList<Product>();

    public Category(int CategoryID, String CategoryName)
    {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
    }

    // <------------------------------------Getters---------------------------------------->

    public int getCategoryID()
    {
        return this.CategoryID;
    }

    public String getCategoryName()
    {
        return this.CategoryName;
    }

    public ArrayList<Product> getProducts()
    {
        return this.products;
    }

    // <------------------------------------Setters---------------------------------------->

    public void setCategoryID(int CategoryID)
    {
        this.CategoryID = CategoryID;
    }

    public void setCategoryName(String CategoryName)
    {
        this.CategoryName = CategoryName;
    }

    public void setProducts(ArrayList<Product> products)
    {
        this.products = products;
    }

    public void addProduct(Product product)
    {
        this.products.add(product);
    }

    public void removeProduct(Product product)
    {
        this.products.remove(product);
    }
}
