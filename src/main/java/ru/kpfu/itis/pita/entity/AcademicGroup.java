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
}
