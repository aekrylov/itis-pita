package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 6:04 PM
 */

@Entity
@Table(name = "timetable_classes")
public class TimetableClass {

    @Id
    private int id;

    @ManyToOne(optional = false)
    private Subject subject;

    @ManyToOne(optional = false)
    private User teacher;

    @ManyToMany
    @JoinTable(name = "timetable_classes_groups")
    private Collection<AcademicGroup> groups;

    public TimetableClass() {
    }

    public TimetableClass(Subject subject, User teacher) {
        this.subject = subject;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Collection<AcademicGroup> getGroups() {
        return groups;
    }

    public void setGroups(Collection<AcademicGroup> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimetableClass)) return false;

        TimetableClass that = (TimetableClass) o;

        if (id != that.id) return false;
        if (!subject.equals(that.subject)) return false;
        if (!teacher.equals(that.teacher)) return false;
        return groups != null ? groups.equals(that.groups) : that.groups == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + subject.hashCode();
        result = 31 * result + teacher.hashCode();
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }
}
