package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.AcademicGroup;
import ru.kpfu.itis.pita.entity.Semester;
import ru.kpfu.itis.pita.entity.TimetableClass;
import ru.kpfu.itis.pita.entity.User;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 4:33 PM
 */
public interface TimetableClassRepository extends JpaRepository<TimetableClass, Integer> {

    List<TimetableClass> findAllBySubjectSemester(Semester semester);
    List<TimetableClass> findAllByGroupsContains(AcademicGroup group);
    List<TimetableClass> findAllByTeacher(User teacher);
}
