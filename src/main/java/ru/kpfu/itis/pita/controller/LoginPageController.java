package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.repository.UserRepository;

/**
 * Created by lenovo on 18.03.2017.
 */

@Controller
public class LoginPageController {
    /**
     * TODO handled by Spring security, remove that?
     */

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView doGet(ModelMap modelMap) {
        return new ModelAndView("login.jsp", modelMap);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView doPost(ModelMap modelMap, @RequestParam String email, @RequestParam String password_hash) {
        /*SessionFactory sessionFactory;
        sessionFactory.getCurrentSession().save(user);*/
        /*something don't work*/
        User user = userRepository.findByEmailAndPasswordHash(email, password_hash);
        if(user != null) {
            return new ModelAndView(new RedirectView("/profile?id="+user.getId()));
        }
        return new ModelAndView("login.jsp", modelMap);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView loguot() {
        return new ModelAndView(new RedirectView("/login"));
    }

}
