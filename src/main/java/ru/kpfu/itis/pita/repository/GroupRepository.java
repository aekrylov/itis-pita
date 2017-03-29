package ru.kpfu.itis.pita.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.pita.entity.Group;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/28/17 6:39 PM
 */
public interface GroupRepository extends CrudRepository<Group, Integer> {

    Group findByName(String name);
}
