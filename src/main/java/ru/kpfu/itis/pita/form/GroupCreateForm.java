package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by taa on 30.03.17.
 */
public class GroupCreateForm extends CommunityCreateForm {

    @NotEmpty
    private String interests;

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
}
