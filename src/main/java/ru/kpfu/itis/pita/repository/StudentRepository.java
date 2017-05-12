package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.Student;

import java.util.List;

/**
 * Created by volkov on 10.05.2017.
 */
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findAllByStartYear(int startYear);
}

