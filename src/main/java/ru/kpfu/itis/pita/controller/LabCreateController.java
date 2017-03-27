package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.LabCreateForm;
import ru.kpfu.itis.pita.repository.UserRepository;
import ru.kpfu.itis.pita.service.LabService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:27 PM
 */

@Controller
@RequestMapping(path = "/labs/create")
//@PreAuthorize("hasRole('DEAN')")
//@PreAuthorize("isFullyAuthenticated()")
public class LabCreateController {

    private LabService labService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public LabCreateController(LabService labService) {
        this.labService = labService;
    }

    //TODO images
    //TODO teachers list

    @GetMapping
    public String showForm() {
        return "lab_create";
    }

    //@PostMapping(consumes = "multipart/form-data")
    @PostMapping
    public ModelAndView processForm(@ModelAttribute LabCreateForm form) {
        /*LabCreateForm form = new LabCreateForm();
        form.setName((String) values.get("name"));
        form.setImage((MultipartFile) values.get("image"));
        form.setDescription((String) values.get("description"));*/

        //todo get current user
        Lab lab = new Lab();
        User currentUser = userRepository.findOne(1);
        lab.setGroup(new Group(form.getName(), form.getDescription(), currentUser, null));
        //todo check errors
        labService.create(lab);

        return new ModelAndView("redirect:lab_list");
    }
}
