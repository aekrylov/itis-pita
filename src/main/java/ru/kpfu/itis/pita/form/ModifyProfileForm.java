package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by volkov on 23.04.2017.
 */
public class ModifyProfileForm {
    @NotEmpty
    @Email
    @Length(min = 6, max = 32)
    private String email;


    @NotEmpty
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
