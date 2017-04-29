package ru.kpfu.itis.pita.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 1 on 12.04.2017.
 * //todo move it to form
 */
@Component
public class UserValidator {
    private boolean isValidEmail(String email){
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean isValidName(String name){
        Pattern p = Pattern.compile("^[A-Za-z]{2,20}$");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    private boolean isValidPhone(String phone){
        Pattern p = Pattern.compile("^\\d{11}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    private boolean isValidPassword(String password){
        Pattern p = Pattern.compile("^[A-Za-z0-9]{6,20}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

}
