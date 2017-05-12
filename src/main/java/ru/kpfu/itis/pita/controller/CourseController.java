package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.form.community.CommunityCreateForm;
import ru.kpfu.itis.pita.form.community.CourseCreateForm;
import ru.kpfu.itis.pita.service.CourseService;

/**
 * Created by volkov on 28.04.2017.
 */
@Controller
@RequestMapping(path = "/course/{id}/")
public class CourseController extends CommunityController<Course>  {
    @Autowired
    public CourseController(CourseService courseService) {
        super(courseService);
    }

    @Override
    protected CommunityCreateForm<Course> editForm(Course community) {
        return new CourseCreateForm().fromEntity(community);
    }
}
