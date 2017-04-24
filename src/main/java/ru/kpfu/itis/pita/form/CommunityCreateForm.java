package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 12:53 PM
 *
 * Base class for community forms
 */
public class CommunityCreateForm {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    //todo checks
    private MultipartFile image;

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
