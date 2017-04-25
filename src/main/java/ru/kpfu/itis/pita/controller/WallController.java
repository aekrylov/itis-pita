package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.entity.WallComment;
import ru.kpfu.itis.pita.entity.WallPost;
import ru.kpfu.itis.pita.form.PostCreateForm;
import ru.kpfu.itis.pita.form.WallCommentForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.GroupService;
import ru.kpfu.itis.pita.service.WallService;

import javax.validation.Valid;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 2:18 PM
 */

@Controller
@RequestMapping(path = "/group/{id}/wall")
@SessionAttributes("form")
public class WallController {

    private GroupService groupService;
    private WallService wallService;

    @Autowired
    public WallController(GroupService groupService, WallService wallService) {
        this.groupService = groupService;
        this.wallService = wallService;
    }

    @ModelAttribute("group_id")
    public int groupId(@PathVariable int id) {
        return id;
    }

    @ModelAttribute("form")
    public PostCreateForm form() {
        return new PostCreateForm();
    }

    @GetMapping(path = "/new")
    @PreAuthorize("hasPermission(#group_id, 'Group', 'post')")
    public String newPostGet(@ModelAttribute("group_id") int group_id) {
        return "communities_newpost";
    }

    @PostMapping(path = "/new")
    @PreAuthorize("hasPermission(#group_id, 'Group', 'post')")
    public String newPostPost(@ModelAttribute("form") @Valid PostCreateForm form, BindingResult result,
                              /* Parameter group_id is needed here for security expression evaluation */
                              @ModelAttribute("group_id") int group_id,
                              ModelMap map) {
        User currentUser = Helpers.getCurrentUser();
        if(result.hasErrors()) {
            return "communities_newpost";
        }
        
        WallPost post = new WallPost(currentUser, form.getText(), form.getVideoLink());
        if(form.getImage() != null)
            post.setImageLink(Helpers.uploadFile(form.getImage()));

        groupService.addPost(group_id, post);
        // to do that redirect work  also with courses,events,labs
        return "redirect:/group/" + group_id + "/";
    }

    @PostMapping(path = "/{post_id}/comments/new")
    @PreAuthorize("hasPermission(#group_id, 'Group', 'comment')")
    public String newCommentPost(@ModelAttribute @Valid WallCommentForm form, BindingResult result,
                                 @ModelAttribute("group_id") int group_id,
                                 @PathVariable int post_id) {
        if(result.hasErrors()) {
            //todo redirect to group page
            return "redirect:../../../";
        }
        User author = Helpers.getCurrentUser();
        wallService.addComment(group_id, post_id, new WallComment(author, form.getText()));
        return "redirect:../../../";
    }
}
