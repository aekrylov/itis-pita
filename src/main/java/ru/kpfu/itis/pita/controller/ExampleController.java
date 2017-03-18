package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.repository.UserRepository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/15/17 6:49 PM
 *
 * Example controller to test capabilities of the Spring framework
 */

@Controller
@SessionAttributes({"user"})
public class ExampleController {

    //todo use services
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/hi")
    public ModelAndView doGet(ModelMap modelMap,
                              @RequestParam(name = "id", required = false, defaultValue = "1") int userId) {
        modelMap.put("user", userRepository.findOne(userId));
        return new ModelAndView("hi", modelMap);
    }

    @RequestMapping(path = "/check")
    @ResponseBody
    public String checkSession(ModelMap modelMap) {
        //user is a session attribute
        return modelMap.get("user").toString();
    }
}
