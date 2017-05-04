package ru.kpfu.itis.pita.entity;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 6:04 PM
 *
 * Entity representing certain class for certain group/groups during one semester
 * (e.g. Math, 11-501, Fall 2017)
 * Dates for classes are represented by separate entity for maximum flexibility
 */

@Entity
@Table(name = "timetable_classes")
public class TimetableClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private Subject subject;

    //todo move to date?
    @ManyToOne(optional = false)
    private User teacher;

    @ManyToMany
    @JoinTable(name = "timetable_classes_groups")
    private Collection<AcademicGroup> groups;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "timetableClass", orphanRemoval = true)
    @SortNatural
    private Set<TimetableDate> dates = new TreeSet<>();

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

        if (id != that.getId()) return false;
        if (!subject.equals(that.getSubject())) return false;
        if (!teacher.equals(that.getTeacher())) return false;
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

    public Set<TimetableDate> getDates() {
        return dates;
    }

    public void setDates(Set<TimetableDate> dates) {
        this.dates = dates;
    }
}
