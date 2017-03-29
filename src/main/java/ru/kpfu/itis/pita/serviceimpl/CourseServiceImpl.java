package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.repository.CourseRepository;
import ru.kpfu.itis.pita.service.CourseService;

/**
 * Created by 1 on 29.03.2017.
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public boolean exists(String courseName) {
        return courseRepository.findByName(courseName) != null;
    }
}
