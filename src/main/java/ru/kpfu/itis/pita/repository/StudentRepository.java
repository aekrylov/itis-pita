package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.pita.entity.Student;

import java.util.List;

/**
 * Created by volkov on 10.05.2017.
 */
public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query("select s from Student as s  join s.academicGroup g where g.start_year = ?1 " +
            "order by s.name desc")
    List<Student> findAllByStartYear(int startYear);
}

