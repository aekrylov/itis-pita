package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.CommunityCreateForm;
import ru.kpfu.itis.pita.misc.EntityNotFoundException;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.CommunityService;

import javax.validation.Valid;

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
public class CommunityController<E extends Community> {

    private final CommunityService<E> service;
    private final String singleViewName;

    private E community;

    public CommunityController(CommunityService<E> service, String singleViewName) {
        this.service = service;
        this.singleViewName = singleViewName;
    }

    @Autowired
    public CommunityController(@Qualifier("communityService") CommunityService<E> service) {
        this(service, "communities_one");
    }

    @ModelAttribute("id")
    public int groupId(@PathVariable int id) {
        return id;
    }

    @ModelAttribute("community")
    public E community(@PathVariable("id") int id) {
        E community = service.getOne(id);
        if(community == null) {
            throw new EntityNotFoundException(id);
        }

        this.community = community;
        return community;
    }

    @ModelAttribute("type")
    public final Community.CommunityType type(@ModelAttribute("community") E community) {
        return community.getType();
    }

    protected CommunityCreateForm<E> editForm(E community) {
        return new CommunityCreateForm<E>().fromEntity(community);
    }

    @GetMapping(path = "/")
    public String getOne() {
        return singleViewName;
    }

    @PostMapping(path = "/")
    public String doPost(ModelMap modelMap,@PathVariable Integer id,
                         @RequestParam(value = "action") String action) {
        User currentUser = Helpers.getCurrentUser();

        if(action.equals("join")){
            community.getMembers().add(currentUser);
        }
        else if(action.equals("leave")){
            community.getMembers().remove(currentUser);
            community.getAdmins().remove(currentUser);
        }
        community = service.save(community);
        modelMap.addAttribute("community", community);
        return "communities_one";
    }

    @GetMapping(path = "/edit")
    public String doEditGet(ModelMap map) {
        map.put("form", editForm(community));
        //todo
        return "communities_create";
    }

    private boolean isValid(BindingResult result) {
        return !(result.hasErrors());
    }

    @PostMapping("/edit")
    public String doEditPost(@ModelAttribute("form") @Valid CommunityCreateForm<E> form, BindingResult result) {
        if(!isValid(result)) {
            //todo
            return "communities_create";
        }

        form.toEntity(community);

        service.save(community);
        return "communities_one";

    }
}
