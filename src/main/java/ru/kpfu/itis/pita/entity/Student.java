package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 11:18 AM
 */

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {

    private Lab lab;
    private AcademicGroup academicGroup;
    private Collection<Interest> interests;

    @Transient
    private double averageScore;

    @Transient
    public double getAverageScore() {
        return averageScore;
    }
    @Transient
    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public Student() {
    }

    public Student(String name, String email, String phone, String passwordHash, String role, AcademicGroup academicGroup) {
        super(name, email, phone, passwordHash, role);
        this.academicGroup = academicGroup;
    }

    @ManyToOne(optional = true)
    public Lab getLab() {
        return lab;
    }
    public void setLab(Lab lab) {
        this.lab = lab;
    }

    @ManyToOne(optional = false)
    public AcademicGroup getAcademicGroup() {
        return academicGroup;
    }
    public void setAcademicGroup(AcademicGroup academicGroup) {
        this.academicGroup = academicGroup;
    }

    @ManyToMany
    @JoinTable(name = "students_interests")
    public Collection<Interest> getInterests() {
        return interests;
    }
    public void setInterests(Collection<Interest> interests) {
        this.interests = interests;
    }

}
