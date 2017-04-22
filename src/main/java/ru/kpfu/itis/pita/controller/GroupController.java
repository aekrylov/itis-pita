package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.misc.EntityNotFoundException;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.InterestService;
import ru.kpfu.itis.pita.service.UserService;

/**
 * Created by 1 on 30.03.2017.
 */
@Controller
@RequestMapping(path = "/group/{id}/")
@PreAuthorize("isFullyAuthenticated()")
public class GroupController {

    private GroupService groupService;
    private UserService userService;
    private InterestService interestService;

    @ModelAttribute("id")
    public int groupId(@PathVariable int id) {
        return id;
    }

    @Autowired
    public GroupController(GroupService groupService, UserService userService, InterestService interestService) {
        this.groupService = groupService;
        this.userService = userService;
        this.interestService = interestService;
    }

    @GetMapping(path = "/")
    public String findOne(@PathVariable Integer id, ModelMap map) {
        Group group = groupService.getOne(id);
        if(group == null) {
            throw new EntityNotFoundException(id);
        }

        map.put("group", group);
        return "communities_one";
    }

}
