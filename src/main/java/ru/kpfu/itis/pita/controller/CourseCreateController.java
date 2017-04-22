package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.CourseCreateForm;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.CourseService;
import ru.kpfu.itis.pita.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 1 on 29.03.2017.
 */
@Controller
@RequestMapping(path = "courses/create")
@PreAuthorize("hasAuthority('CREATE_COURSE')")
public class CourseCreateController {
    private CourseService courseService;
    private UserService userService;

    @Autowired
    public CourseCreateController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping
    public String doGet(ModelMap map){
        List<User> names = userService.findAllWorkers();
        map.put("names", names);
        return "course_create";
    }

    @PostMapping
    public String doPost(@ModelAttribute @Valid CourseCreateForm form, BindingResult result, ModelMap map){
        if(result.hasErrors()){
            return "course_create";
        }

        Course course = new Course();
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        course.setName(form.getName());
        course.setDescription(form.getDescription());
        course.setSchedule(form.getSchedule());
        course.setCapacity(form.getCapacity());
        course.setCreator(ud.getUser());
        if(courseService.exists(form.getName())){
            map.put("error", "Course exists");
            return "course_create";
        }
        courseService.create(course);

        return "redirect:/communities/";
    }
}
