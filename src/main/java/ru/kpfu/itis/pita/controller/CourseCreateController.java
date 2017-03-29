package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.form.CourseCreateForm;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.CourseService;

import javax.validation.Valid;

/**
 * Created by 1 on 29.03.2017.
 */
@Controller
@RequestMapping(path = "courses/create")
public class CourseCreateController {
    private CourseService courseService;

    @Autowired
    public CourseCreateController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String doGet(){
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
        //todo save image
        if(courseService.exists(form.getName())){
            map.put("error", "Course exists");
            return "course_create";
        }
        courseService.create(course);

        return "redirect:course_create";
    }
}
