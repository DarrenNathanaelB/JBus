package darrenNathanaelBoentaraJBusIO;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public City city;
    public List<Schedule> schedules;

    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival)
    {
        this.capacity = capacity;
        this.facility = facility;
        this.name = name;
        this.price = price;
        
        this.schedules = new ArrayList<>();
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
    }
    public void addSchedule(Timestamp schedule) {
        try {
            for (Schedule existingSchedule : schedules) {
                if (existingSchedule.departureSchedule.equals(schedule)) {
                    System.out.println("Jadwal Yang Dimasukkan Duplikat!");
                    return;
                }
            }
            schedules.add(new Schedule(schedule, this.capacity));
        } catch (Exception e) {
            System.out.println("Ditemukan Error Ketika Menambahkan Jadwal: " + e.getMessage());
        }
    }
    public String toString()
    {
        return "Id: " + this.id +", Name: " + this.name +", Facility: " + this.facility +
        ", " + this.price +"Capacity: " + this.capacity + ", Bus Type: " + this.busType +
        ", City: " + this.city +"\nDeparture: " + this.departure +"\nArrival: " + this.arrival +"\n"; 
    }

    public Object write(){
        return this;
    }
    public boolean read(String content){
        return true;
    }
}
