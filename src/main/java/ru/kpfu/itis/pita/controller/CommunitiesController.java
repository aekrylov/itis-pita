package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.*;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.CommunityService;
import ru.kpfu.itis.pita.service.InterestService;
import ru.kpfu.itis.pita.service.UserService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 10:42 PM
 */
@Controller
@RequestMapping(path = "/communities")
@PreAuthorize("isFullyAuthenticated()")
public class CommunitiesController {

    private UserService userService;
    private InterestService interestService;
    private CommunityService<Community> communityService;

    @Autowired
    public CommunitiesController(UserService userService, InterestService interestService, CommunityService<Community> communityService) {
        this.userService = userService;
        this.interestService = interestService;
        this.communityService = communityService;
    }


    @GetMapping(path = "/")
    @Transactional
    public String listAll(ModelMap map) {
        Class<?>[] classes = new Class[]{Group.class, Course.class, Lab.class, Event.class};
        //todo reasonable hierarchy

        List<Community> allComms = communityService.getAll();
        List<Group> allGroups = Helpers.filterByType(allComms, Group.class);
        List<Course> allCourses = Helpers.filterByType(allComms, Course.class);
        List<Lab> allLabs = Helpers.filterByType(allComms, Lab.class);

        User user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        //todo lazy init
        //user is stored in http session and hence is detached from db session
        user = userService.findByEmail(user.getEmail());

        Collection<Community> myComms = user.getCommunities(); //returns all groups and child entities as well
        Collection<Group> myGroups = Helpers.filterByType(myComms, Group.class);
        Collection<Course> myCourses = Helpers.filterByType(myComms, Course.class);
        Collection<Lab> myLabs = Helpers.filterByType(myComms, Lab.class);

        map.put("all_courses", allCourses);
        map.put("all_labs", allLabs);
        map.put("all_groups", allGroups);
        map.put("my_courses", myCourses);
        map.put("my_labs", myLabs);
        map.put("my_groups", myGroups);

        List<Interest> tags = interestService.getAll();
        map.put("tags", tags);

        return "communities_list";
    }
}
