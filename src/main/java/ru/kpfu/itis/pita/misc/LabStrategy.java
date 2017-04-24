package ru.kpfu.itis.pita.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.form.CommunityCreateForm;
import ru.kpfu.itis.pita.form.LabCreateForm;
import ru.kpfu.itis.pita.service.LabService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 2:18 PM
 */
@Component
public class LabStrategy extends CommunityStrategy<LabCreateForm> {

    private LabService service;

    @Autowired
    public LabStrategy(LabService service) {
        super(service);
        this.service = service;
    }

    @Override
    public CommunityCreateForm newCreateForm() {
        return new LabCreateForm();
    }

    @Override
    public Group createEntity(LabCreateForm form) {
        User user = Helpers.getCurrentUser();
        Lab lab = new Lab(form.getName(), form.getDescription(), user, null);

        //todo save image

        return service.create(lab);
    }
}
