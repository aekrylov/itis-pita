package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.User;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/28/17 6:39 PM
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Group findByName(String name);
    List<Group> findByMember(User user);
}
