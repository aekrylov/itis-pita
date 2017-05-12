package ru.kpfu.itis.pita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.Event;
import ru.kpfu.itis.pita.form.community.EventCreateForm;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 10:41 PM
 *
 */
@Controller
@RequestMapping(path = "/events/")
public class EventsController extends BaseCommunitiesController<Event> {

    protected EventsController() {
        super(Community.CommunityType.EVENT);
    }

    @Override
    public EventCreateForm newCreateForm() {
        return new EventCreateForm();
    }

    @Override
    protected Event getNewEntity() {
        return new Event();
    }

}
