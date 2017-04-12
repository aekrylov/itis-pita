package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

/**
 * Created by 1 on 08.04.2017.
 */
@ScriptAssert(lang = "javascript", script = "_this.password.equals(_this.password_confirmed)")
public class RegistrationForm {

    @NotEmpty
    @Email
    @Length(min = 6, max = 32)
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String phone;

    @NotEmpty
    @Length(min = 8, max = 32)
    private String password;

    @NotEmpty
    private String password_confirmed;


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmed() {
        return password_confirmed;
    }

    public void setPassword_confirmed(String password_confirmed) {
        this.password_confirmed = password_confirmed;
    }
}
