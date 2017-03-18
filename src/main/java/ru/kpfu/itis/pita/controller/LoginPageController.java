package ru.kpfu.itis.pita.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.repository.UserRepository;

/**
 * Created by lenovo on 18.03.2017.
 */

@Controller
public class LoginPageController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView doGet(ModelMap modelMap) {
        return new ModelAndView("login.jsp", modelMap);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView doPost(ModelMap modelMap, @ModelAttribute User user) {
        /*SessionFactory sessionFactory;
        sessionFactory.getCurrentSession().save(user);*/
        return new ModelAndView("login.jsp", modelMap);
    }

}
