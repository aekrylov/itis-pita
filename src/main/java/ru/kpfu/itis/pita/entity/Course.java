package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by taa on 26.03.17.
 */
@Entity
@Table(name = "courses")
public class Course{

    @Id
    private int id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Group group;

    @Column(nullable = false)
    private int capacity;

    //todo merge with group admins?
    @ManyToMany
    @JoinTable(name= "courses_teachers",joinColumns = {
            @JoinColumn(name = "course_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "teacher_id")
    })
    private Collection<User> teachers;

    //todo implement timetable
/*
    @Column
    private Map<Date, String> timetable;
*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Collection<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<User> teachers) {
        this.teachers = teachers;
    }

/*
    public Map<Date, String> getTimetable() {
        return timetable;
    }

    public void setTimetable(Map<Date, String> timetable) {
        this.timetable = timetable;
    }
*/

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
