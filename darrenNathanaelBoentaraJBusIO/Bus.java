package darrenNathanaelBoentaraJBusIO;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Bus extends Serializable implements FileParser
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

    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival)
    {
        super(id);
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
    
    public void addSchedule (Calendar calendar){
        Schedule newSched = new Schedule(calendar, capacity);
        schedules.add(newSched);
    }
    
    public void printSchedule(Schedule schedule){
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        System.out.println(format.format(schedule.departureSchedule.getTime()));
        System.out.println(schedule.seatAvailability);
    }
    
    public String toString()
    {
        return "Id: " + this.id +", Name: " + this.name +", Facility: " + this.facility +
        ", " + this.price +"Capacity: " + this.capacity + ", Bus Type: " + this.busType +
        ", City: " + this.city +"\nDeparture: " + this.departure +"\nArrival: " + this.arrival +"\n"; 
    }
    
    @Override
    public Object write(){
        return this;
    }
    @Override
    public boolean read(String content){
        return true;
    }
}
