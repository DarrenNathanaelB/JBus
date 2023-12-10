package com.darrenNathanaelBoentaraJBusIO;

import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;

/**
 * This class is used to store the information of the voucher
 * @author Darren Nathanael
 * @see Serializable
 */
public class Voucher extends Serializable
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(int id, String name, int code, Type type, double minimum, double cut)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }

    /**
     * method that is used to know if the voucher is used or not
     * @return true if it is used, false if it is not
     */
    public boolean isUsed()
    {
        return this.used;
    }
    
    public boolean canApply(Price price)
    {
        if (price.price > this.minimum && this.used == false){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * method that is used to apply the voucher
     * @param price price of the bus ticket
     * @return price after the voucher is applied
     */
    public double apply (Price price)
    {
        this.used = true;
        
        if(this.type == Type.DISCOUNT){
                if(this.cut >= 100){
                    return 0;
                }
                else {
                    return ((double)(100 - this.cut) / 100) * price.price;
                }
        }
        else{
                if (this.cut > price.price){
                return 0;
                }
                else {
                return ((double)(price.price - this.cut));
                }
        }
    }
    public Object write(){
        return this;
    }

    public boolean read(String content){
        return true;
    }
}
