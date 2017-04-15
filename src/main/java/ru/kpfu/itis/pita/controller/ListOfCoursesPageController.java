package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.service.CourseService;

import java.util.List;

/**
 * Created by 1 on 30.03.2017.
 */
@Controller
@RequestMapping(path = "/courses")
public class ListOfCoursesPageController {

    private CourseService courseService;

    @Autowired
    public ListOfCoursesPageController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public String doGet(ModelMap modelMap){
        List<Course> courses = courseService.getAll();
        modelMap.addAllAttributes(courses);
        modelMap.addAttribute("courses", courses);
        return "course_list";
    }
}
