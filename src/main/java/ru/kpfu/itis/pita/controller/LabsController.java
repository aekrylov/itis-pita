package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.community.CommunityCreateForm;
import ru.kpfu.itis.pita.form.community.LabCreateForm;
import ru.kpfu.itis.pita.service.UserService;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:27 PM
 */

@Controller
@RequestMapping(path = "/labs")
public class LabsController extends BaseCommunitiesController<Lab> {

    private final UserService userService;

    @Autowired
    public LabsController(UserService userService) {
        super(Community.CommunityType.LAB);
        this.userService = userService;
    }

    @Override
    public LabCreateForm newCreateForm() {
        return new LabCreateForm();
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE_LAB')")
    public String doCreateGet() {
        return super.doCreateGet();
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE_LAB')")
    public String doCreatePost(CommunityCreateForm<Lab> form, BindingResult result, ModelMap map) {
        return super.doCreatePost(form, result, map);
    }

    @Override
    protected Lab getNewEntity() {
        return new Lab();
    }

    //TODO teachers list
    @ModelAttribute("names")
    public List<User> getAllWorkers() {
        return userService.findAllWorkers();
    }
}
