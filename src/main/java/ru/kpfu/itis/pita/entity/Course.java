package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by taa on 26.03.17.
 */
@Entity
@Table(name = "courses")
@PrimaryKeyJoinColumn(name = "id")
public class Course extends Community {

    @Column(nullable = false)
    private int capacity;

    @Column
    private String schedule;

    @ManyToMany
    @JoinTable(name= "courses_teachers",joinColumns = {
            @JoinColumn(name = "course_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "teacher_id")
    })
    private Collection<User> teachers;

    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    private Subject subject;

    public Course() {
        super(CommunityType.COURSE);
    }

    public Course(String name, String description, User creator, int capacity) {
        super(CommunityType.COURSE, name, description, creator);
        this.capacity = capacity;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}