package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.entity.UserRole;
import ru.kpfu.itis.pita.form.RegistrationForm;
import ru.kpfu.itis.pita.service.UserService;
import ru.kpfu.itis.pita.validator.RegistrationFormDbValidator;

import javax.validation.Valid;

/**
 * Created by 1 on 08.04.2017.
 *
 * How session attributes work:
 * 1. Spring notices SessionAttributes annotation on controller
 * 2. Spring tries to load attribute from the session into the model
 * 3. If it fails, it looks for ModelAttribute annotated method with corresponding value
 * 4. If such method is found, Spring calls the method and writes its return to the model and the session
 *
 * 5. When ModelAttribute annotation is used on method parameter, the attribute is retrieved from the model
 */
@Controller
@SessionAttributes("form")
@PreAuthorize("isAnonymous()")
@RequestMapping(path = "/registration")
public class RegistrationController {

    private UserService userService;
    private RegistrationFormDbValidator validator;

    @ModelAttribute("form")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @Autowired
    public RegistrationController(UserService userService, RegistrationFormDbValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String registration(@ModelAttribute("form") @Valid RegistrationForm form,
                               BindingResult bindingResult,
                               ModelMap map,
                               RedirectAttributes redirectAttributes) {
        validator.validate(form, bindingResult);

        if(bindingResult.hasErrors()) {
            map.put("error", bindingResult.getModel());
            //todo why it is so
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form", bindingResult);
            return "redirect:/registration";
        }

        //todo add student specific information

        User user = new User();
        user.setName(form.getSurname() + " " + form.getName());
        user.setEmail(form.getEmail());
        user.setPasswordRaw(form.getPassword());
        user.setPhone(form.getPhone());
        user.setRole(UserRole.ROLE_STUDENT);
        //todo set inactive
        userService.save(user);

//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/login";
    }
}
