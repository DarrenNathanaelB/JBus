package darrenNathanaelBoentaraJBusIO;

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

    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival)
    {
        super(id);
        this.capacity = capacity;
        this.facility = facility;
        this.name = name;
        this.price = price;
        
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
    }
    
    public String toString()
    {
        return "Id: " + this.id +", Name: " + this.name +", Facility: " + this.facility +
        ", " + this.price +"Capacity: " + this.capacity + ", Bus Type: " + this.busType +
        ", City: " + this.city +"\nDeparture: " + this.departure +"\nArrival: " + this.arrival +"\n"; 
    }
}
