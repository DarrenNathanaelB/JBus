package com.darrenNathanaelBoentaraJBusIO;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.List;

public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public List<String> busSeats;

    public Payment(int buyerId, int renterId, int busId, List<String> busSeats, Timestamp departureDate)
    {
        super(buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeats = busSeats;
    }

    public Payment(Account buyer, Renter renter, int busId, List<String> busSeats, Timestamp departureDate)
    {
        super(buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeats = busSeats;
    }

    /*public String toString()
    {
        return "Id: " + this.id +" Buyer Id: " + this.buyerId + " Renter Id: " + this.renterId + " time: "+ this.time + " Bus Id: " + this.busId + " date: " + this.departureDate + " seat: " + this.busSeat;
    }*/

    public int getBusId()
    {
        return busId;
    }

    /*public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus)
    {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule)){
                return schedule.isSeatAvailable(seat);
            }
        }
        return false;
    }*/

    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus)
    {
            for (Schedule schedule : bus.schedules){
                if (schedule.departureSchedule.equals(departureSchedule)){
                    if (schedule.isSeatAvailable(seat)) {
                    schedule.bookSeat(seat);
                    return true;
                }
            }
        }
        return false;
    }

    public String getDepartureInfo()
    {
        SimpleDateFormat SDFormat
            = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss");
        String departDate = SDFormat.format(departureDate.getTime());
        return "Id: " + this.id +" Buyer Id: " + this.buyerId + " Renter Id: " + this.renterId + " Bus Id: " + this.busId + " Departure Date: " + departDate + " Seat: " + this.busSeats;
    }

    public String getTime(){
        SimpleDateFormat SDFormat
            = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String currDate = SDFormat.format(departureDate.getTime());
        return "Payment Time: " + currDate;
    }

    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule) && schedule.isSeatAvailable(seat)) {
                return schedule;
            }
        }
        return null;
    }

    public static List<Schedule> availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus) {
        List<Schedule> availableSchedules = new ArrayList<>();
        for (String seat : seats) {
            Schedule schedule = availableSchedule(departureSchedule, seat, bus);
            if (schedule != null) {
                availableSchedules.add(schedule);
            }
        }
        return availableSchedules;
    }

    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus) {
        boolean allBooked = true;
        for (String seat : seats) {
            boolean booked = makeBooking(departureSchedule, seat, bus);
            if (!booked) {
                allBooked = false;
            }
        }
        return allBooked;
    }
}
