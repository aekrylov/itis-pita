package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.WallComment;
import ru.kpfu.itis.pita.entity.WallPost;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.repository.WallCommentRepository;
import ru.kpfu.itis.pita.repository.WallRepository;
import ru.kpfu.itis.pita.service.WallService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/23/17 7:30 PM
 */
@Service
public class WallServiceImpl implements WallService {
    private GroupRepository groupRepository;
    private WallCommentRepository commentRepository;
    private WallRepository wallRepository;

    @Autowired
    public WallServiceImpl(GroupRepository groupRepository,
                           WallCommentRepository commentRepository,
                           WallRepository wallRepository) {
        this.groupRepository = groupRepository;
        this.commentRepository = commentRepository;
        this.wallRepository = wallRepository;
    }

    @Override
    public WallComment addComment(WallComment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public WallComment addComment(int groupId, int postId, WallComment comment) {
        WallPost post = wallRepository.findByCommunityIdAndId(groupId, postId);
        comment.setPost(post);
        return addComment(comment);
    }
}
