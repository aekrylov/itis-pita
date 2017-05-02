package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.AcademicGroup;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 5:18 PM
 */
public interface AcademicGroupRepository extends JpaRepository<AcademicGroup, Integer> {

    AcademicGroup findOneByNameEquals(String name);
    List<AcademicGroup> findAllByNameIn(List<String> names);
}
