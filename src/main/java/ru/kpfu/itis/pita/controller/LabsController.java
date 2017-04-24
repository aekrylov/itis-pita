package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.form.LabCreateForm;
import ru.kpfu.itis.pita.misc.LabStrategy;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:27 PM
 */

@Controller
@RequestMapping(path = "/labs")
public class LabsController extends BaseCommunitiesController<LabCreateForm> {

    @Autowired
    public LabsController(LabStrategy strategy) {
        super(strategy, "lab_create", "redirect:/communities/");
    }

    @PreAuthorize("hasAuthority('CREATE_LAB')")
    public String doCreateGet() {
        return super.doCreateGet();
    }

    @PreAuthorize("hasAuthority('CREATE_LAB')")
    public String doCreatePost(LabCreateForm form, BindingResult result, ModelMap map) {
        return super.doCreatePost(form, result, map);
    }

    //TODO teachers list

}
