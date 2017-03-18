package ru.kpfu.itis.pita.controller.ajax_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Oleg Shatin
 *         11-501
 */
@Controller
public class EmailAjaxController {

    @Autowired
    private UserRepository userRepository;

    //"/ajax/email-check"
    @RequestMapping(value="/ajax/email-check", method=RequestMethod.GET)

    public  @ResponseBody
    String  getSearchUserProfiles(@RequestBody String email, HttpServletRequest request) {

        // TODO: 18.03.2017 add logic and JSON
        return "{"+ Boolean.toString(true) +"}";
    }

}

