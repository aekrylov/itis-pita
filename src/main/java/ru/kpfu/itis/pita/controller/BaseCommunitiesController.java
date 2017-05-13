package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.form.community.CommunityCreateForm;
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
public abstract class BaseCommunitiesController<E extends Community> {

    private final String createViewName = "communities_create";
    private CommunityService<E> communityService;
    private final Community.CommunityType type;

    protected BaseCommunitiesController(Community.CommunityType type) {
        this.type = type;
    }

    @ModelAttribute("form")
    public CommunityCreateForm<E> newCreateForm() {
        return new CommunityCreateForm<E>();
    }

    /**
     * Creates new empty entity
     * @return new entity
     */
    protected abstract E getNewEntity();

    @ModelAttribute("type")
    public Community.CommunityType communityType() {
        return type;
    }

    private boolean isValid(CommunityCreateForm form, BindingResult result) {
        return !(result.hasErrors() || communityService.exists(form.getName()));
    }

    @GetMapping(path = "/create")
    public String doCreateGet() {
        return createViewName;
    }

    @PostMapping(path = "/create")
    public String doCreatePost(@ModelAttribute("form") @Valid CommunityCreateForm<E> form, BindingResult result,
                               ModelMap map) {
        if(!isValid(form, result)) {
            return createViewName;
        }

        E community = getNewEntity();
        //todo move to form/entity?
        form.toEntity(community);
        communityService.create(community);
        return "redirect:/communities/";
    }

    @Autowired
    public void setCommunityService(CommunityService<E> communityService) {
        this.communityService = communityService;
    }
}
