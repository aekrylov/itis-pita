package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.ScriptAssert;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * Created by volkov on 29.04.2017.
 */
@ScriptAssert(lang = "javascript", script = "_this.new_password.equals(_this.password_confirmed)")
public class ChangePasswordForm {


    @NotEmpty
    private String old_password;

    @NotEmpty
    @Length(min = 8, max = 32)
    private String new_password;

    @NotEmpty
    private String password_confirmed;

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getPassword_confirmed() {
        return password_confirmed;
    }

    public void setPassword_confirmed(String password_confirmed) {
        this.password_confirmed = password_confirmed;
    }
}
