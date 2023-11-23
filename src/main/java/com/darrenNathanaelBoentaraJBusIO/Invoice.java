package com.darrenNathanaelBoentaraJBusIO;

import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;

import java.sql.Timestamp;

public class Invoice extends Serializable
{
    public enum BusRating
    {
    NONE, NEUTRAL, GOOD, BAD
    }
    
    public enum PaymentStatus
    {
    FAILED, WAITING, SUCCESS
    }
    
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    protected Invoice(int buyerId, int renterId)
    {
       this.buyerId = buyerId;
       this.renterId = renterId;
       this.time = new Timestamp(System.currentTimeMillis());
       this.rating = BusRating.NONE;
       this.status = PaymentStatus.WAITING;
    }

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
