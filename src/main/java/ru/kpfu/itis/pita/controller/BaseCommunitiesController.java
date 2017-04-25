package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.form.CommunityCreateForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.CommunityService;

import javax.validation.Valid;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 1:18 PM
 *
 * Base controller for communities operations. Child community classes should override factory methods,
 * and also can override handler methods to add custom annotations.
 */
@PreAuthorize("isFullyAuthenticated()")
public abstract class BaseCommunitiesController<F extends CommunityCreateForm> {

    private final String createViewName;
    private CommunityService<Community> communityService;

    protected BaseCommunitiesController(String createViewName) {
        this.createViewName = createViewName;
    }

    @ModelAttribute("form")
    public CommunityCreateForm newCreateForm() {
        return new CommunityCreateForm();
    }

    private boolean isValid(CommunityCreateForm form, BindingResult result) {
        return !(result.hasErrors() || communityService.exists(form.getName()));
    }

    private void fillEntity(CommunityCreateForm form, Community community) {
        community.setName(form.getName());
        community.setDescription(form.getDescription());
        community.setCreator(Helpers.getCurrentUser());
        if(form.getImage() != null && form.getImage().getSize() > 0) {
            community.setImageLink(Helpers.uploadImage(form.getImage()));
        }
    }

    @GetMapping(path = "/create")
    public String doCreateGet() {
        return createViewName;
    }

    @PostMapping(path = "/create")
    public String doCreatePost(@ModelAttribute("form") @Valid F form, BindingResult result,
                               ModelMap map) {
        if(!isValid(form, result)) {
            return createViewName;
        }

        Community community = getNewEntity(form);
        //todo move to form/entity?
        fillEntity(form, community);
        communityService.create(community);
        return "redirect:/communities/";
    }

    /**
     * Creates new entity given the form and performs child class - specific operations
     * (like filling child-specific fields).
     * Entity returned will be saved to DB
     * @param form form
     * @return entity to save to the DB
     */
    protected abstract Community getNewEntity(F form);

    @Autowired
    public void setCommunityService(CommunityService<Community> communityService) {
        this.communityService = communityService;
    }
}
