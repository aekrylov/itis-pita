package ru.kpfu.itis.pita.dto;

import ru.kpfu.itis.pita.entity.Group;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/18/17 10:38 AM
 */
public class GroupDto extends CommunityDto {

    private Collection<InterestDto> tags;

    public GroupDto(Group group) {
        super(group);
        tags = group.getInterests().stream()
                .map(InterestDto::new)
                .collect(Collectors.toList());
    }

    public Collection<InterestDto> getTags() {
        return tags;
    }

}
