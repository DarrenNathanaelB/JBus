package com.darrenNathanaelBoentaraJBusIO;

/**
 * This class is used to store the bus price information
 * @author Darren Nathanael
 */
public class Price
{
    public double rebate;
    public double price;
    //public int discount;
    
    public Price (double price)
    {
        this.price = price;
        //this.discount = 0;
        this.rebate = 0;
    }
    
    /*public Price (double price, int discount)
    {
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }*/
    
    public Price (double price, double rebate)
    {
        this.price = price;
        this.rebate = rebate;
        //this.discount = 0;
    }

    /**
     * This method is used to get the price of a room
     * @return String of price and discount
     */
    public String toString()
    {
        return "Price: " + this.price +", Rebate: " + this.rebate +"\n"; 
    }
    
    /*private double getDiscountedPrice()
    {
        if (this.discount >= 100){
            return 0.0;
        }
        
        else {
            return ((double)(100 - this.discount) / 100) * this.price;
        }
    }
    
    private double getRebatedPrice()
    {
        if (this.rebate > this.price){
            return 0;
        }
        
        else {
            return this.price - this.rebate;
        }
    }*/
}
