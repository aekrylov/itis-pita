package ru.kpfu.itis.pita.serviceimpl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Interest;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.InterestService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 4:47 PM
 */

@Service("groupServiceImpl")
public class GroupServiceImpl extends BaseCommunityServiceImpl<Group> implements GroupService {

    private InterestService interestService;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, InterestService interestService) {
        super(groupRepository);
        this.interestService = interestService;
    }

    @Override
    @Transactional
    public Group getOne(int id) {
        Group group = super.getOne(id);
        Hibernate.initialize(group.getInterests());
        return group;
    }

    @Override
    public Group save(Group entity) {
        List<Interest> newInterests = entity.getInterests().stream()
                .map(interest -> interestService.save(interest))
                .collect(Collectors.toList());
        entity.setInterests(newInterests);
        return super.save(entity);
    }
}
