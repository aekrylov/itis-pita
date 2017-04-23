package ru.kpfu.itis.pita.controller;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.*;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.CourseService;
import ru.kpfu.itis.pita.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by volkov on 09.04.2017.
 */
@Controller
@RequestMapping(path = "/courses/{id}")
@PreAuthorize("isFullyAuthenticated()")
public class CoursePageController {

    @Autowired
    private CourseService courseService;
    private UserService userService;
    @Autowired
    public CoursePageController(CourseService courseService,UserService userService) {

        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping
    public String doGet(ModelMap modelMap, @PathVariable Integer id){
        Course course = courseService.getOne(id);
        if (course!= null) {
            modelMap.addAttribute("course", course);
        }
        else{
            modelMap.addAttribute("error","Course not found");
        }

        return "course_page";
    }


    @PostMapping
    public String doPost(ModelMap modelMap,@PathVariable Integer id,
                         @RequestParam(value = "form")String form){
        Course course = courseService.getOne(id);
        User currentUser = Helpers.getCurrentUser();
        if(form.equals("join")){
            course.getMembers().add(currentUser);
            courseService.saveAndFlush(course);
        }
        else{
            if(form.equals("leave")){
                course.getMembers().remove(currentUser);
                course.getAdmins().remove(currentUser);
                courseService.saveAndFlush(course);
                course.getMembers().size();
            }

        }
        modelMap.addAttribute("course", course);
        return "course_page";
    }
}
