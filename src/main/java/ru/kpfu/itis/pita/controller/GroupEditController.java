package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Interest;
import ru.kpfu.itis.pita.form.GroupEditForm;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.InterestService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cmen on 4/24/17.
 */

@Controller
@RequestMapping(path = "group/{id}/edit")
@PreAuthorize("isFullyAuthenticated()")
public class GroupEditController {

    private GroupService groupService;
    private InterestService interestService;

    @ModelAttribute("form")
    public GroupEditForm form() {
        return new GroupEditForm();
    }

    @Autowired
    public GroupEditController(GroupService groupService, InterestService interestService) {
        this.groupService = groupService;
        this.interestService = interestService;
    }

    @GetMapping
    public String doGet(ModelMap modelMap, @PathVariable int id) {

        Group currentGroup = groupService.getOne(id);
        modelMap.addAttribute("interests", interestService.getAll());
        modelMap.put("group", currentGroup);

        return "groupOfInterestEdit";
    }

    @PostMapping
    public String doPost(@ModelAttribute @Valid GroupEditForm form, BindingResult result, ModelMap map, @PathVariable int id) {
        if (result.hasErrors()) {
            map.addAttribute("interests", interestService.getAll());
            return "redirect:/group/{id}/edit";
        }

        Group currentGroup = groupService.getOne(id);
        currentGroup.setName(form.getName());
        currentGroup.setDescription(form.getDescription());

        String tags = form.getInterests();
        String[] separated_tags = tags.split(",");
        List<Interest> interests = Arrays.stream(separated_tags)
                .map(Interest::new)
                .map(interest -> interestService.save(interest))
                .collect(Collectors.toList());

        currentGroup.setInterests(interests);

        groupService.save(currentGroup);

        return "redirect:/group/{id}/";
    }
}
