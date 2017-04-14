package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.service.GroupService;

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
    public List<Group> getByUser(User user) {
        return repository.findByMember(user);
    }
}
