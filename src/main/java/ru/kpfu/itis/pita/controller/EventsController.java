package ru.kpfu.itis.pita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 10:41 PM
 *
 * TODO
 */
@Controller
@RequestMapping(path = "/events/")
public class EventsController {

    @GetMapping(path = "/create")
    public String createGet() {
        return "event_create";
    }
}
