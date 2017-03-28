package ru.kpfu.itis.pita.entity;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.Date;
import java.util.SortedSet;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 12:05 AM
 */

@Entity
@Table(name = "group_wall")
public class WallPost implements Comparable<WallPost> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private Group group;

    @ManyToOne(optional = false)
    private User author;

    @Lob
    private String text;

    @Column(nullable = false)
    private Date timestamp = new Date();

    //tODO photo

    @Column(name = "video_link")
    private String videoLink;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "post")
    @SortNatural
    private SortedSet<WallComment> comments;

    public WallPost() {}

    public WallPost(Group group, User author, String text, String videoLink) {
        this.group = group;
        this.author = author;
        this.text = text;
        this.videoLink = videoLink;
    }

    public WallPost(Group group, User author, String text, Date timestamp, String videoLink) {
        this.group = group;
        this.author = author;
        this.text = text;
        this.timestamp = timestamp;
        this.videoLink = videoLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WallPost)) return false;

        WallPost groupPost = (WallPost) o;

        if (!group.equals(groupPost.group)) return false;
        if (!author.equals(groupPost.author)) return false;
        return timestamp.equals(groupPost.timestamp);
    }

    @Override
    public int hashCode() {
        int result = group.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }

    /**
     * Compare by timestamp
     */
    @Override
    public int compareTo(WallPost o) {
        if(o == null)
            return 1;

        return timestamp.compareTo(o.timestamp);
    }


}
