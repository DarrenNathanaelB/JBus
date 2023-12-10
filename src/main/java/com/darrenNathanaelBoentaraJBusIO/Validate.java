package com.darrenNathanaelBoentaraJBusIO;

import java.util.ArrayList;

/**
 * This class is used to validate the price and the value
 * @author Darren Nathanael
 */

public class Validate
{
    public Validate()
    {

    }

    /**
     * Method that is used for filter
     * @param list The list of Price
     * @param value The value to be filtered
     * @param less The less of the value from the price
     * @return The filtered list
     */
    public static ArrayList<Double> filter (Price[] list, int value, boolean less)
    {
        ArrayList<Double> newArr = new ArrayList<Double>();
        for (Price harga: list){
            if ((less && harga.price <= value) || (!less && harga.price > value)){
                newArr.add(harga.price);
            }
        }
        return newArr;
    }
}
