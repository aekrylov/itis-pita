package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.entity.*;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.*;

/**
 * Created by taa on 18.03.17.
 */
@Controller
public class ProfileViewController {

    private final UserService userService;


    @Autowired
    public ProfileViewController( UserService userService) {
        this.userService = userService;
    }

    //@PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ModelAndView doGet(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id) {
        User user = null;
        Student student = null;
        Lab lab = null;
        if(id == null) {
            user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        } else {

            //user = userService.getById(id);
            //student = userService.getById(user.getId());
            lab = student.getLab();



        }

        if(user == null) {
            modelMap.addAttribute("error", "Error: no such user");
        } else {
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("lab_id", lab);
            modelMap.addAttribute("academic_group", student.getAcademicGroup());
        }

        return new ModelAndView("/profile.jsp", modelMap);



    }

}
