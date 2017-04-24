package ru.kpfu.itis.pita.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 11:22 AM
 */

@Entity
@Table(name = "labs")
@PrimaryKeyJoinColumn(name = "id")
public class Lab extends Community {

    //TODO

    public Lab() {
        super(CommunityType.LAB);
    }

    public Lab(String name, String description, User creator) {
        super(CommunityType.LAB, name, description, creator);
    }
}
