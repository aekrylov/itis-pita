package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.ModifyProfileForm;
import ru.kpfu.itis.pita.misc.EntityNotFoundException;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.UserService;

import javax.validation.Valid;
/**
 * Created by 1 on 17.03.2017.
 *
 * FIXME backend
 */
@Controller
@RequestMapping(path = "/profile")
@PreAuthorize("isFullyAuthenticated()")
public class ProfileController {

    private UserService userService;

    @ModelAttribute("form")
    public ModifyProfileForm profileEditForm() {
        ModifyProfileForm form =  new ModifyProfileForm();
        User currentUser = Helpers.getCurrentUser();
        form.setName(currentUser.getName());
        form.setEmail(currentUser.getEmail());
        form.setPhone(currentUser.getPhone());
        form.setAbout_me(currentUser.getAboutMe());
        //todo interests
        return form;
    }

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String doGet(){
        return  "settings";
    }

    @PostMapping("/edit")
    public String doPost(@ModelAttribute @Valid ModifyProfileForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return  "redirect:/profile/edit";
        }

        User currentUser = Helpers.getCurrentUser();
        currentUser.setEmail(form.getEmail());
        currentUser.setPhone(form.getPhone());
        currentUser.setAboutMe(form.getAbout_me());
        //interest? (needed table) TODO
        userService.save(currentUser);

        return "redirect:/profile/edit";
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView doGet(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id) {
        User user = (id == null) ? Helpers.getCurrentUser() : userService.findById(id);

        if(user == null) {
            throw new EntityNotFoundException(0);
        } else {
            modelMap.addAttribute("user", user);
        }

        return new ModelAndView("profile", modelMap);
    }

}
