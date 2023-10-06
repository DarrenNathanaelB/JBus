package darrenNathanaelBoentaraJBusIO;

public class Account extends Serializable implements FileParser
{
    public String email;
    public String name;
    public String password;
    
    public Account(int id, String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString()
    {
        return "Id: " + this.id +", Name: " + this.name +", Email: " + this.email +
        ", Password: " + this.password +"\n"; 
    }
    @Override
    public Object write(){
        return this;
    }
    @Override
    public boolean read(String content){
        return true;
    }
}
