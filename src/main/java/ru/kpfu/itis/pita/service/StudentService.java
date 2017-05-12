package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Student;

import java.util.List;

/**
 * Created by volkov on 10.05.2017.
 */
public interface StudentService  {
    List<Student> findAllByStartYear(int year);


}
