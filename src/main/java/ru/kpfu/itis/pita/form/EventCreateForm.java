package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.NotEmpty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by volkov on 28.03.2017.
 */
public class EventCreateForm extends GroupCreateForm {

    @NotEmpty
    private String place;

    private int members;

    //@NotEmpty
    private Date date;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd mm yyyy - hh:mm");
        Date pars = format.parse(date);
        this.date = pars;
    }
}