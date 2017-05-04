package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 6:09 PM
 *
 * One date for some class
 */

@Entity
@Table(name = "timetable_dates")
public class TimetableDate implements Comparable<TimetableDate> {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    private TimetableClass timetableClass;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "place")
    private String place;

    public TimetableDate() {
    }

    public TimetableDate(TimetableClass timetableClass, LocalDateTime startTime, String place) {
        this.timetableClass = timetableClass;
        this.startTime = startTime;
        this.place = place;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
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

        return id == that.getId()
                && timetableClass.equals(that.getTimetableClass())
                && startTime.equals(that.getStartTime());
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + timetableClass.hashCode();
        result = 31 * result + startTime.hashCode();
        return result;
    }

    @Override
    public int compareTo(TimetableDate o) {
        return startTime.compareTo(o.getStartTime());
    }
}
