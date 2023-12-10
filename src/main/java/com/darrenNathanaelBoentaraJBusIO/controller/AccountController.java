package com.darrenNathanaelBoentaraJBusIO.controller;

import com.darrenNathanaelBoentaraJBusIO.Account;
import com.darrenNathanaelBoentaraJBusIO.Algorithm;
import com.darrenNathanaelBoentaraJBusIO.Predicate;
import com.darrenNathanaelBoentaraJBusIO.Renter;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonAutowired;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**.
 * This class is used to control the /account request from the client
 * @author Darren Nathanael
 * @see Account
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @JsonAutowired(value = Account.class, filepath = "src\\main\\java\\com\\darrenNathanaelBoentaraJBusIO\\json\\account_db.json")
    public static JsonTable<Account> accountTable;

    /**
     * Method that is used t0 get the table of the accounts
     */
    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }

    @GetMapping
    String index() { return "account page"; }

    /**
     * Method that is used to register accounts
     */
    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        Predicate<Account> s = (val) -> val.email.equals(email);

        String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";
        String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
        Pattern patternPass = Pattern.compile(REGEX_PASSWORD);
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherPass = patternPass.matcher(password);
        Matcher matcherEmail = patternEmail.matcher(email);

        if (name.isBlank() == false && matcherPass.find() && matcherEmail.find() && Algorithm.exists(accountTable,s) == false) {
            String passwordToHash = password;
            String generatedPassword = null;
            try
            {
                MessageDigest md = MessageDigest.getInstance("MD5");

                md.update(passwordToHash.getBytes());

                byte[] bytes = md.digest();

                StringBuilder sb = new StringBuilder();
                for (int i = 0;i < bytes.length;i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Account tempAcc =  new Account(name, email, generatedPassword);
            accountTable.addElement(tempAcc);
            return new BaseResponse<>(true, "Berhasil register", tempAcc);
        }
        return new BaseResponse<>(false, "Gagal register", null);
    }

    /**
     * Method that is used to login to the application with your account
     */
    @PostMapping("/login")
    BaseResponse<Account> login
            (
                    @RequestParam String email,
                    @RequestParam String password
            ) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for(Account acc : accountTable){
            if (acc.email.equals(email) && acc.password.equals(generatedPassword)){
                return new BaseResponse <>(true, "Berhasil login", acc);
            }
        }
        return new BaseResponse <>(false, "Gagal login", null);
    }

    /**
     * Method that is used to register the renter of the bus
     */
    @PostMapping ("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter
            (
                    @PathVariable int id,
                    @RequestParam String companyName,
                    @RequestParam String address,
                    @RequestParam String phoneNumber
            ) {
        for(Account acc : accountTable){
            if (acc.id == id && acc.company == null){
                acc.company = new Renter(companyName, phoneNumber, address);
                return new BaseResponse <>(true, "Berhasil register renter", acc.company);
            }
        }
        return new BaseResponse <>(false, "Gagal register renter", null);
    }

    /**
     * Method that is used to handle topup for the account
     */
    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp
            (
                    @PathVariable int id,
                    @RequestParam double amount
            ) {
        for (Account acc : accountTable){
            if (acc.id == id){
                if (acc.topUp(amount)){
                    return new BaseResponse <>(true, "Berhasil register renter", amount);
                }
            }
        }
        return new BaseResponse<>(false, "Gagal top up", null);
    }
    /*
    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
    */
}
