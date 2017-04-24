package ru.kpfu.itis.pita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.form.EventCreateForm;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 10:41 PM
 *
 * TODO
 */
@Controller
@RequestMapping(path = "/events/")
public class EventsController extends BaseCommunitiesController<EventCreateForm> {

    protected EventsController() {
        super("event_create", "redirect:/communities/");
    }

    @Override
    public EventCreateForm newCreateForm() {
        return new EventCreateForm();
    }

    @Override
    protected Community createEntity(EventCreateForm form) {
        return null;
    }
}
