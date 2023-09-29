package darrenNathanaelBoentaraJBusIO;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate;
    public String busSeat;

    public Payment(int id, int buyerId, int renterId, int busId, String busSeat)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.busSeat = busSeat;
    }

    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat)
    {
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
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
    
    public String getDepartureInfo()
    {
        departureDate.add(Calendar.DAY_OF_MONTH, 2);
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
