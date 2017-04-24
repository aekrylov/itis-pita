package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.CourseCreateForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.CourseService;
import ru.kpfu.itis.pita.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 1 on 29.03.2017.
 */
@Controller
@RequestMapping(path = "/courses")
public class CoursesController extends BaseCommunitiesController<CourseCreateForm> {
    private CourseService courseService;
    private UserService userService;

    @Autowired
    public CoursesController(CourseService courseService, UserService userService) {
        super("course_create", "redirect:/communities/");
        this.courseService = courseService;
        this.userService = userService;
    }

    @ModelAttribute("names")
    public List<User> getAllWorkers() {
        return userService.findAllWorkers();
    }

    @Override
    public CourseCreateForm newCreateForm() {
        return new CourseCreateForm();
    }

    //override methods to add more annotations
    @PreAuthorize("hasAuthority('CREATE_COURSE')")
    public String doCreateGet() {
        return super.doCreateGet();
    }

    @PreAuthorize("hasAuthority('CREATE_COURSE')")
    public String doCreatePost(@ModelAttribute @Valid CourseCreateForm form, BindingResult result, ModelMap map) {
        return super.doCreatePost(form, result, map);
    }

    @Override
    protected Community createEntity(CourseCreateForm form) {
        Course course = new Course();
        course.setName(form.getName());
        course.setDescription(form.getDescription());
        course.setSchedule(form.getSchedule());
        course.setCapacity(form.getCapacity());
        course.setCreator(Helpers.getCurrentUser());

        return courseService.create(course);
    }
}
