package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.InterestService;
import ru.kpfu.itis.pita.service.UserService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 10:42 PM
 */
@Controller
@RequestMapping(path = "/communities")
@PreAuthorize("isFullyAuthenticated()")
public class CommunitiesController {

    private GroupService groupService;
    private UserService userService;
    private InterestService interestService;

    @Autowired
    public CommunitiesController(GroupService groupService, UserService userService, InterestService interestService) {
        this.groupService = groupService;
        this.userService = userService;
        this.interestService = interestService;
    }

/*

TODO
TODO
    @GetMapping(path = "/")
    @Transactional
    public String listAll(ModelMap map) {
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

        List<Interest> tags = interestService.getAll();
        map.put("tags", tags);

        return "communities_list";
    }
*/
}
