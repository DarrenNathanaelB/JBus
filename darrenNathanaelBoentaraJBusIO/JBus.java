package darrenNathanaelBoentaraJBusIO;

public class JBus
{
    public static void main(String[] args){
        /**Bus testBus = createBus();
        
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);**/
        
        Price tesPrice = new Price(200);
        Voucher tes = new Voucher(1,"halo", 200, Type.REBATE, 150, 10);

        System.out.println(tes.name);
        System.out.println(tes.canApply(tesPrice));
        System.out.println(tes.apply(tesPrice));
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
}
