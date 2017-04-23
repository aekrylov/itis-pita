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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.ModifyProfileForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.UserService;
import ru.kpfu.itis.pita.validator.RegistrationFormDbValidator;
import javax.validation.Valid;
/**
 * Created by 1 on 17.03.2017.
 */
@Controller
@RequestMapping(path = "profile/edit")
@PreAuthorize("isFullyAuthenticated()")
public class ModifyProfilePageController {

    private UserService userService;

    @ModelAttribute("form")
    public  ModifyProfileForm  registrationForm() {
        return new  ModifyProfileForm ();
    }

    @Autowired
    public ModifyProfilePageController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String doGet(ModelMap modelMap){
        User currentUser = Helpers.getCurrentUser();
        modelMap.put("name", currentUser.getName());
        modelMap.put("email", currentUser.getEmail());
        modelMap.put("phone_number", currentUser.getPhone());
//        modelMap.put("interests", ud.getUser().getInterests());//todo Interests?
//        modelMap.put("info", ud.getUser().getInfo());//todo Info?

        return  "setting";
    }

    @PostMapping
    public String doPost(@ModelAttribute @Valid ModifyProfileForm form, BindingResult bindingResult, ModelMap map) {
        if(bindingResult.hasErrors()) {
            return  "redirect:/profile/edit";
        }
        User currentUser = Helpers.getCurrentUser();
        currentUser.setEmail(form.getEmail());
        currentUser.setPhone(form.getPhone());
        System.out.println("2222");
        //about me? (not use)
        //interest? (needed table)
        userService.save(currentUser);

        return "redirect:/profile/edit";
    }
}
