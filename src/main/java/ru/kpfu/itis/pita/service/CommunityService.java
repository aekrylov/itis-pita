package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.WallPost;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 1:37 PM
 */
public interface CommunityService<T extends Community> {

    T getOne(int id);
    List<T> getAll();
    boolean exists(String name);
    T create(T entity);
    T save(T entity);

    WallPost addPost(WallPost post);
    WallPost addPost(int groupId, WallPost post);
}
