package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.ModifyPasswordForm;
import ru.kpfu.itis.pita.form.ModifyProfileForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.UserService;

import javax.validation.Valid;

/**
 * Created by 1 on 17.03.2017.
 * <p>
 * FIXME backend + frontend
 */
@Controller
@RequestMapping(path = "profile/edit")
@PreAuthorize("isFullyAuthenticated()")
public class ModifyProfilePageController {

    private UserService userService;

    @ModelAttribute("form")
    public ModifyProfileForm registrationForm() {
        return new ModifyProfileForm();
    }

    @Autowired
    public ModifyProfilePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String doGet(ModelMap modelMap) {
        //FIXME - incapsulate to service
        User currentUser = Helpers.getCurrentUser();
        modelMap.put("name", currentUser.getName());
        modelMap.put("email", currentUser.getEmail());
        modelMap.put("phone_number", currentUser.getPhone());
//        modelMap.put("interests", ud.getUser().getInterests());//todo Interests?
//        modelMap.put("info", ud.getUser().getInfo());//todo Info?

        return "setting";
    }
    //todo: remove to ajax
    @RequestMapping(path = "/password", method = RequestMethod.POST)
    public String doPasswordPost(@ModelAttribute @Valid ModifyPasswordForm form, BindingResult bindingResult,
                                 ModelMap map, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("error", "Пароль должен содержать от 4 до 30 символов");
            return "redirect:/profile/edit";
        }
        User currentUser = Helpers.getCurrentUser();
        //FIXME - doesnt working service method
        redirectAttributes.addFlashAttribute("PasswordChangedSuccess", userService.changePassword(currentUser, form.getOldPassword(), form.getPassword()));
        return "redirect:/profile/edit";
    }

    @PostMapping
    public String doPost(@ModelAttribute @Valid ModifyProfileForm form, BindingResult bindingResult, ModelMap map) {
        if (bindingResult.hasErrors()) {
            return "redirect:/profile/edit";
        }
        //todo: remove to services
        User currentUser = Helpers.getCurrentUser();
        currentUser.setEmail(form.getEmail());
        currentUser.setPhone(form.getPhone());
        System.out.println("2222");
        //about me? (not use)   FIXME
        //interest? (needed table)
        userService.save(currentUser);

        return "redirect:/profile/edit";
    }
}
