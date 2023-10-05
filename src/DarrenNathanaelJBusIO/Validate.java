package darrenNathanaelBoentaraJBusIO;

import java.util.ArrayList;

public class Validate
{
    public Validate()
    {
        
    }

    public static ArrayList<Double> filter (Price[] list, int value, boolean less)
    {
        ArrayList<Double> newArr = new ArrayList<Double>();
        for (Price harga: list){
            if ((less && harga.price <= value) || (!less && harga.price > value)){
                newArr.add(harga.price);
            }
        }
        return newArr;
    }
}
