package ru.kpfu.itis.pita.serviceimpl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.WallPost;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.repository.GroupWallRepository;
import ru.kpfu.itis.pita.service.GroupService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 4:47 PM
 */

@Service("groupServiceImpl")
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupWallRepository wallRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, GroupWallRepository wallRepository) {
        this.groupRepository = groupRepository;
        this.wallRepository = wallRepository;
    }

    @Override
    public boolean exists(String name) {
        return groupRepository.findByName(name) != null;
    }

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    @Transactional
    public Group getOne(int id) {
        Group group = groupRepository.findOne(id);
        //since lazy collections will be needed, init them now
        Hibernate.initialize(group.getAdmins());
        Hibernate.initialize(group.getWall());
        Hibernate.initialize(group.getInterests());
        return group;
    }

    @Override
    public WallPost addPost(WallPost post) {
        return wallRepository.save(post);
    }

    @Override
    public WallPost addPost(int groupId, WallPost post) {
        Group group = groupRepository.findOne(groupId);
        post.setGroup(group);
        return wallRepository.save(post);
    }

}
