package ru.kpfu.itis.pita.dto;

import ru.kpfu.itis.pita.entity.Group;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/18/17 10:38 AM
 */
public class GroupDto implements Serializable {

    private int id;

    private String name;
    private String avatar;
    private String description;

    private Collection<InterestDto> tags;

    private int members;

    public GroupDto(Group group) {
        id = group.getId();
        name = group.getName();
        avatar = group.getImageLink();
        description = group.getDescription();
        tags = group.getInterests().stream()
                .map(InterestDto::new)
                .collect(Collectors.toList());

        members = group.getMembers().size();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Collection<InterestDto> getTags() {
        return tags;
    }

    public int getMembers() {
        return members;
    }

    public String getAvatar() {
        return avatar;
    }
}
