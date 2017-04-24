package ru.kpfu.itis.pita.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.security.UserDetails;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/19/17 5:34 PM
 *
 * This class methods apply to all controllers using AOP and load auth info into the model for use in views
 */

@ControllerAdvice
public class CurrentUserAdvice {

    @ModelAttribute("logged_in")
    public boolean isLoggedIn() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
    }

    @ModelAttribute("current_user")
    public User currentUser() {
        return isLoggedIn()
                ? ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()
                : null;
    }
}
