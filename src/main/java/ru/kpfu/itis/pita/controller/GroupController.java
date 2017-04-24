package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.misc.GroupStrategy;

/**
 * Created by 1 on 30.03.2017.
 */
@Controller
@RequestMapping(path = "/group/{id}/")
public class GroupController extends BaseCommunityController {

    @Autowired
    public GroupController(GroupStrategy strategy) {
        super(strategy, "communities_one");
    }

}
