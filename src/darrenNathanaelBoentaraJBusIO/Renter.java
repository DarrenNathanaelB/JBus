package darrenNathanaelBoentaraJBusIO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;
    private final String REGEX_PHONE = "\\d{9,12}$";
    private final String REGEX_NAME = "^[A-Z][A-Z0-9_]{3,19}$";

    public Renter(String companyName)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = "";
    }

    public Renter (String companyName, String address)
    {
        super();
       this.companyName = companyName;
       this.address = address;
       this.phoneNumber = 0;
    }
    
    public Renter (String companyName, int phoneNumber)
    {
        super();
       this.companyName = companyName;
       this.phoneNumber = phoneNumber;
       this.address = "";
    }
    
    public Renter (String companyName,int phoneNumber, String address)
    {
        super();
       this.companyName = companyName;
       this.phoneNumber = phoneNumber;
       this.address = address;
    }

    public boolean validate(){
            Pattern namePat = Pattern.compile(REGEX_NAME);
            Matcher nameMatch = namePat.matcher(this.companyName);
            Pattern phonePat = Pattern.compile(REGEX_PHONE);
            Matcher phoneMatch = phonePat.matcher(String.valueOf(phoneNumber));
            return phoneMatch.find() && nameMatch.matches());
    }
}
