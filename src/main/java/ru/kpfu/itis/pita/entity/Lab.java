package ru.kpfu.itis.pita.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 11:22 AM
 */

@Entity
@Table(name = "labs")
@PrimaryKeyJoinColumn(name = "id")
public class Lab extends Group {

    //TODO

    public Lab() {
        setType(CommunityType.LAB);
    }

    public Lab(String name, String description, User creator, Collection<Interest> interests) {
        super(name, description, creator, interests);
        setType(CommunityType.LAB);
    }
}
