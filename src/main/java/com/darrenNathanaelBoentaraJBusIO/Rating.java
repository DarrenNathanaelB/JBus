package com.darrenNathanaelBoentaraJBusIO;

/**
 * This class is used to represent the rating of the bus
 * @author Darren Nathanael
 */
public class Rating
{
    private long count;
    private long total;

    public Rating()
    {
        this.total = 0;
        this.count = 0;
    }

    /**
     * This method is used to count rating
     * @param rating The rating of the bus
     */
    public void insert (int rating){
        this.total += rating;
        this.count ++;
    }

    /**
     * This method is used to get the total
     * @return The total
     */
    public long getTotal (){
        return this.total;
    }
    /**
     * This method is used to get the count
     * @return The count
     */
    
    public long getCount (){
        return this.count;
    }

    /**
     * This method is used to get the total
     * @return The total
     */
    public double getAverage (){
        if (this.count == 0){
            return 0;
        }
        
        else {
            return this.total / this.count;
        }
    }
    
    public String toString()
    {
        return "Total: " + this.total +", Count: " + this.count +"\n"; 
    }
}
