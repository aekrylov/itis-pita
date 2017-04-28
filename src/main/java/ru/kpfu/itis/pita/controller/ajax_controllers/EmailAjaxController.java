package ru.kpfu.itis.pita.controller.ajax_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.misc.Helpers;
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
    @CrossOrigin //for testing purposes
    @RequestMapping(value="/ajax/email-check", method=RequestMethod.GET)
    public boolean getSearchUserProfiles(@RequestParam String email) {
        User candidate  = userRepository.findByEmail(email.trim());
        //return true - if email is free or belong to current user
        if (candidate == null){
            return true;
        } else {
            return  candidate.getId() == Helpers.getCurrentUser().getId();
        }

    }

}

