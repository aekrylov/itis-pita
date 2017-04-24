package ru.kpfu.itis.pita.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.form.CommunityCreateForm;

import javax.validation.Valid;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 1:18 PM
 *
 * Base controller for communities operations
 */
@PreAuthorize("isFullyAuthenticated()")
public abstract class BaseCommunitiesController<F extends CommunityCreateForm> {

    private final String createViewName;
    private final String createRedirect;

    protected BaseCommunitiesController(String createViewName, String createRedirect) {
        this.createViewName = createViewName;
        this.createRedirect = createRedirect;
    }

    @ModelAttribute("form")
    public abstract F newCreateForm();

    @GetMapping(path = "/create")
    public String doCreateGet() {
        return createViewName;
    }

    @PostMapping(path = "/create")
    public String doCreatePost(@ModelAttribute("form") @Valid F form, BindingResult result,
                               ModelMap map) {
        if(result.hasErrors()) {
            return createViewName;
        }

        //todo if exists

        createEntity(form);
        return createRedirect;
    }

    protected abstract Community createEntity(F form);
}
