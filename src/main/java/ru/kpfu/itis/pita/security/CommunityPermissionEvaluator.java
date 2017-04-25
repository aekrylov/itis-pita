package ru.kpfu.itis.pita.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.service.CommunityService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 5:24 PM
 */
@Component
public class CommunityPermissionEvaluator extends AbstractPermissionEvaluator<Community> {

    private CommunityService service;

    @Autowired
    public CommunityPermissionEvaluator(@Qualifier("communityService") CommunityService service) {
        this.service = service;
    }

    @Override
    protected boolean hasPermission(User user, Community community, String permission) {
        switch (permission) {
            case "view_wall":
            case "post":
            case "comment":
                return community.getMembers().contains(user);

            case "admin":
                return community.getAdmins().contains(user);

            default:
                return false;

        }
    }

    @Override
    protected Community getDomainObject(int id) {
        return service.getOne(id);
    }

    @Override
    protected String getSupportedType() {
        return "Community";
    }

}
