package ru.kpfu.itis.pita.controller;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.CourseService;

import java.util.List;

/**
 * Created by volkov on 09.04.2017.
 */
@Controller
@RequestMapping(path = "/course")
//@PreAuthorize("hasAuthority('USER')")  to do
public class CoursePageController {

    @Autowired
    private CourseService courseService;
    @Autowired
    public CoursePageController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String doGet(ModelMap modelMap, @RequestParam(value = "id") int id){
        Course course = courseService.getById(id);
        course = courseService.initializeAndUnproxy(course);
        if (course!= null) {
            modelMap.addAttribute("course", course);
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User u =course.getCreator();
            System.out.println(u.getName());
            Boolean participant = false;
            if (course.getMembers()!=null ){
                participant = course.getMembers().contains(ud.getUser());
            }
            modelMap.addAttribute("participant",participant);
        }
        else{
            modelMap.addAttribute("error","Course not found");
        }

        return "course_page";
    }
}
