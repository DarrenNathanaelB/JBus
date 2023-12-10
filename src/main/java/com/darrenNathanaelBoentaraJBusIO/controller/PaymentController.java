package com.darrenNathanaelBoentaraJBusIO.controller;

import com.darrenNathanaelBoentaraJBusIO.*;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonAutowired;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.darrenNathanaelBoentaraJBusIO.Algorithm.exists;
import static com.darrenNathanaelBoentaraJBusIO.Algorithm.find;
import static com.darrenNathanaelBoentaraJBusIO.controller.BusController.busTable;

/**
 * This class handles HTTP requests related to payment operations,
 * including making bookings, accepting payments, canceling payments, and retrieving booked buses.
 *
 * @author Darren Nathanael
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    @JsonAutowired(value = Payment.class, filepath = "src\\main\\java\\com\\darrenNathanaelBoentaraJBusIO\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;

    /**
     * Makes a booking for a bus.
     *
     * @param buyerId       The ID of the buyer account.
     * @param renterId      The ID of the renter account.
     * @param busId         The ID of the bus.
     * @param busSeats      The list of booked bus seats.
     * @param departureDate The departure date of the bus.
     * @return A response indicating the success or failure of the booking.
     */
    @PostMapping("/makeBooking")
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate

    ){
        try {
            Account buyeracc = Algorithm.<Account>find(new AccountController().getJsonTable(), t->t.id == buyerId);
            Bus bus = Algorithm.<Bus>find(new BusController().getJsonTable(), t->t.id == busId);
            if (buyeracc == null || bus == null){
                return new BaseResponse<>(false, "Buyer Account Or Bus Not Found", null);
            }
            if (buyeracc.balance < bus.price.price) {
                return new BaseResponse<>(false, "Balance Not Enough", null);
            }
            if (!Algorithm.<Schedule>exists(bus.schedules, t->t.departureSchedule.equals(Timestamp.valueOf(departureDate)))) {
                return new BaseResponse<>(false, "Schedule Not Found", null);
            }
            if(!Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, bus)){
                return new BaseResponse<>(false, "Make Booking Failed", null);
            }
            Payment paymentTemp = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
            paymentTemp.status = Invoice.PaymentStatus.WAITING;
            paymentTable.add(paymentTemp);
            return new BaseResponse<>(true, "Make Booking Successful", paymentTemp);
        }catch (Exception e){
            return new BaseResponse<>(false, "-", null);
        }
    }

    /**
     * Accepts a payment by updating the payment status to SUCCESS.
     *
     * @param id The ID of the payment to accept.
     * @return A response indicating the success or failure of the payment acceptance.
     */
    @RequestMapping(value = "/{id}/accept", method = RequestMethod.POST)
    BaseResponse<Payment> accept(@PathVariable int id){
        if (!(Algorithm.<Payment>exists(paymentTable, x -> x.id == id))){
            return new BaseResponse<>(false, "Payment Failed", null);
        }

        Payment newPayment = Algorithm.<Payment>find(paymentTable, x -> x.id == id);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, b->b.id == newPayment.getBusId());
        Account account = Algorithm.<Account>find(AccountController.accountTable, a->a.id == newPayment.buyerId);

        account.balance = account.balance - bus.price.price;
        newPayment.status = Invoice.PaymentStatus.SUCCESS;
        return new BaseResponse<>(true, "Payment Successful", newPayment);
    }

    /**
     * Cancels a payment by updating the payment status to FAILED.
     *
     * @param id The ID of the payment to cancel.
     * @return A response indicating the success or failure of the payment cancellation.
     */
    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    BaseResponse<Payment> cancel(@PathVariable int id){
        if (!(Algorithm.<Payment>exists(paymentTable, x -> x.id == id))){
            return new BaseResponse<>(false, "Cancel Payment Failed", null);
        }

        Payment newPayment = Algorithm.<Payment>find(paymentTable, a -> a.id == id);
        newPayment.status = Invoice.PaymentStatus.FAILED;
        return new BaseResponse<>(true, "Payment Canceled", newPayment);
    }

    /**
     * Retrieves booked buses for a specific buyer account.
     *
     * @param buyerId The ID of the buyer account.
     * @return A list of payments representing booked buses for the buyer account.
     */
    @GetMapping("/getBookedBusById")
    public List<Payment> getBookedBusById(@RequestParam int buyerId) {
        return Algorithm.<Payment>collect(getJsonTable(), t->t.buyerId == buyerId);}

    /**
     * Gets the JSON table associated with the Payment entity.
     *
     * @return The JSON table for the Payment entity.
     */
    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }
}
