package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 11:18 AM
 */

@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @OneToOne
    private User user;

    @ManyToOne(optional = true)
    private Lab lab;

    @ManyToOne(optional = false)
    private AcademicGroup academicGroup;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public AcademicGroup getAcademicGroup() {
        return academicGroup;
    }

    public void setAcademicGroup(AcademicGroup academicGroup) {
        this.academicGroup = academicGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        return user.equals(student.user);
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }
}
