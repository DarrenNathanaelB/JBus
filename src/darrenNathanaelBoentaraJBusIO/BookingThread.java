package darrenNathanaelBoentaraJBusIO;

import java.sql.Timestamp;
public class BookingThread extends Thread{
    private Bus bus;
    private Timestamp timestamp ;
    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }
    public void run() {
        long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread " + threadId + " ID: " + threadName + " is running");

        synchronized (bus) {
            String seatToBook = "IO01";
            boolean book = Payment.makeBooking(timestamp, seatToBook, bus);

            if (book) {
                System.out.println(getName() + " Berhasil Melakukan Booking");
            } else {
                System.out.println(getName() + " Gagal Melakukan Booking ");
            }
        }
    }
}
