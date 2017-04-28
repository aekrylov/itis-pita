package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.misc.EntityNotFoundException;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.CommunityService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 12:57 PM
 *
 * Base controller for single community. Child classes should override controller RequestMapping,
 * and also pass custom service to the constructor
 */
@Controller
@RequestMapping(path = "/community/{id}")
@PreAuthorize("isFullyAuthenticated()")
public class CommunityController {

    private final CommunityService service;
    private final String singleViewName;

    public CommunityController(CommunityService service, String singleViewName) {
        this.service = service;
        this.singleViewName = singleViewName;
    }

    @Autowired
    public CommunityController(@Qualifier("communityService") CommunityService service) {
        this(service, "communities_one");
    }

    @ModelAttribute("id")
    public int groupId(@PathVariable int id) {
        return id;
    }

    @GetMapping(path = "/")
    public String getOne(@PathVariable("id") int id, ModelMap map) {
        Community community = service.getOne(id);
        if(community == null) {
            throw new EntityNotFoundException(id);
        }

        map.put("course", community);
        return singleViewName;
    }

    @PostMapping(path = "/")
    public String doPost(ModelMap modelMap,@PathVariable Integer id,
                         @RequestParam(value = "action") String action) {
        Community community = service.getOne(id);
        User currentUser = Helpers.getCurrentUser();

        if(action.equals("join")){
            community.getMembers().add(currentUser);
        }
        else if(action.equals("leave")){
            community.getMembers().remove(currentUser);
            community.getAdmins().remove(currentUser);
        }
        community = service.save(community);
        modelMap.addAttribute("course", community);
        return singleViewName;
    }

}
