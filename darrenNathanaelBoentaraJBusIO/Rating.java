package darrenNathanaelBoentaraJBusIO;

public class Rating
{
    private long count;
    private long total;

    public Rating()
    {
        this.total = 0;
        this.count = 0;
    }
    
    public void insert (int rating){
        this.total += rating;
        this.count ++;
    }
    
    public long getTotal (){
        return this.total;
    }
    
    public long getCount (){
        return this.count;
    }
    
    public double getAverage (){
        if (this.count == 0){
            return 0;
        }
        
        else {
            return this.total / this.count;
        }
    }
}
