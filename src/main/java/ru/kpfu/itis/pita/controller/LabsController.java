package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.LabCreateForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.LabService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:27 PM
 */

@Controller
@RequestMapping(path = "/labs")
public class LabsController extends BaseCommunitiesController<LabCreateForm> {

    private final LabService service;

    @Autowired
    public LabsController(LabService service) {
        super("lab_create", "redirect:/communities/");
        this.service = service;
    }

    @Override
    public LabCreateForm newCreateForm() {
        return new LabCreateForm();
    }

    @PreAuthorize("hasAuthority('CREATE_LAB')")
    public String doCreateGet() {
        return super.doCreateGet();
    }

    @PreAuthorize("hasAuthority('CREATE_LAB')")
    public String doCreatePost(LabCreateForm form, BindingResult result, ModelMap map) {
        return super.doCreatePost(form, result, map);
    }

    @Override
    protected Community createEntity(LabCreateForm form) {
        User user = Helpers.getCurrentUser();
        Lab lab = new Lab(form.getName(), form.getDescription(), user);

        //todo save image

        return service.create(lab);

    }

    //TODO teachers list

}
