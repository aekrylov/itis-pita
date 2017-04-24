package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Interest;
import ru.kpfu.itis.pita.form.GroupCreateForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.InterestService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by taa on 30.03.2017.
 */
@Controller
@RequestMapping(path = "groups/create")
public class GroupCreateController {
    private GroupService groupService;
    private InterestService interestService;

    @Autowired
    public GroupCreateController(GroupService groupService, InterestService interestService) {
        this.groupService = groupService;
        this.interestService = interestService;
    }

    @GetMapping
    public String doGet(ModelMap map){
        map.addAttribute("interests", interestService.getAll());
        return "groupOfInterestCreation";
    }

    @PostMapping
    public String doPost(@ModelAttribute @Valid GroupCreateForm form, BindingResult result, ModelMap map){
        if(result.hasErrors()){
            map.addAttribute("interests", interestService.getAll());
            return "groupOfInterestCreation";
        }

        Group group = new Group();
        group.setCreator(Helpers.getCurrentUser());

        String tags = form.getInterests();
        String[] separated_tags = tags.split(",");
        List<Interest> interests = Arrays.stream(separated_tags)
                .map(Interest::new)
                .map(interest -> interestService.save(interest))
                .collect(Collectors.toList());

        group.setInterests(interests);
        group.setName(form.getName());
        group.setDescription(form.getDescription());
        //temp?
        //todo move to view?
        group.setImageLink("/static/img/avatar_example.png");

        groupService.create(group);
        
        return "redirect:/communities/";
    }
}