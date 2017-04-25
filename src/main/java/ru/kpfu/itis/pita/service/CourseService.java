package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Course;

/**
 * Created by 1 on 29.03.2017.
 */
public interface CourseService extends CommunityService<Course> {

    Course saveAndFlush(Course course);
}
