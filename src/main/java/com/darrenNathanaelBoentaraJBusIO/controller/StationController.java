package com.darrenNathanaelBoentaraJBusIO.controller;

import com.darrenNathanaelBoentaraJBusIO.Bus;
import com.darrenNathanaelBoentaraJBusIO.City;
import com.darrenNathanaelBoentaraJBusIO.Station;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonAutowired;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class handles HTTP requests related to station operations,
 * such as creating new stations and retrieving all stations.
 *
 * @author Darren Nathanael
 */
@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {
    public static @JsonAutowired(value = Station.class, filepath = "src\\main\\java\\com\\darrenNathanaelBoentaraJBusIO\\json\\station.json") JsonTable<Station> stationTable;
    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }

    /**
     * Creates a new station with the provided details.
     *
     * @param stationName The name of the station.
     * @param city         The city where the station is located.
     * @param address      The address of the station.
     * @return A response indicating the success or failure of the station creation.
     */
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @RequestParam String stationName,
            @RequestParam String city,
            @RequestParam String address
    ) {
        try {
            // Validate parameters
            if (stationName.isBlank() || city.isBlank() || address.isBlank()) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
            }

            // Validate city as a valid enum value
            City cityEnum = City.valueOf(city.toUpperCase());

            // Create a new station using the provided details
            Station newStation = new Station(stationName, cityEnum, address);

            // Add the new station to the stationTable
            stationTable.add(newStation);

            //Success response message
            return new BaseResponse<>(true, "Station added successfully", newStation);
        } catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return new BaseResponse<>(false, "Invalid city value", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "An error occurred while adding the station", null);
        }
    }
    /**
     * Retrieves a list of all stations.
     *
     * @return A list containing all stations.
     */
    @GetMapping("/getAll")
    public List<Station> getAllStation() { return getJsonTable();}
}
