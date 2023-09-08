package darrenNathanaelBoentaraJBusIO;

public class JBus
{
    public static void main(String[] args){
        System.out.println("Bus ID : " + getBusId());
        System.out.println("Nama Bus : " + getBusName());
        System.out.println("Terdapat Discount : " + isDiscount());
        System.out.println("Besar Discount : " + getDiscountPercentage(1000,900));
        System.out.println("Besar Harga Discount : " + getDiscountedPrice(1000,10));
        System.out.println("Besar Harga Asli : " + getOriginalPrice(900,10));
        System.out.println("Besar Admin Fee : " + getAdminFeePercentage());
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
            float diskon = (discountedPrice / 1000) - discountPercentage;
            float hargaAsli = diskon + discountedPrice;
            return (int) hargaAsli;
        }
    }
    
    public static float getAdminFeePercentage()
    {
        return (float) 0.05;
    }
}
