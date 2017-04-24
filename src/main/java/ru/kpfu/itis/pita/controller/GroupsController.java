package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Interest;
import ru.kpfu.itis.pita.form.GroupCreateForm;
import ru.kpfu.itis.pita.misc.GroupStrategy;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.InterestService;

import java.util.List;

/**
 * Created by taa on 30.03.2017.
 */
@Controller
@RequestMapping(path = "/groups")
public class GroupsController extends BaseCommunitiesController<GroupCreateForm> {
    private GroupService groupService;
    private InterestService interestService;

    @ModelAttribute("interests")
    public List<Interest> getInterests() {
        return interestService.getAll();
    }

    @Autowired
    public GroupsController(GroupStrategy strategy, InterestService interestService) {
        super(strategy, "groupOfInterestCreation", "redirect:/communities/");
        this.interestService = interestService;
    }

}