package ru.kpfu.itis.pita.dto;

import ru.kpfu.itis.pita.entity.Community;

import java.io.Serializable;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 4:43 PM
 */
public class CommunityDto implements Serializable {

    private int id;

    private String name;
    private String avatar;
    private String description;

    private int members;

    public CommunityDto(Community community) {
        id = community.getId();
        name = community.getName();
        avatar = community.getImageLink();
        description = community.getDescription();

        members = community.getMembers().size();
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }
}
