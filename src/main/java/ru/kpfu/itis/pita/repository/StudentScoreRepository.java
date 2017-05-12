package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.StudentScore;
import ru.kpfu.itis.pita.entity.User;

import java.util.List;

/**
 * Created by volkov on 09.05.2017.
 */
public interface StudentScoreRepository extends JpaRepository<StudentScore,Integer>{

    List<StudentScore> findAllByStudent(User student);
    List<StudentScore> findAllBySemesterNumber(int semester_number);
}
