package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 11:21 AM
 */

@Entity
@Table(name = "groups")
public class Group {
    //TODO

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    @ManyToMany
    @JoinTable(name = "groups_interests")
    private Collection<Interest> interests;

    @ManyToMany
    @JoinTable(name = "groups_members", joinColumns = {
            @JoinColumn(name = "group_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    })
    private Collection<User> members;

    @ManyToMany
    @JoinTable(name = "groups_admins")
    private Collection<User> admins;

    public Group() {}

    public Group(String name, String description, User creator, Collection<Interest> interests) {
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.interests = interests;
        //todo add creator to members?
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Collection<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Collection<Interest> interests) {
        this.interests = interests;
    }

    public Collection<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Collection<User> admins) {
        this.admins = admins;
    }

    public Collection<User> getMembers() {
        return members;
    }

    public void setMembers(Collection<User> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        return name.equals(group.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
