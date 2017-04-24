package ru.kpfu.itis.pita.serviceimpl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.WallPost;
import ru.kpfu.itis.pita.repository.CommunityRepository;
import ru.kpfu.itis.pita.repository.GroupWallRepository;
import ru.kpfu.itis.pita.service.CommunityService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 2:41 PM
 */
@Component
public abstract class CommunityServiceImpl<T extends Community> implements CommunityService<T> {

    @Autowired
    private final CommunityRepository<T> repository;
    @Autowired
    private GroupWallRepository wallRepository;

    public CommunityServiceImpl(CommunityRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T getOne(int id) {
        T community = repository.findOne(id);
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

}
