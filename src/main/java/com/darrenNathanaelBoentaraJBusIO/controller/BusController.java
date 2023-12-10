package com.darrenNathanaelBoentaraJBusIO.controller;

import com.darrenNathanaelBoentaraJBusIO.*;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonAutowired;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * This class is responsible for handling HTTP requests related to bus operations,
 * such as adding schedules, creating buses, and retrieving bus information.
 *
 * @author Darren Nathanael
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus>{
    @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\darrenNathanaelBoentaraJBusIO\\json\\bus_db.json")
    public static JsonTable<Bus> busTable;

    /**
     * Gets the JSON table associated with the Bus entity.
     *
     * @return The JSON table for the Bus entity.
     */
    public JsonTable<Bus> getJsonTable(){
        return busTable;
    }

    /**
     * Adds a schedule to a bus.
     *
     * @param busId The ID of the bus.
     * @param time  The time for the new schedule.
     * @return A response indicating the success or failure of the operation.
     */
    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ){
        try {
            Bus newBus = Algorithm.<Bus>find(busTable, t -> t.id == busId);
            newBus.addSchedule(Timestamp.valueOf(time));
            return new BaseResponse<>(true, "Jadwal berhasil ditambahkan", newBus);

        } catch (Exception e) {
            return new BaseResponse<>(false, "Jadwal tidak berhasil ditambahkan", null);
        }
    }

    /**
     * Creates a new bus.
     *
     * @param accountId         The ID of the account associated with the bus.
     * @param name              The name of the bus.
     * @param capacity          The capacity of the bus.
     * @param facilities        The list of facilities available on the bus.
     * @param busType           The type of the bus.
     * @param price             The price of the bus.
     * @param stationDepartureId The ID of the departure station.
     * @param stationArrivalId   The ID of the arrival station.
     * @return A response indicating the success or failure of the operation.
     */
    @PostMapping("/create")
    public BaseResponse<Bus> create (
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ){
        try{
            Account account = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == accountId);
            if (account == null || account.company == null) {
                return new BaseResponse<>(false, "No Account Found", null);
            }
            Station stationDep = Algorithm.<Station>find(StationController.stationTable, stat -> stat.id == stationDepartureId);
            Station stationArr = Algorithm.<Station>find(StationController.stationTable, stat -> stat.id == stationArrivalId);
            if(stationDep == null || stationArr == null){
                return new BaseResponse<>(false, "Cannot Find Station", null);
            }

            Bus newbus = new Bus(
                    accountId,
                    name,
                    facilities,
                    new Price(price),
                    capacity,
                    busType,
                    stationDep,
                    stationArr
            );
            busTable.add(newbus);
            busTable.writeJson();
            return new BaseResponse<>(true, "Bus Added!", newbus);}
        catch (IllegalArgumentException | IOException e){
            return new BaseResponse<>(false, "Bus Fail To Be Add!",null);
        }
    }

    /**
     * Retrieves buses associated with a specific account.
     *
     * @param accountId The ID of the account.
     * @return A list of buses associated with the account.
     */
    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);}

    /**
     * Retrieves a bus by its ID.
     *
     * @param busId The ID of the bus to retrieve.
     * @return A list containing the bus with the specified ID.
     */
    @GetMapping("/getBusbyId")
    public List<Bus> getBusbyId(@RequestParam int busId) {
        return Algorithm.<Bus>collect(getJsonTable(), t -> t.id == busId);}

    /**
     * Retrieves all buses.
     *
     * @return A list containing all buses.
     */
    @GetMapping("/getAll")
    public List<Bus> getAllBus() { return getJsonTable();}
}
