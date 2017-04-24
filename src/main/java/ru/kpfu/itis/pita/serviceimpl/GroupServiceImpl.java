package ru.kpfu.itis.pita.serviceimpl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.repository.WallRepository;
import ru.kpfu.itis.pita.service.GroupService;

import javax.transaction.Transactional;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 4:47 PM
 */

@Service("groupServiceImpl")
public class GroupServiceImpl extends BaseCommunityServiceImpl<Group> implements GroupService {

    private final GroupRepository groupRepository;
    private final WallRepository wallRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, WallRepository wallRepository) {
        super(groupRepository);
        this.groupRepository = groupRepository;
        this.wallRepository = wallRepository;
    }

    @Override
    @Transactional
    public Group getOne(int id) {
        Group group = super.getOne(id);
        Hibernate.initialize(group.getInterests());
        return group;
    }
}
