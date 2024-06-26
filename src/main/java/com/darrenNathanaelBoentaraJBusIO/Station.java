package com.darrenNathanaelBoentaraJBusIO;

import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;

/**
 * This class is used to store the information of the station
 * @author Darren Nathanael
 * @see Serializable
 */
public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;
    
    public Station(String stationName, City city, String address)
    {
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }

    public String toString()
    {
        return "Id: " + this.id + ", Station Name: " + this.stationName +
        ", City: " + this.city + ", Address: " + this.address;
    }
}
