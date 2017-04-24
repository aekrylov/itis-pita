package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.service.CommunityService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 6:54 PM
 */
@Controller
@RequestMapping("/community/{id}")
public class CommunityController extends BaseCommunityController {

    @Autowired
    public CommunityController(CommunityService<Community> service) {
        super(service, "communities_one");
    }
}
