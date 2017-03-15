package ru.kpfu.itis.pita.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/15/17 6:49 PM
 */

@Controller
public class MainPageController {

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    @ResponseBody
    public String doGet() {
        return "main page body";
    }
}
