package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.User;

import java.util.List;

/**
 * Created by 1 on 29.03.2017.
 */
public interface CourseService {

    Course create(Course course);
    boolean exists(String courseName);
    List<Course> getAll();
    List<Course> getByUser(User user);
}
