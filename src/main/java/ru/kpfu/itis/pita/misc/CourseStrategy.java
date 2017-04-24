package ru.kpfu.itis.pita.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.form.CommunityCreateForm;
import ru.kpfu.itis.pita.form.CourseCreateForm;
import ru.kpfu.itis.pita.service.CourseService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 1:36 PM
 */
@Component
public class CourseStrategy extends CommunityStrategy<CourseCreateForm> {

    private CourseService courseService;

    @Autowired
    public CourseStrategy(CourseService courseService) {
        super(courseService);
        this.courseService = courseService;
    }

    @Override
    public CommunityCreateForm newCreateForm() {
        return new CourseCreateForm();
    }

    @Override
    public Course createEntity(CourseCreateForm form) {
        Course course = new Course();
        course.setName(form.getName());
        course.setDescription(form.getDescription());
        course.setSchedule(form.getSchedule());
        course.setCapacity(form.getCapacity());
        course.setCreator(Helpers.getCurrentUser());

        return courseService.create(course);
    }

}
