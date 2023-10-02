package darrenNathanaelBoentaraJBusIO;

import java.util.Calendar;
import java.util.Map;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class Schedule
{
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Timestamp departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        initializaSeatAvailability(numberOfSeats);
    }

    private void initializaSeatAvailability(int numberOfSeats)
    {
        seatAvailability = new LinkedHashMap();
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            String sn = seatNumber < 10 ? "0"+seatNumber : ""+seatNumber;
            seatAvailability.put("IO" + sn, true);
        }
    }
    
    public void bookSeat(String seat)
    {
       this.seatAvailability.put(seat, false);    
    }
    
    public boolean isSeatAvailable(String seat)
    {
        if (seatAvailability.containsKey(seat)) {
            return seatAvailability.get(seat);
        } 
        else {
            return false;
        }
    }
    
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
}
