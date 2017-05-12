package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.StudentScore;
import ru.kpfu.itis.pita.entity.User;

import java.util.List;

/**
 * Created by volkov on 09.05.2017.
 */
public interface StudentScoreService {
    StudentScore saveClass(StudentScore studentScore);

    List<StudentScore> findAll();
    List<StudentScore> findAllBySemesterNumber(int semester_number);
    List<StudentScore> findAllByStudent(User student);
    StudentScore findOne(int id);

}
