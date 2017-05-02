package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 6:09 PM
 *
 * One date for some class
 */

@Entity
@Table(name = "timetable_dates")
public class TimetableDate {

    @Id
    private int id;

    @ManyToOne(optional = false)
    private TimetableClass timetableClass;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "place")
    private String place;

    public TimetableDate(TimetableClass timetableClass, Date startTime) {
        this.timetableClass = timetableClass;
        this.startTime = startTime;
    }

    public TimetableDate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TimetableClass getTimetableClass() {
        return timetableClass;
    }

    public void setTimetableClass(TimetableClass timetableClass) {
        this.timetableClass = timetableClass;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimetableDate)) return false;

        TimetableDate that = (TimetableDate) o;

        if (id != that.id) return false;
        if (!timetableClass.equals(that.timetableClass)) return false;
        return startTime.equals(that.startTime);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + timetableClass.hashCode();
        result = 31 * result + startTime.hashCode();
        return result;
    }
}
