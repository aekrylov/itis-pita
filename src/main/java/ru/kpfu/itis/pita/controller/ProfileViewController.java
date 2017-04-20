package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.repository.UserRepository;
import ru.kpfu.itis.pita.security.UserDetails;

/**
 * Created by taa on 18.03.17.
 */
@Controller
public class ProfileViewController {

    private final UserRepository userRepository;

    @Autowired
    public ProfileViewController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ModelAndView doGet(ModelMap modelMap, @RequestParam(value = "id", required = false) Integer id) {
        User user;
        if(id == null) {
            user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        } else {
            user = userRepository.findOne(id);
        }

        if(user == null) {
            modelMap.addAttribute("error", "Error: no such user");
        } else {
            modelMap.addAttribute("user", user);
        }

        return new ModelAndView("/profile.jsp", modelMap);



    }

}
