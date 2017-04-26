package ru.kpfu.itis.pita.serviceimpl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.WallPost;
import ru.kpfu.itis.pita.repository.BaseCommunityRepository;
import ru.kpfu.itis.pita.repository.WallRepository;
import ru.kpfu.itis.pita.service.CommunityService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 2:41 PM
 */
public abstract class BaseCommunityServiceImpl<T extends Community> implements CommunityService<T> {

    private final BaseCommunityRepository<T> repository;
    private WallRepository wallRepository;

    public BaseCommunityServiceImpl(BaseCommunityRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T getOne(int id) {
        T community = repository.findOne(id);
        if(community == null)
            return null;
        //since lazy collections will be needed, init them now
        Hibernate.initialize(community.getAdmins());
        Hibernate.initialize(community.getWall());
        return community;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean exists(String name) {
        return repository.findByName(name) != null;
    }

    @Override
    public T create(T community) {
        community.getMembers().add(community.getCreator());
        community.getAdmins().add(community.getCreator());
        return repository.save(community);
    }

    @Override
    public WallPost addPost(WallPost post) {
        return wallRepository.save(post);
    }

    @Override
    public WallPost addPost(int communityId, WallPost post) {
        Community community = repository.findOne(communityId);
        post.setCommunity(community);
        return wallRepository.save(post);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Autowired
    public void setWallRepository(WallRepository wallRepository) {
        this.wallRepository = wallRepository;
    }
}
