package com.darrenNathanaelBoentaraJBusIO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+@[A-Za-z]+(\\.[A-Za-z]+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=\\S+$).{8,}$";
    
    public Account(String name, String email, String password)
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
}
