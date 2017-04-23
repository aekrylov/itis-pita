package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.WallComment;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/23/17 7:28 PM
 */
public interface WallService {

    WallComment addComment(WallComment comment);
    WallComment addComment(int groupId, int postId, WallComment comment);
}
