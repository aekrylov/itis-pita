package ru.kpfu.itis.pita.form;


import org.springframework.format.annotation.DateTimeFormat;
import ru.kpfu.itis.pita.entity.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 4:41 PM
 */
public class EventCreateForm extends CommunityCreateForm<Event> {

    //@DateTimeFormat(pattern = "dd MM yyyy - hh:mm")
    private Date date;
    private String place;
    private int capacity;

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy - hh:mm");
        Date pars = format.parse(date);
        this.date = pars;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void toEntity(Event entity) {
        super.toEntity(entity);
        entity.setDate(date);
        entity.setPlace(place);
        entity.setMaxMembers(capacity);
    }

    @Override
    public EventCreateForm fromEntity(Event entity) {
        date = entity.getDate();
        place = entity.getPlace();
        capacity = entity.getMaxMembers();
        super.fromEntity(entity);
        return this;
    }
}
