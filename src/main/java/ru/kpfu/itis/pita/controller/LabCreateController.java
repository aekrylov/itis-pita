package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.form.LabCreateForm;
import ru.kpfu.itis.pita.repository.UserRepository;

import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.LabService;

import javax.validation.Valid;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:27 PM
 */

@Controller
@RequestMapping(path = "/labs/create")
@PreAuthorize("hasAuthority('CREATE_LAB')")
public class LabCreateController {

    private LabService labService;

    @Autowired
    public LabCreateController(LabService labService) {
        this.labService = labService;
    }

    //TODO teachers list

    @GetMapping
    public String showForm() {
        return "lab_create";
    }

    @PostMapping
    public String processForm(@ModelAttribute @Valid LabCreateForm form, BindingResult result, ModelMap modelMap) {
        if(result.hasErrors()) {
            return "lab_create";
        }

        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Lab lab = new Lab(form.getName(), form.getDescription(), ud.getUser(), null);

        //todo save image
        if(labService.exists(form.getName())) {
            modelMap.put("error", "Lab exists");
            return "lab_create";
        }
        labService.create(lab);

        return "redirect:lab_list";
    }
}
