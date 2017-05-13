package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.Semester;
import ru.kpfu.itis.pita.entity.Subject;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 5:24 PM
 */
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findByName(String name);
    Subject findByNameAndSemester(String name, Semester semester);
}
