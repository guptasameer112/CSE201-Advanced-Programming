// Import
import java.util.*;

// <---------------------------Deals--------------------->

public class Deals 
{
    float dealID;
    private Product product1;
    private Product product2;
    private int combinedprice;

    public Deals(float dealID, Product product1, Product product2, int combinedprice)
    {
        this.dealID = dealID;
        this.product1 = product1;
        this.product2 = product2;
        this.combinedprice = combinedprice;
    }

    // <------------------------------------Getters---------------------------------------->

    public float getDealID()
    {
        return this.dealID;
    }

    public Product getProduct1()
    {
        return this.product1;
    }

    public Product getProduct2()
    {
        return this.product2;
    }

    public int getCombinedprice()
    {
        return this.combinedprice;
    }

    // <------------------------------------Setters---------------------------------------->

    public void setDealID(float dealID)
    {
        this.dealID = dealID;
    }

    public void setProduct1(Product product1)
    {
        this.product1 = product1;
    }

    public void setProduct2(Product product2)
    {
        this.product2 = product2;
    }

    public void setCombinedprice(int combinedprice)
    {
        this.combinedprice = combinedprice;
    }

    static void printalldealsk()
    {
        System.out.println("No deals present");
    }
}
