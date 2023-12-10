package com.darrenNathanaelBoentaraJBusIO;

import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;

/**
 * This class is used to store the review of the bus
 * @author Darren Nathanael
 * @see Serializable
 */
public class Review extends Serializable
{
    public String date;
    public String desc;

   public Review(int id, String date, String desc)
    {
        this.date = date;
        this.desc = desc;
    }

    public String toString()
    {
        return "Id: " + this.id +", Date: " + this.date +", Desc: " + this.desc +"\n"; 
    }
}
