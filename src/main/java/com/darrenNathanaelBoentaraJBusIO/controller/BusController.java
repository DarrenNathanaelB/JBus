package com.darrenNathanaelBoentaraJBusIO.controller;

import com.darrenNathanaelBoentaraJBusIO.*;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonAutowired;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Predicate<Bus> s = (val) -> val.id == busId;
        Bus tmp = Algorithm.find(busTable,s);
        if (tmp != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
            Timestamp departureDate = Timestamp.valueOf(time);
            tmp.addSchedule(departureDate);
            return new BaseResponse<>(true, "Berhasil addSchedule", tmp);
            /*
            try {
                Date date = dateFormat.parse(time);
                Timestamp departureDate = new Timestamp(date.getTime());
                tmp.addSchedule(departureDate);
                return new BaseResponse<>(true, "Berhasil addSchedule", tmp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            */
        }
        return new BaseResponse<>(false, "Gagal addSchedule", null);
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
        if(Algorithm.<Bus>exists(busTable, a -> a.arrival.id == stationArrivalId) && Algorithm.<Bus>exists(busTable, a -> a.departure.id == stationDepartureId)) {
            Account account = Algorithm.<Account>find(AccountController.accountTable, a -> a.id == accountId);
            if (account != null) {
                if (account.company != null) {
                    Bus bus = Algorithm.<Bus>find(busTable, a -> a.arrival.id == stationArrivalId && a.departure.id == stationDepartureId);
                    Price priceObj = new Price(price);
                    Bus createdBus = new Bus(name, facilities, priceObj, capacity, busType, bus.departure, bus.arrival);
                    return new BaseResponse<>(true, "Bus Telah Berhasil Dibuat", createdBus);
                } else
                    return new BaseResponse<>(false, "Gagal Dibuat Karena Bukan Merupakan Renter", null);
            }
            return new BaseResponse<>(false, "Gagal Dibuat Karena Tidak Menemukan Account Dengan ID Yang Sesuai", null);
        } return new BaseResponse<>(false, "Gagal Dibuat karena Tidak Terdapat ID Departure atau Arrival", null);
    }
}
