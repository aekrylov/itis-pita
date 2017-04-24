package ru.kpfu.itis.pita.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 11:04 AM
 */

@Entity
@Table(name = "events")
@PrimaryKeyJoinColumn(name = "id")
public class Event extends Group {

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "max_members")
    private int maxMembers = -1;

    public Event() {
        setType(CommunityType.EVENT);
    }

    public Event(String name, String description, User creator, Collection<Interest> interests, String place, Date date) {
        super(name, description, creator, interests);
        setType(CommunityType.EVENT);
        this.place = place;
        this.date = date;
    }

    public Event(String name, String description, User creator, Collection<Interest> interests, String place, Date date, int maxMembers) {
        this(name, description, creator, interests, place, date);
        this.maxMembers = maxMembers;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }
}
