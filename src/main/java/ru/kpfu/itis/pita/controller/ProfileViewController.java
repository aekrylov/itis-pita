package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.misc.EntityNotFoundException;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.UserService;

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
        User user = (id == null) ? Helpers.getCurrentUser() : userService.findById(id);

        if(user == null) {
            throw new EntityNotFoundException(0);
        } else {
            modelMap.addAttribute("user", user);
        }

        return new ModelAndView("profile", modelMap);
    }

}
