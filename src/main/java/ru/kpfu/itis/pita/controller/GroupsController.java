package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Interest;
import ru.kpfu.itis.pita.form.community.GroupCreateForm;
import ru.kpfu.itis.pita.service.InterestService;

import java.util.List;

/**
 * Created by taa on 30.03.2017.
 */
@Controller
@RequestMapping(path = "/groups")
public class GroupsController extends BaseCommunitiesController<Group> {
    private InterestService interestService;

    @ModelAttribute("interests")
    public List<Interest> getInterests() {
        return interestService.getAll();
    }

    @Autowired
    public GroupsController(InterestService interestService) {
        super(Community.CommunityType.GROUP);
        this.interestService = interestService;
    }

    @Override
    public GroupCreateForm newCreateForm() {
        return new GroupCreateForm();
    }

    @Override
    protected Group getNewEntity() {
        return new Group();
    }

}