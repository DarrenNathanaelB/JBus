package darrenNathanaelBoentaraJBusIO;
import java.util.*;

public class JBus
{
    public static void main(String[] args){
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
        System.out.println(Validate.filter(unfilteredArray, 10000, false));**/
        
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
    }
}

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
