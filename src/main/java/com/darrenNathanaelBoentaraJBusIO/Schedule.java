package com.darrenNathanaelBoentaraJBusIO;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;


/**
 * This class is used to store the schedule of the bus
 * @author Darren Nathanael
 */
public class Schedule
{
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Timestamp departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        initializaSeatAvailability(numberOfSeats);
    }

    /**
     * This method is used initialize the seat avaibility of the bus
     */
    private void initializaSeatAvailability(int numberOfSeats)
    {
        seatAvailability = new LinkedHashMap();
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            String sn = seatNumber < 10 ? "0"+seatNumber : ""+seatNumber;
            seatAvailability.put("IO" + sn, true);
        }
    }

    /**
     * This method is used to book the bus seat
     */
    public void bookSeat(String seat)
    {
       this.seatAvailability.put(seat, false);    
    }
    /**
     * This method is used to check if the seat is available or not
     */
    public boolean isSeatAvailable(String seat)
    {
        if (seatAvailability.containsKey(seat)) {
            return seatAvailability.get(seat);
        }
        else {
            return false;
        }
    }

    /**
     * This method is used to check if the seat is available or not
     */
    public boolean isSeatAvailable(List<String> seats)
    {
        for (String seat : seats){
            if (!seatAvailability.get(seat) || !seatAvailability.containsKey(seat)) {
                return false;
            }
        }
        return true;
    }

    public void bookSeat(List<String> seats)
    {
        for (String seat : seats) {
            if (seatAvailability.containsKey(seat)) {
                seatAvailability.put(seat, false);
            }
        }
    }

    /**
     * This method is used to print the schedule and the seat information
     */
    public void printSchedule(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        
        // Print tanggal keberangkatan
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        
        // Print daftar kursi dan ketersediaan kursinya
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        
        // Create a list of seats and sort them numerically
        int maxSeatsPerRow = 4; // Assuming there are 4 seats per row
        int currentSeat = 1;
        
        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }
    @Override
    public String toString() {
        int totalSeats = seatAvailability.size();
        long bookedSeats = seatAvailability.values().stream().filter(b -> !b).count();
        return "Schedule: " + departureSchedule +
                "\nOccupied: " + bookedSeats + "/" + totalSeats;
    }
}
