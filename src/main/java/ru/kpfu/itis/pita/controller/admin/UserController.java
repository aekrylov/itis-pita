package ru.kpfu.itis.pita.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.repository.UserRepository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/14/17 10:42 AM
 */
@Controller
@RequestMapping(path = "/admin/users")
public class UserController extends BaseAdminController<User> {

    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository repository) {
        super(repository);
    }

    @Override
    protected void preSave(User entry) {
        //todo encoding
    }
}
