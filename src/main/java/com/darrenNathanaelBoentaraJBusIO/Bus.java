package com.darrenNathanaelBoentaraJBusIO;
import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
/**
 * This class is used to store the bus information.
 * @author Darren Nathanael
 * @see Serializable
 */
public class Bus extends Serializable
{
    public int capacity;
    public List<Facility> facilities;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public List<Schedule> schedules;
    public int accountId;

    public Bus(int accountId, String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival)
    {
        super();
        this.accountId = accountId;
        this.capacity = capacity;
        this.facilities = facilities;
        this.name = name;
        this.price = price;
        this.schedules = new ArrayList<>();
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
    }
    public void addSchedule(Timestamp schedule) {
        try {
            for (Schedule existingSchedule : schedules) {
                if (existingSchedule.departureSchedule.equals(schedule)) {
                    System.out.println("Duplicated Schedule!");
                    return;
                }
            }
            schedules.add(new Schedule(schedule, this.capacity));
        } catch (Exception e) {
            System.out.println("Error Found: " + e.getMessage());
        }
    }
    public String toString()
    {
        return "Id: " + this.id +", Name: " + this.name +", Facility: " + this.facilities +
        ", " + this.price +"Capacity: " + this.capacity + ", Bus Type: " + this.busType +
        "\nDeparture: " + this.departure +"\nArrival: " + this.arrival +"\n";
    }

    public Object write(){
        return this;
    }
    public boolean read(String content){
        return true;
    }
}
