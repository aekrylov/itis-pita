package ru.kpfu.itis.pita.entity;

import javax.persistence.*;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 11:30 AM
 */

@Entity
@Table(name = "interests")
public class Interest {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;


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
}
