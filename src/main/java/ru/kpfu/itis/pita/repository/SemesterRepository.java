package ru.kpfu.itis.pita.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.pita.entity.Semester;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/31/17 11:05 PM
 */
public interface SemesterRepository extends CrudRepository<Semester, Integer> {
}
