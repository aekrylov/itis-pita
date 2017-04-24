package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.service.CommunityService;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 2:41 PM
 */
public abstract class CommunityServiceImpl<T extends Group> implements CommunityService<T> {

    private JpaRepository<T, Integer> repository;
    private GroupRepository groupRepository;

    public CommunityServiceImpl(JpaRepository<T, Integer> repository, GroupRepository groupRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
    }

    @Override
    public T getOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean exists(String name) {
        return groupRepository.findByName(name) != null;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }
}
