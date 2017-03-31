package ru.kpfu.itis.pita.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.security.UserDetails;

/**
 * Created by 1 on 17.03.2017.
 */
@Controller
@RequestMapping(path = "profile/edit")
public class ModifyProfilePageController {

    @GetMapping
    public ModelAndView doGet(ModelMap modelMap){
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.put("name", ud.getUser().getName());
        modelMap.put("email", ud.getUser().getEmail());
        modelMap.put("phone_number", ud.getUser().getPhone());
//        modelMap.put("interests", ud.getUser().getInterests());//todo Interests?
//        modelMap.put("info", ud.getUser().getInfo());//todo Info?
        return new ModelAndView("settings.jsp", modelMap);
    }
}
