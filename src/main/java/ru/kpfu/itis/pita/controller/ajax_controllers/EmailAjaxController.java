package ru.kpfu.itis.pita.controller.ajax_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.pita.repository.UserRepository;

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
    String  getSearchUserProfiles(@RequestBody String email) {

        // TODO: 18.03.2017 add logic and JSON
        return "{"+ Boolean.toString(true) +"}";
    }

}

