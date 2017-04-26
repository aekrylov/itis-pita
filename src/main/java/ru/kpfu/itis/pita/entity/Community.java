package ru.kpfu.itis.pita.entity;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.SortedSet;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 3:38 PM
 */

@Entity
@Table(name = "communities")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Community {

    public enum CommunityType {
        GROUP(Group.class),
        LAB(Lab.class),
        EVENT(Event.class),
        COURSE(Course.class);

        private Class<? extends Community> clazz;

        CommunityType(Class<? extends Community> clazz) {
            this.clazz = clazz;
        }

        public static CommunityType getByClass(Class<? extends Community> clazz) {
            for(CommunityType type: values()) {
                if(type.clazz.isAssignableFrom(clazz))
                    return type;
            }
            return null;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected CommunityType type;

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String imageLink;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User creator;


    @ManyToMany
    @JoinTable(name = "communities_members", joinColumns = {
            @JoinColumn(name = "community_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    })
    private Collection<User> members = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "communities_admins", joinColumns = {
            @JoinColumn(name = "community_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "admin_id", referencedColumnName = "id")
    })
    private Collection<User> admins = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "community")
    @SortNatural
    private SortedSet<WallPost> wall;

    public Community() {
    }

    public Community(CommunityType type) {
        this.type = type;
    }

    public Community(CommunityType type, String name, String description, User creator) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.creator = creator;
    }

    public CommunityType getType() {
        return type;
    }

    public void setType(CommunityType type) {
        this.type = type;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

    public Collection<User> getMembers() {
        return members;
    }

    public void setMembers(Collection<User> members) {
        this.members = members;
    }

    public Collection<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Collection<User> admins) {
        this.admins = admins;
    }

    public SortedSet<WallPost> getWall() {
        return wall;
    }

    public void setWall(SortedSet<WallPost> wall) {
        this.wall = wall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Community)) return false;

        Community community = (Community) o;

        if (getId() != community.getId()) return false;
        return getName().equals(community.getName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
