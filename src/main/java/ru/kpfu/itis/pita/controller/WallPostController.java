package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.entity.WallPost;
import ru.kpfu.itis.pita.form.PostCreateForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.GroupService;

import javax.validation.Valid;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 2:18 PM
 */

@Controller
@RequestMapping(path = "/group/{id}/wall")
public class WallPostController {

    private GroupService service;

    @Autowired
    public WallPostController(GroupService service) {
        this.service = service;
    }

    @ModelAttribute("group_id")
    public int groupId(@PathVariable int id) {
        return id;
    }

    @GetMapping(path = "/new")
    public String newPostGet() {
        return "communities_newpost";
    }

    @PostMapping(path = "/new")
    public String newPostPost(@ModelAttribute @Valid PostCreateForm form, BindingResult result, ModelMap map) {
        User currentUser = Helpers.getCurrentUser();
        //todo check errors
        WallPost post = new WallPost(currentUser, form.getText(), form.getVideoLink());
        service.addPost((Integer) map.get("group_id"), post);
        return "redirect:/group?id=" + map.get("group_id");
    }
}
