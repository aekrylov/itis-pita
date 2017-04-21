package ru.kpfu.itis.pita.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.service.GroupService;

import java.util.Collection;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 5:24 PM
 */
@Component
public class GroupPermissionEvaluator extends AbstractPermissionEvaluator<Group> {

    private GroupService service;

    @Autowired
    public GroupPermissionEvaluator(GroupService service) {
        this.service = service;
    }

    @Override
    protected boolean hasPermission(User user, Group group, String permission) {
        switch (permission) {
            case "view_wall":
            case "post":
                //todo
                Collection<User> members = group.getMembers();
/*                System.out.println("members.contains(user) = " + members.contains(user));
                User user1 = members.iterator().next();
                System.out.println("user1.equals(user) = " + user1.equals(user));*/

                return members.contains(user);

            case "admin":
                return group.getAdmins().contains(user);

            default:
                return false;

        }
    }

    @Override
    protected Group getDomainObject(int id) {
        return service.getOne(id);
    }

    @Override
    protected String getSupportedType() {
        return "Group";
    }

}
