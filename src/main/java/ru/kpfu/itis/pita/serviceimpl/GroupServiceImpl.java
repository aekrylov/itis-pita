package ru.kpfu.itis.pita.serviceimpl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.service.GroupService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 4:47 PM
 */

@Service("groupServiceImpl")
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;

    @Autowired
    public GroupServiceImpl(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean exists(String name) {
        return repository.findByName(name) != null;
    }

    @Override
    public Group create(Group group) {
        return repository.save(group);
    }

    @Override
    public List<Group> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Group findOne(int id) {
        Group group = repository.findOne(id);
        //since lazy collections will be needed, init them now
        Hibernate.initialize(group);
        return group;
    }

}
