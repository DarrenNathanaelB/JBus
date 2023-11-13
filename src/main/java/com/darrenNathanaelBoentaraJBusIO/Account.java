package com.darrenNathanaelBoentaraJBusIO;

import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance = 0;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    public static final String REGEX_PASSWORD = "^( =.*[a-z])( =.*[A-Z])( =.*\\d)[a-zA-Z\\d]{8,}$";
    
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
