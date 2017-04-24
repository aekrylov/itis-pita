package ru.kpfu.itis.pita.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.misc.EntityNotFoundException;
import ru.kpfu.itis.pita.service.CommunityService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 12:57 PM
 *
 * Base controller for single community
 */
@RequestMapping(path = "/community/{id}")
@PreAuthorize("isFullyAuthenticated()")
public abstract class BaseCommunityController {

    private final CommunityService service;
    private final String singleViewName;

    public BaseCommunityController(CommunityService service, String singleViewName) {
        this.service = service;
        this.singleViewName = singleViewName;
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

        map.put("community", community);
        return singleViewName;
    }
}
