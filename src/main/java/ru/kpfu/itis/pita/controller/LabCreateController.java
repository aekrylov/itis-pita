package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.LabCreateForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.LabService;

import javax.validation.Valid;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:27 PM
 */

@Controller
@RequestMapping(path = "/labs/create")
@SessionAttributes("form")
@PreAuthorize("hasAuthority('CREATE_LAB')")
public class LabCreateController {

    private LabService labService;

    @Autowired
    public LabCreateController(LabService labService) {
        this.labService = labService;
    }
    
    @ModelAttribute("form")
    public LabCreateForm form() {
        return new LabCreateForm();
    }

    //TODO teachers list

    @GetMapping
    public String showForm() {
        return "lab_create";
    }

    @PostMapping
    public String processForm(@ModelAttribute("form") @Valid LabCreateForm form, BindingResult result, ModelMap modelMap) {
        if(result.hasErrors()) {
            return "lab_create";
        }

        User user = Helpers.getCurrentUser();
        Lab lab = new Lab(form.getName(), form.getDescription(), user, null);

        //todo save image
        if(labService.exists(form.getName())) {
            modelMap.put("error", "Lab exists");
            return "lab_create";
        }
        labService.create(lab);

        //redirect: redirects to specified URL
        return "redirect:/communities/";
    }
}
