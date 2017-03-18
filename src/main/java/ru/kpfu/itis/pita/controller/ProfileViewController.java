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
 * Created by taa on 18.03.17.
 */
@Controller
public class ProfileViewController {

    @Autowired
    private UserRepository userRepository;
    @RequestMapping(path = "/profile?id=*", method = RequestMethod.GET)
    public ModelAndView doGet(ModelMap modelMap, @RequestParam(value = "id") String id) {
        if (userRepository.findById(id)!= null){
            User user = userRepository.findById(id);
            modelMap.addAttribute("user", user);
        } else{
            modelMap.addAttribute("error", "Error: no such user");
        }

        return new ModelAndView("/profile.jsp", modelMap);



    }

}
