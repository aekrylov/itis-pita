package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.Community;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 4:29 PM
 */
public interface BaseCommunityRepository<E extends Community> extends JpaRepository<E, Integer> {

    E findByName(String name);
}
