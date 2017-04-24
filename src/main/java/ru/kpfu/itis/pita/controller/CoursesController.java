package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.CourseCreateForm;
import ru.kpfu.itis.pita.misc.CourseStrategy;
import ru.kpfu.itis.pita.service.CourseService;
import ru.kpfu.itis.pita.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 1 on 29.03.2017.
 */
@Controller
@RequestMapping(path = "/courses")
public class CoursesController extends BaseCommunitiesController<CourseCreateForm> {
    private CourseService courseService;
    private UserService userService;

    @Autowired
    public CoursesController(CourseStrategy strategy,
                             UserService userService) {
        super(strategy, "course_create", "redirect:/communities/");
        this.userService = userService;
    }

    @ModelAttribute("names")
    public List<User> getAllWorkers() {
        return userService.findAllWorkers();
    }

    //override methods to add more annotations
    @PreAuthorize("hasAuthority('CREATE_COURSE')")
    public String doCreateGet() {
        return super.doCreateGet();
    }

    @PreAuthorize("hasAuthority('CREATE_COURSE')")
    public String doCreatePost(@ModelAttribute @Valid CourseCreateForm form, BindingResult result, ModelMap map) {
        return super.doCreatePost(form, result, map);
    }
}
