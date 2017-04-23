package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.WallComment;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/23/17 7:28 PM
 */
public interface WallCommentRepository extends JpaRepository<WallComment, Integer> {
}
