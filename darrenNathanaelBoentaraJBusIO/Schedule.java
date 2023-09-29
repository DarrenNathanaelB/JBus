package darrenNathanaelBoentaraJBusIO;

import java.util.Calendar;
import java.util.Map;
import java.util.LinkedHashMap;

public class Schedule
{
    public Calendar departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Calendar departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        initializaSeatAvailability(numberOfSeats);
    }

    private void initializaSeatAvailability(int numberOfSeats)
    {
        this.seatAvailability = new LinkedHashMap();
        for(int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            seatAvailability.put("IO" + seatNumber, true);
        }
    }
}
