package ru.kpfu.itis.pita.form;

import java.util.Date;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 4:41 PM
 */
public class EventCreateForm extends CommunityCreateForm {

    private Date date;
    private String place;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
