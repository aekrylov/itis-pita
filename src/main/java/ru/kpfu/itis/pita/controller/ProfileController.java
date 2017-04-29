package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.ChangePasswordForm;
import ru.kpfu.itis.pita.form.ModifyProfileForm;
import ru.kpfu.itis.pita.misc.EntityNotFoundException;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.UserService;
import ru.kpfu.itis.pita.validator.NewPasswordFormValidator;

import javax.validation.Valid;
/**
 * Created by 1 on 17.03.2017.
 *
 */
@Controller
@RequestMapping(path = "/profile")
@PreAuthorize("isFullyAuthenticated()")
public class ProfileController {

    private UserService userService;
    private NewPasswordFormValidator validator;

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
    @ModelAttribute("passwordForm")
    public ChangePasswordForm changePasswordForm() {
        return new ChangePasswordForm();
    }

    @Autowired
    public ProfileController(UserService userService, NewPasswordFormValidator validator) {
        this.userService = userService;
        this.validator = validator;
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

    @PostMapping("/edit/change_password")
    public String doPost(@ModelAttribute("passwordForm") @Valid ChangePasswordForm form,
                         BindingResult bindingResult,
                         ModelMap map,
                         RedirectAttributes redirectAttributes) {
        validator.validate(form, bindingResult);
        if(bindingResult.hasErrors()) {
            map.put("error", bindingResult.getModel());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form", bindingResult);
            
            if(bindingResult.hasFieldErrors("old_password")) {
                redirectAttributes.addFlashAttribute("PasswordChangedSuccess", false);
            }
            return  "redirect:/profile/edit";
        }

        User currentUser = Helpers.getCurrentUser();
        currentUser.setPasswordRaw(form.getNew_password());

        userService.save(currentUser);

        redirectAttributes.addFlashAttribute("PasswordChangedSuccess", true);
        return "redirect:/profile/edit";
    }

}
