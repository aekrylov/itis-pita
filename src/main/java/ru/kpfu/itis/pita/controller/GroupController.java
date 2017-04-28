package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.form.CommunityCreateForm;
import ru.kpfu.itis.pita.form.GroupCreateForm;
import ru.kpfu.itis.pita.service.GroupService;

/**
 * Created by 1 on 30.03.2017.
 */
@Controller
@RequestMapping(path = "/group/{id}/")
public class GroupController extends CommunityController<Group> {

    @Autowired
    public GroupController(GroupService groupService) {
        super(groupService);
    }

    @Override
    protected CommunityCreateForm<Group> editForm(Group community) {
        return new GroupCreateForm().fromEntity(community);
    }
}
