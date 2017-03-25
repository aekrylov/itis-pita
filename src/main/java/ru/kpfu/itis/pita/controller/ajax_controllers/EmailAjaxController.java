package ru.kpfu.itis.pita.controller.ajax_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.pita.repository.UserRepository;

/**
 * @author Oleg Shatin
 *         11-501
 */
@RestController
public class EmailAjaxController {

    @Autowired
    private UserRepository userRepository;

    //"/ajax/email-check"
    //@ResponseBody annotation is infered automatically because this class is annotated with RestController
    @RequestMapping(value="/ajax/email-check", method=RequestMethod.GET)
    public boolean getSearchUserProfiles(@RequestParam String email) {

        // TODO: 18.03.2017 add logic and JSON
        //return value will be converted to JSON automatically
        // (the only converter currently installed is Jackson JSON converter).
        return true;
    }

}

