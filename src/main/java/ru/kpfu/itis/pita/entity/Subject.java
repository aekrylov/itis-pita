package ru.kpfu.itis.pita.entity;

import javax.persistence.*;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 5:56 PM
 */

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    private Semester semester;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
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

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;

        Subject subject = (Subject) o;

        if (id != subject.id) return false;
        if (!name.equals(subject.name)) return false;
        return semester != null ? semester.equals(subject.semester) : subject.semester == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        return result;
    }
}