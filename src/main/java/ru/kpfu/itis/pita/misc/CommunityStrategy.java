package ru.kpfu.itis.pita.misc;

import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.form.CommunityCreateForm;
import ru.kpfu.itis.pita.service.CommunityService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 1:02 PM
 */
public abstract class CommunityStrategy<F extends CommunityCreateForm> {

    private CommunityService service;

    public CommunityStrategy(CommunityService service) {
        this.service = service;
    }

    public Group getEntity(int id) {
        return service.getOne(id);
    }

    public abstract CommunityCreateForm newCreateForm();

    public abstract Group createEntity(F form);



}
