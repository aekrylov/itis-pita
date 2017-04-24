package ru.kpfu.itis.pita.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Interest;
import ru.kpfu.itis.pita.form.CommunityCreateForm;
import ru.kpfu.itis.pita.form.GroupCreateForm;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.InterestService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 1:09 PM
 */
@Component
public class GroupStrategy extends CommunityStrategy<GroupCreateForm> {

    private GroupService groupService;
    private InterestService interestService;

    @Autowired
    public GroupStrategy(GroupService groupService, InterestService interestService) {
        super(groupService);
        this.groupService = groupService;
        this.interestService = interestService;
    }

    @Override
    public CommunityCreateForm newCreateForm() {
        return new GroupCreateForm();
    }

    @Override
    public Group createEntity(GroupCreateForm form) {
        Group group = new Group();
        group.setCreator(Helpers.getCurrentUser());

        String tags = form.getInterests();
        String[] separated_tags = tags.split(",");
        List<Interest> interests = Arrays.stream(separated_tags)
                .map(Interest::new)
                .map(interest -> interestService.save(interest))
                .collect(Collectors.toList());

        group.setInterests(interests);
        group.setName(form.getName());
        group.setDescription(form.getDescription());
        //temp?
        //todo move to view?
        group.setImageLink("/static/img/avatar_example.png");

        return groupService.create(group);
    }
}
