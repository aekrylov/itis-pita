package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.repository.UserRepository;

/**
 * Created by 1 on 17.03.2017.
 */
@Controller
public class ModifyProfilePageController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(path = "/profile/edit", method = RequestMethod.GET)
    public ModelAndView doGet(ModelMap modelMap,
                              @RequestParam(value = "name", required = false, defaultValue = "") String name,
                              @RequestParam(value = "email", required = false, defaultValue = "") String email,
                              @RequestParam(value = "phone_number", required = false, defaultValue = "") String phone_number,
                              @RequestParam(value = "interests", required = false, defaultValue = "") String interests,
                              @RequestParam(value = "info", required = false, defaultValue = "") String info){
        modelMap.put("name", name);
        modelMap.put("email", email);
        modelMap.put("phone_number", phone_number);
        modelMap.put("interests", interests);
        modelMap.put("info", info);
        return new ModelAndView("settings.jsp", modelMap);
    }
}
