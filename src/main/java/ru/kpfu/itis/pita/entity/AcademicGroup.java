package ru.kpfu.itis.pita.entity;

import javax.persistence.*;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 11:17 AM
 */

@Entity
@Table(name = "academic_groups")
public class AcademicGroup {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int start_year;

    @Column(nullable = false)
    private int finish_year;

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

    public int getStart_year() {
        return start_year;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }

    public int getFinish_year() {
        return finish_year;
    }

    public void setFinish_year(int finish_year) {
        this.finish_year = finish_year;
    }
}
