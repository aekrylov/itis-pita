package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/17/17 1:28 PM
 */

@Entity
@Table(name = "users")
public class User {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String passwordHash;

    private String secretQuestion;
    private String secretAnswer;

    //TODO enum
    private String role;

    private boolean active;

    private Collection<Group> groups;

    public User() {}

    public User(String name, String email, String phone, String passwordHash, String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "password_hash", nullable = false)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Column(name = "secret_question")
    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    @Column(name = "secret_answer")
    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "active", nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "groups_members", joinColumns = {
            @JoinColumn(name = "user_id")
    }, inverseJoinColumns = {@JoinColumn(name = "group_id")})
    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d; name=%s; email=%s;]", id, name, email);
    }
}
