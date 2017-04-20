package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Course;

import java.util.List;

/**
 * Created by 1 on 29.03.2017.
 */
public interface CourseService {

    Course create(Course course);
    boolean exists(String courseName);
    List<Course> getAll();


}
