package ru.kpfu.itis.pita.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Event;
import ru.kpfu.itis.pita.form.EventCreateForm;
import ru.kpfu.itis.pita.repository.UserRepository;
import ru.kpfu.itis.pita.security.UserDetails;
import ru.kpfu.itis.pita.service.EventService;

import javax.validation.Valid;

/**
 * Created by volkov on 28.03.2017.
 */






@Controller
@RequestMapping(path = "/events/create")
@PreAuthorize("hasAuthority('DEAN')")
public class EventCreateController {

    private EventService eventService;
    private UserRepository userRepository;

    @Autowired
    public EventCreateController(EventService eventService, UserRepository ur) {
        this.eventService = eventService;
        this.userRepository = ur;
    }


    @GetMapping
    public String showForm() {
        return "event_create";
    }

    @PostMapping
    public String processForm(@ModelAttribute @Valid EventCreateForm form, BindingResult result, ModelMap modelMap) {
        if(result.hasErrors()) {
            return "event_create";
        }

        Event event = new Event();
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //todo save all fields
        if(eventService.exists(form.getName())) {
            modelMap.put("error", "Lab exists");
            return "event_create";
        }
        eventService.create(event);

        return "redirect:event_list";
    }
}