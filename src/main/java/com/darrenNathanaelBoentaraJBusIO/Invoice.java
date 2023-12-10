package com.darrenNathanaelBoentaraJBusIO;

import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * This class is used to store the invoice information.
 * @author Darren Nathanael
 * @see Serializable
 */
public class Invoice extends Serializable
{
    /**
     * Enum for Bus Rating
     */
    public enum BusRating
    {
    NONE, NEUTRAL, GOOD, BAD
    }

    /**
     * Enum for Payment Status
     */
    public enum PaymentStatus
    {
    FAILED, WAITING, SUCCESS
    }
    
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;

    /**
     * Invoice Constructor
     * @param buyerId id of the buyer
     * @param renterId id of the renter
     */
    protected Invoice(int buyerId, int renterId)
    {
       this.buyerId = buyerId;
       this.renterId = renterId;
       this.time = new Timestamp(System.currentTimeMillis());
       this.rating = BusRating.NONE;
       this.status = PaymentStatus.WAITING;
    }

    /**
     * Invoice Constructor
     * @param buyer account of the buyer
     * @param renter Renter of the renter
     */
    public Invoice(Account buyer, Renter renter)
    {
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String toString()
    {
        return "Id: " + this.id + ", Buyer id: " + this.buyerId + ", Renter id: " + this.renterId + ", Time: " + this.time;
    }
}
