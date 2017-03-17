package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.repository.UserRepository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/15/17 6:49 PM
 */

@Controller
public class MainPageController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/hi")
    public ModelAndView doGet(ModelMap modelMap) {
        modelMap.put("user", userRepository.findOne(1));
        return new ModelAndView("hi.jsp", modelMap);
    }

    @RequestMapping(path = "/login")
    public String login(){
        return "login.jsp";
    }
}
