package ru.kpfu.itis.pita.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.repository.GroupRepository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/14/17 9:49 AM
 */
@Controller
@RequestMapping(path = "/admin/groups/")
public class GroupAdminController extends BaseAdminController<Group> {

    @Autowired
    public GroupAdminController(GroupRepository repository) {
        super(repository);
    }

}
