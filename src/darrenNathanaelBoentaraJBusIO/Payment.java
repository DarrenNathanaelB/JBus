package darrenNathanaelBoentaraJBusIO;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;

    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }

    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate)
    {
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    /*public String toString()
    {
        return "Id: " + this.id +" Buyer Id: " + this.buyerId + " Renter Id: " + this.renterId + " time: "+ this.time + " Bus Id: " + this.busId + " date: " + this.departureDate + " seat: " + this.busSeat;
    }*/
    
    public int getBusId()
    {
        return busId;
    }
    
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus)
    {
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule)){
                return schedule.isSeatAvailable(seat);
            }
        }
        return false;
    }
    
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus)
    {
        if (isAvailable(departureSchedule, seat, bus)){
            for (Schedule schedule : bus.schedules){
                if (schedule.departureSchedule.equals(departureSchedule)){
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
        return "Id: " + this.id +" Buyer Id: " + this.buyerId + " Renter Id: " + this.renterId + " Bus Id: " + this.busId + " Departure Date: " + departDate + " Seat: " + this.busSeat;
    }
    
    public String getTime(){
        SimpleDateFormat SDFormat
            = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        String currDate = SDFormat.format(departureDate.getTime());
        return "Payment Time: " + currDate;
    }
}
