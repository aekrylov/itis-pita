package ru.kpfu.itis.pita.entity;

import javax.persistence.*;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 9:56 PM
 */
@Entity
@Table(name = "scores")
public class StudentScore {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne(optional = false)
    private Subject subject;

    @Column(nullable = false)
    private boolean closed = false;

    private int praxisScore;
    private int examScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getPraxisScore() {
        return praxisScore;
    }

    public void setPraxisScore(int praxisScore) {
        this.praxisScore = praxisScore;
    }

    public int getExamScore() {
        return examScore;
    }

    public void setExamScore(int examScore) {
        this.examScore = examScore;
    }
}
