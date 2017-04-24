package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.WallPost;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/29/17 4:47 PM
 */
public interface GroupService extends CommunityService<Group> {

    WallPost addPost(WallPost post);
    WallPost addPost(int groupId, WallPost post);
}
