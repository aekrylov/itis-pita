package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.CourseService;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.LabService;
import ru.kpfu.itis.pita.service.UserService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by 1 on 30.03.2017.
 */
@Controller
@RequestMapping(path = "/communities")
@PreAuthorize("isFullyAuthenticated()")
public class ListOfCommunitiesPageController {

    private CourseService courseService;
    private LabService labService;
    private GroupService groupService;
    private UserService userService;

    @Autowired
    public ListOfCommunitiesPageController(CourseService courseService,
                                           LabService labService,
                                           GroupService groupService,
                                           UserService userService){
        this.courseService = courseService;
        this.labService = labService;
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping
    @Transactional
    public String doGet(ModelMap map){
        List<Group> allGroups = groupService.getAll(); //returns all groups and child entities as well
        List<Course> allCourses = Helpers.filterByType(allGroups, Course.class);
        List<Lab> allLabs = Helpers.filterByType(allGroups, Lab.class);

        User user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        //todo lazy init
        //user is stored in http session and hence is detached from db session
        user = userService.findByEmail(user.getEmail());

        Collection<Group> myGroups = user.getGroups(); //returns all groups and child entities as well
        Collection<Course> myCourses = Helpers.filterByType(myGroups, Course.class);
        Collection<Lab> myLabs = Helpers.filterByType(myGroups, Lab.class);

        map.put("all_courses", allCourses);
        map.put("all_labs", allLabs);
        map.put("all_groups", allGroups);
        map.put("my_courses", myCourses);
        map.put("my_labs", myLabs);
        map.put("my_groups", myGroups);
        return "communities_list";
    }
}
