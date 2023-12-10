package com.darrenNathanaelBoentaraJBusIO;

import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to store the user account information.
 * @author Darren Nathanael
 * @see Serializable
 */
public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance = 0;
    /**
     * Regex for the email with the example : darren@gmail.com
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    /**
     * Regex for the password. Password should be at least 8 characters long, 1 uppercase letter
     * 1 lowercase letter, 1 number, and no whitespace
     */
    public static final String REGEX_PASSWORD = "^( =.*[a-z])( =.*[A-Z])( =.*\\d)[a-zA-Z\\d]{8,}$";

    /**
     * This is the constructor for the Account class.
     * @author Darren Nathanael
     */
    public Account(String name, String email, String password)
    {
        super();
        this.balance = 0.0;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString()
    {
        return "Id: " + this.id +", Name: " + this.name +", Email: " + this.email +
        ", Password: " + this.password +"\n"; 
    }

    public Object write(){
        return this;
    }
    public boolean read(String content) {
        return true;
    }

    /**
     * This is the method to validate email and password of the account.
     * @author Darren Nathanael
     */
    public boolean validate() {
        Pattern emailPat = Pattern.compile(REGEX_EMAIL);
        Matcher emailMatch = emailPat.matcher(email);
        Pattern passwordPat = Pattern.compile(REGEX_PASSWORD);
        Matcher passwordMatch = passwordPat.matcher(password);
        return emailMatch.matches() && passwordMatch.matches();
    }
    public boolean topUp (double amount){
        if (amount <= 0){
            return false;
        }
        this.balance += amount;
        return true;
    }
}
