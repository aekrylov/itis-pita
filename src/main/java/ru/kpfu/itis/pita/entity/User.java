package ru.kpfu.itis.pita.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/17/17 1:28 PM
 */

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String aboutMe;

    private String passwordRaw;
    private String passwordHash;

    private String secretQuestion;
    private String secretAnswer;

    private UserRole role;

    private boolean active = true;

    private Collection<Community> communities;

    public User() {}

    public User(String name, String email, String phone, String passwordHash, String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
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
    @JoinTable(name = "communities_members", joinColumns = {
            @JoinColumn(name = "user_id")
    }, inverseJoinColumns = {@JoinColumn(name = "community_id")})
    public Collection<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(Collection<Community> communities) {
        this.communities = communities;
    }

    @Transient
    public String getPasswordRaw() {
        return passwordRaw;
    }

    public void setPasswordRaw(String passwordRaw) {
        this.passwordRaw = passwordRaw;
    }

    @Column(name = "about_me")
    @Lob
    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        //todo
        if (id != user.getId()) return false;
        return email.equals(user.getEmail());
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
