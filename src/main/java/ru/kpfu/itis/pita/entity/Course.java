package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by taa on 26.03.17.
 */
@Entity
@Table(name = "courses")
public class Course{

    @Column(name = "group_id")
    private int groupId;

    @ManyToOne(targetEntity = Group.class)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @Column
    private int capacity;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Collection<User> teachers;

    @Column
    private String photo;

    @Column
    private Map<Date, String> timetable;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<User> teachers) {
        this.teachers = teachers;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Map<Date, String> getTimetable() {
        return timetable;
    }

    public void setTimetable(Map<Date, String> timetable) {
        this.timetable = timetable;
    }
}
