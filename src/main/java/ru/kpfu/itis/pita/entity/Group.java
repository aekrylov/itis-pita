package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 11:21 AM
 */

@Entity
@Table(name = "groups")
@PrimaryKeyJoinColumn(name = "id")
public class Group extends Community {

    @ManyToMany
    @JoinTable(name = "groups_interests")
    private Collection<Interest> interests;

    public Group() {
        super(CommunityType.GROUP);
    }

    public Group(String name, String description, User creator, Collection<Interest> interests) {
        super(CommunityType.GROUP, name, description, creator);
        this.interests = interests;
    }

    public Collection<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Collection<Interest> interests) {
        this.interests = interests;
    }

}
