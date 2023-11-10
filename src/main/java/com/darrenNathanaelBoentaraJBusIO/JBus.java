package com.darrenNathanaelBoentaraJBusIO;

import java.sql.Timestamp;
import java.util.List;

import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonDBEngine;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JBus {
    public static void main(String[] args) throws InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
        //SpringApplication.run(JBus.class, args);
        /*// TP Modul 6
        String filepath = "C:\\Users\\LENOVO\\Documents\\Kuliah\\Semester 3\\Prak OOP\\JBus\\JBus\\data\\station.json";
        Gson gson = new Gson();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filepath));
            List<Station> stationjson = gson.fromJson(buffer, new TypeToken<List<Station>>() {
            }.getType());
            stationjson.forEach(e -> System.out.println(e.toString()));
            System.out.println();
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        for (int i = 0; i < 10; i++) {
            BookingThread thread = new BookingThread("Thread " + i, bus,
                    Timestamp.valueOf("2023-07-27 19:00:00"));
        }
        Thread.sleep(1000);
        bus.schedules.forEach(Schedule::printSchedule);

        try {
            String filepath = "C:\\Users\\LENOVO\\Documents\\Kuliah\\Semester 3\\Prak OOP\\JBus\\JBus\\data\\accountDatabase.json";
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);

            Account account1 = new Account("Dio", "dio@gmail.com", "NgikNgok");
            tableAccount.add(account1);
            tableAccount.writeJson();
            for (Account print : tableAccount) {
                System.out.println(print.toString());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25,
                BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
                Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }


    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page , int pageSize){
        Predicate<Bus> filterPredicate = bus -> bus.departure.city == departure;
        List<Bus> filteredBuses = Algorithm.collect(buses, filterPredicate);
        List<Bus> paginatedBuses = Algorithm.paginate(filteredBuses, page, pageSize, bus -> true);

        return paginatedBuses;
    }

    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max){
        Predicate<Bus> filterPredicate = bus -> bus.price.price >= min && bus.price.price <= max;
        List<Bus> filteredBuses = Algorithm.collect(buses, filterPredicate);

        return filteredBuses;
    }

    public static List<Bus> filterBusId(List<Bus> buses, int id){
        Predicate<Bus> filterPredicate = bus -> bus.id == id;
        List<Bus> filteredBuses = Algorithm.collect(buses, filterPredicate);

        return filteredBuses;
    }

    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page , int pageSize){
        Predicate<Bus> filterPredicate = bus -> bus.departure.city == departure && bus.arrival.city == arrival;
        List<Bus> filteredBuses = Algorithm.collect(buses, filterPredicate);
        List<Bus> paginatedBuses = Algorithm.paginate(filteredBuses, page, pageSize, bus -> true);

        return paginatedBuses;
    }*/

        /*// PT Modul 5
        // Tes Pagination
        Bus b = createBus();
        List<Timestamp> listOfSchedules = new ArrayList<>();
        listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));

        listOfSchedules.forEach(b::addSchedule);
        System.out.println("Page 1");
        Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");
        System.out.println("Page 2");
        Algorithm.paginate(b.schedules, 1, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");

        // Tes Booking
        String msgSuccess = "Booking Success!";
        String msgFailed = "Booking Failed";
        // valid date, invalid seat = Booking Failed
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: IO17 IO18");
        System.out.println(Payment.makeBooking(t1, List.of("IO17", "IO18"), b)? msgSuccess : msgFailed);
        // valid date, invalid seat = Booking Failed
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("Make booking at July 18, 2023 15:00:00 Seat IO26");
        System.out.println(Payment.makeBooking(t2, "IO26", b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        System.out.println("Make booking at July 18, 2023 15:00:00 Seats: IO07 IO08");
        System.out.println(Payment.makeBooking(t2, List.of("IO07", "IO08"), b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("Make booking at July 20, 2023 12:00:00 Seats: IO01 IO02");
        System.out.println(Payment.makeBooking(t3, List.of("IO01", "IO02"), b)? msgSuccess : msgFailed);
        // valid date, book the same seat = Booking Failed
        System.out.println("Make booking at July 20, 2023 12:00:00 Seat IO01");
        System.out.println(Payment.makeBooking(t3, "IO01", b)? msgSuccess : msgFailed);
        // check if the data changed
        System.out.println("\nUpdated Schedule");
        Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);*/

        /*Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
        System.out.println("Number "+Arrays.toString(numbers));

        // Tes Algorithm
        System.out.print("1. ");
        testCount(numbers);
        System.out.print("2. ");
        testFind(numbers);
        System.out.print("3. ");
        testExist(numbers);
        System.out.println("4. Filtering");
        testCollect(numbers);
    }
    private static void testExist(Integer[] t) {
        int valueToCheck = 67;
        boolean result3 = Algorithm.exists(t, valueToCheck);
        if (result3) {
            System.out.println(valueToCheck + " exist in the array.");
        } else {
            System.out.println(valueToCheck + " doesn't exists in the array.");
        }
    }
    public static void testCount(Integer[] t) {
        int valueToCount = 18;
        int result = Algorithm.count(t, valueToCount);
        System.out.println("Number " + valueToCount + " appears " + result + " times");
    }
    public static void testFind(Integer[] t) {
        Integer valueToFind = 69;
        Integer result2 = Algorithm.find(t, valueToFind);
        System.out.print("Finding " + valueToFind + " inside the array : ");
        if (result2 != null) {
            System.out.println("Found!" + result2);
        } else {
            System.out.println("Not Found");
        }
    }
    private static void testCollect(Integer[] t) {
        Predicate<Integer> below = (val)->val<=22;
        Predicate<Integer> above = (val)->val>43;

        List<Integer> integerBelow = Algorithm.collect(t, below);
        List<Integer> integerAbove = Algorithm.collect(t, above);

        System.out.println("Below 22");
        System.out.println(integerBelow);
        System.out.println("Above 43");
        System.out.println(integerAbove);*/
    }
}

    /*public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }*/

        /**Bus testBus = createBus();
        
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);
        
        Price tesPrice = new Price(200);
        Voucher tes = new Voucher(1,"halo", 200, Type.REBATE, 150, 10);

        System.out.println(tes.name);
        System.out.println(tes.canApply(tesPrice));
        System.out.println(tes.apply(tesPrice));
        
        Payment testPayment = new Payment(1, 1, 1, "A", 1, "A", "A");
        Invoice testInvoice = new Invoice(2,2,2, "B");
        Station testStation = new Station(3, "C", City.DEPOK);
        System.out.println(testPayment.print());
        System.out.println(testInvoice.print());
        System.out.println(testStation.print());
        
        Review testReview = new Review(1, "23 August 2023", "Bad Quality");
        Price testPrice = new Price(100000, 20000);
        Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
        Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        Bus testBus = new Bus(1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
        Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
        Rating testRating = new Rating();

        System.out.println(testReview);
        System.out.println(testBus);
        System.out.println(testAccount);
        System.out.println(testPrice);
        System.out.println(testRating);
        
        Price[] unfilteredArray = new Price[5];
        for(int i = 0; i <unfilteredArray.length; i++){
            int j = 5000;
            unfilteredArray[i] = new Price((i+1)*j);
        }
        System.out.println("Price List");
        for(Price price : unfilteredArray){
            System.out.println(price.price);
        }
        System.out.println("Below 12000.0");
        System.out.println(Validate.filter(unfilteredArray, 12000, true));
        System.out.println("Above 10000.0");
        System.out.println(Validate.filter(unfilteredArray, 10000, false));
        
         Bus testBus = createBus();
     
         Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
         System.out.println(testPayment.getDepartureInfo());
         System.out.println(testPayment.getTime());
     
         Calendar sched1 = Calendar.getInstance();
         testBus.addSchedule(sched1);
         Calendar sched2 =  Calendar.getInstance();
         sched2.add(Calendar.DAY_OF_MONTH, 3);
         testBus.addSchedule(sched2);
     
         for(Schedule s : testBus.schedules) {
             testBus.printSchedule(s);
         }
         
    }
     

    public static Bus createBus(){
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
        
    Bus b = createBus();
        Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
        Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
        
        b.addSchedule(schedule1);
        b.addSchedule(schedule2);
        b.schedules.forEach(Schedule::printSchedule);
        
        //Invalid date
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("Make booking at July 19, 2023 15:00:00 Seat IO12");
        System.out.println(Payment.makeBooking(t1, "IO12", b));
        
        //Valid date, invalid seat
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat IO20");
        System.out.println(Payment.makeBooking(t2, "IO20", b));

        //Valid date, valid seat
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat IO07");
        System.out.println(Payment.makeBooking(t2, "IO07", b));
        
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat IO01");
        System.out.println(Payment.makeBooking(t3, "IO01", b));

        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat IO01 again");
        System.out.println(Payment.makeBooking(t3, "IO01", b));

        //Check if the data changes
        System.out.println("\nUpdated Schedule\n");
        b.schedules.forEach(Schedule::printSchedule);

    }
    
    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }**/



    /**public static Bus createBus(){
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25);
        return bus;
    }
    
    public static int getBusId()
    {
        return 0;
    }
    
    public static String getBusName()
    {
        return "Bus";
    }
    
    public static boolean isDiscount()
    {
        return true;
    }
    
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount)
    {
        if (beforeDiscount < afterDiscount){
            return (float) 0;
        }
        
        else if (beforeDiscount == afterDiscount){
            return (float) 0;
        }
        else {
            return (float) (beforeDiscount - afterDiscount) / beforeDiscount * 100;
        }
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        if (discountPercentage >= 100){
            return 0;
        }
        else {
            float diskon = ((100 - discountPercentage) / 100);
            float hargaAkhir = diskon * price;
            return (int) hargaAkhir;
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage)
    {
        if (discountPercentage >= 100){
            return 0;
        }
        else {
            float persen = 100 - discountPercentage;
            float diskon = 100 * discountedPrice;
            float hargaAsli = diskon / persen;
            return (int) hargaAsli;
        }
    }
    
    public static float getAdminFeePercentage()
    {
        return (float) 0.05;
    }
    
    public static int getAdminFee(int price)
    {
        double admin = price * getAdminFeePercentage();
        return (int) admin;
    }
    
    public static int getTotalPrice(int price, int numberOfSeat)
    {
        int total = price * numberOfSeat;
        double admin = total * getAdminFeePercentage();
        double fixTotal = total + admin;
        return (int) fixTotal;
    }**/
