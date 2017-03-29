package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 12:17 AM
 */

@Entity
@Table(name = "wall_comments")
public class WallComment implements Comparable<WallComment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private WallPost post;

    @ManyToOne(optional = false)
    private User author;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Date timestamp = new Date();

    public WallComment() { }

    public WallComment(WallPost post, User author, String text) {
        this.post = post;
        this.author = author;
        this.text = text;
    }

    public WallComment(WallPost post, User author, String text, Date timestamp) {
        this.post = post;
        this.author = author;
        this.text = text;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WallPost getPost() {
        return post;
    }

    public void setPost(WallPost post) {
        this.post = post;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WallComment)) return false;

        WallComment that = (WallComment) o;

        if (!post.equals(that.post)) return false;
        if (!author.equals(that.author)) return false;
        return timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = post.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }

    /**
     * Compare by timestamp
     * @param o
     */
    @Override
    public int compareTo(WallComment o) {
        if(o == null)
            return 1;

        return timestamp.compareTo(o.getTimestamp());
    }
}
