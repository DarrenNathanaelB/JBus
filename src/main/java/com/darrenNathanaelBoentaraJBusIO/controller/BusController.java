package com.darrenNathanaelBoentaraJBusIO.controller;

import com.darrenNathanaelBoentaraJBusIO.*;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonAutowired;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus>{
    @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\darrenNathanaelBoentaraJBusIO\\json\\bus_db.json")
    public static JsonTable<Bus> busTable;

    public JsonTable<Bus> getJsonTable(){
        return busTable;
    }

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

    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);}

    @GetMapping("/getBusbyId")
    public List<Bus> getBusbyId(@RequestParam int busId) {
        return Algorithm.<Bus>collect(getJsonTable(), t -> t.id == busId);}

    @GetMapping("/getAll")
    public List<Bus> getAllBus() { return getJsonTable();}
}
