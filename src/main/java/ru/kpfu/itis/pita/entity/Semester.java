package ru.kpfu.itis.pita.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 5:53 PM
 */

@Entity
@Table(name = "semesters")
public class Semester {

    @Id
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "start_date", nullable = false)
    private Date startDate = new Date();

    public Semester(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Semester)) return false;

        Semester semester = (Semester) o;

        if (id != semester.id) return false;
        return startDate.equals(semester.startDate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + startDate.hashCode();
        return result;
    }
}