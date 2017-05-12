package ru.kpfu.itis.pita.form.community;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by cmen on 4/24/17.
 */
public class GroupEditForm {
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String interests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
}