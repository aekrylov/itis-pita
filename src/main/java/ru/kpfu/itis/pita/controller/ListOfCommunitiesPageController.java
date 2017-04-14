package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.CourseService;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.LabService;

import java.util.List;

/**
 * Created by 1 on 30.03.2017.
 */
@Controller
@RequestMapping(path = "/communities")
public class ListOfCommunitiesPageController {

    private CourseService courseService;
    private LabService labService;
    private GroupService groupService;

    @Autowired
    public ListOfCommunitiesPageController(CourseService courseService, LabService labService, GroupService groupService){
        this.courseService = courseService;
        this.labService = labService;
        this.groupService = groupService;
    }

    @GetMapping
    public String doGet(ModelMap map){
        List<Course> allCourses = courseService.getAll();
        List<Lab> allLabs = labService.getAll();
        List<Group> allGroups = groupService.getAll();
        User user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        List<Course> myCourses = courseService.getByUser(user);
        List<Lab> myLabs = labService.getByUser(user);
        List<Group> myGroups = groupService.getByUser(user);

        map.put("all_courses", allCourses);
        map.put("all_labs", allLabs);
        map.put("all_groups", allGroups);
        map.put("my_courses", myCourses);
        map.put("my_labs", myLabs);
        map.put("my_groups", myGroups);
        return "course_list";
    }
}
