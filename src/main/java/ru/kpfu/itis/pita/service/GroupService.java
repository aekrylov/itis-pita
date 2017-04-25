package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.WallPost;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 4:47 PM
 */
public interface GroupService {

    boolean exists(String name);
    void save(Group group);
    Group create(Group group);
    List<Group> getAll();
    Group getOne(int id);
    WallPost addPost(WallPost post);
    WallPost addPost(int groupId, WallPost post);
}
