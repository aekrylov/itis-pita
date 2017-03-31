package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.form.GroupCreateForm;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.InterestService;

import javax.validation.Valid;

/**
 * Created by taa on 30.03.2017.
 */
@Controller
@RequestMapping(path = "groups/create")
public class GroupCreateController {
    private GroupService groupService;
    private InterestService interestService;

    public GroupCreateController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String doGet(){
        return "groupOfInterestCreation";
    }

    @PostMapping
    public String doPost(@ModelAttribute @Valid GroupCreateForm form, BindingResult result, ModelMap map){
        if(result.hasErrors()){
            map.addAttribute("interests", interestService.getAll());
            return "groupOfInterestCreation";
        }

        Group group = new Group();
        //UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        group.setName(form.getName());
        group.setDescription(form.getDescription());
        group.setInterests(form.getInterests());
        groupService.create(group);


        return "redirect:groupOfInterestCreation";
    }
}