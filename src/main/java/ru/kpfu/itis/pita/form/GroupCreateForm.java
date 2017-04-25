package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by taa on 30.03.17.
 */
public class GroupCreateForm {
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private MultipartFile image; //TODO add to templates

    //@NotEmpty TODO
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
