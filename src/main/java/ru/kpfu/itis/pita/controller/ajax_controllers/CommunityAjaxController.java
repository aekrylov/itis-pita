package ru.kpfu.itis.pita.controller.ajax_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.pita.dto.CourseDto;
import ru.kpfu.itis.pita.dto.GroupDto;
import ru.kpfu.itis.pita.dto.LabDto;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.UserService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/18/17 11:02 AM
 */

@RestController
@RequestMapping(path = "/ajax/groups")
public class CommunityAjaxController {

    private GroupService groupService;
    private UserService userService;

    @Autowired
    public CommunityAjaxController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public Map<String, Object> listAll() {
        List<Group> allGroups = groupService.getAll(); //returns all groups and child entities as well

        User user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        //todo lazy init
        //user is stored in http session and hence is detached from db session
        user = userService.findByEmail(user.getEmail());

        Collection<Group> myGroups = user.getGroups(); //returns all groups and child entities as well

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("all_groups", toDtoList(allGroups, Group.class, GroupDto::new));
        returnMap.put("all_courses", toDtoList(allGroups, Course.class, CourseDto::new));
        returnMap.put("all_labs", toDtoList(allGroups, Lab.class, LabDto::new));

        returnMap.put("my_groups", toDtoList(myGroups, Group.class, GroupDto::new));
        returnMap.put("my_courses", toDtoList(myGroups, Course.class, CourseDto::new));
        returnMap.put("my_labs", toDtoList(myGroups, Lab.class, LabDto::new));
        return returnMap;
    }

    private <T extends Group, D extends GroupDto> List<D> toDtoList(Collection<Group> allGroups, Class<T> clazz,
                                                                    Function<T, D> dtoMapper) {
        return Helpers.filterByType(allGroups, clazz).stream()
                .map(dtoMapper)
                .collect(Collectors.toList());
    }
}
