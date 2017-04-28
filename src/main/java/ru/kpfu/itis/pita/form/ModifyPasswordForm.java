package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Oleg Shatin
 *         11-501
 */
public class ModifyPasswordForm {
    @NotEmpty
    private String old_password;
    @NotEmpty
    @Length(min = 8, max = 30)
    private String password;
    private String repeated_password;

    public String getOldPassword() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
