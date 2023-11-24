package com.darrenNathanaelBoentaraJBusIO.controller;

import com.darrenNathanaelBoentaraJBusIO.*;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonAutowired;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import static com.darrenNathanaelBoentaraJBusIO.Algorithm.exists;
import static com.darrenNathanaelBoentaraJBusIO.Algorithm.find;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    @JsonAutowired(value = Payment.class, filepath = "src\\main\\java\\com\\darrenNathanaelBoentaraJBusIO\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;

    @RequestMapping(value = "/makeBooking", method = RequestMethod.POST)
    BaseResponse<Payment> makeBooking (
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ){
        for(Account account : AccountController.accountTable){
            if(account.id == buyerId && account.balance >= BusController.busTable.get(busId).price.price && Algorithm.<Schedule>find(BusController.busTable.get(busId).schedules, s -> s.departureSchedule.equals(departureDate)) != null){
                account.balance -= BusController.busTable.get(busId).price.price;
                Payment payment = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
                payment.status = Invoice.PaymentStatus.WAITING;
                paymentTable.add(payment);
                return new BaseResponse<>(true, "Berhasil Melakukan Booking", payment);
            }
            else
                return new BaseResponse<>(false, "Gagal Melakukan Booking", null);
        }

        return new BaseResponse<>(false, "Gagal Melakukan Booking", null);
    }

    @RequestMapping(value = "/{id}/accept", method = RequestMethod.POST)
    BaseResponse<Payment> accept(@PathVariable int id){
        if (!(Algorithm.<Payment>exists(paymentTable, x -> x.id == id))){
            return new BaseResponse<>(false, "Payment Tidak Ada", null);
        }

        Payment newPayment = Algorithm.<Payment>find(paymentTable, x -> x.id == id);
        newPayment.status = Invoice.PaymentStatus.SUCCESS;
        return new BaseResponse<>(true, "Payment Cancel", newPayment);
    }

    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    BaseResponse<Payment> cancel(@PathVariable int id){
        if (!(Algorithm.<Payment>exists(paymentTable, x -> x.id == id))){
            return new BaseResponse<>(false, "Payment Tidak Ada", null);
        }

        Payment newPayment = Algorithm.<Payment>find(paymentTable, a -> a.id == id);
        newPayment.status = Invoice.PaymentStatus.FAILED;
        return new BaseResponse<>(true, "Payment Cancel", newPayment);
    }

    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }
}
