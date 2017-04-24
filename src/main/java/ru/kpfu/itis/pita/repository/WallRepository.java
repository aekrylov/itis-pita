package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.WallPost;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 2:27 PM
 */
public interface WallRepository extends JpaRepository<WallPost, Integer> {

    WallPost findByCommunityIdAndId(int communityId, int id);
}
