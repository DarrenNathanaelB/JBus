package com.darrenNathanaelBoentaraJBusIO;

import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to store the renter information.
 * @author Darren Nathanael
 * @see Serializable
 */
public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public String phoneNumber;
    /**
     * Regex for the phone number. Password should be at least 9 digit and maximum of 12 digit
     */
    private final String REGEX_PHONE = "\\d{9,12}$";
    /**
     * Regex for the name.
     */
    private final String REGEX_NAME = "^[A-Z][A-Z0-9_]{3,19}$";

    public Renter(String companyName)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = "";
        this.address = "";
    }
    
    public Renter (String companyName, String phoneNumber)
    {
        super();
       this.companyName = companyName;
       this.phoneNumber = phoneNumber;
       this.address = "";
    }
    
    public Renter (String companyName, String phoneNumber, String address)
    {
        super();
       this.companyName = companyName;
       this.phoneNumber = phoneNumber;
       this.address = address;
    }

    /**
     * method that is used to validate the renter name and phone
     */
    public boolean validate(){
        Pattern namePat = Pattern.compile(REGEX_NAME);
        Matcher nameMatch = namePat.matcher(this.companyName);
        Pattern phonePat = Pattern.compile(REGEX_PHONE);
        Matcher phoneMatch = phonePat.matcher(String.valueOf(phoneNumber));
        return phoneMatch.find() && nameMatch.find();
    }
}
