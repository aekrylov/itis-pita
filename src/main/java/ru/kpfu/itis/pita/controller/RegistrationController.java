package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.entity.UserRole;
import ru.kpfu.itis.pita.form.RegistrationForm;
import ru.kpfu.itis.pita.service.UserService;
import ru.kpfu.itis.pita.serviceimpl.UserServiceImpl;
import ru.kpfu.itis.pita.validator.UserValidator;

import javax.validation.Valid;

/**
 * Created by 1 on 08.04.2017.
 */
@Controller
public class RegistrationController {

    private UserService userService = new UserServiceImpl();
    @Autowired
    private UserValidator userValidator;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute @Valid RegistrationForm form, BindingResult bindingResult, Model model) {
        User user = new User();
//        userValidator.validate(userForm, bindingResult);
        user.setName(form.getName());
        System.out.println("Name set");
        user.setEmail(form.getEmail());
        System.out.println("Email set");
        user.setPasswordHash(form.getPassword());
        System.out.println("Pas set");
        user.setPhone(form.getPhone());
        System.out.println("Phone set");
        user.setRole(UserRole.ROLE_STUDENT);
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
        System.out.println("Blabla");
        userService.save(user);
        System.out.println("User registered");

//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/login";
    }
}
