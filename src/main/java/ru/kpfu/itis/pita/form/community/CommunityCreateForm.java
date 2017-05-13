package ru.kpfu.itis.pita.form.community;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.pita.entity.Community;
import ru.kpfu.itis.pita.form.InfoForm;
import ru.kpfu.itis.pita.misc.Helpers;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/24/17 12:53 PM
 *
 * Base class for community forms
 */
public class CommunityCreateForm<E extends Community> implements InfoForm<E> {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

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

    @Override
    public void toEntity(E entity) {
        entity.setName(getName());
        entity.setDescription(getDescription());

        if(entity.getCreator() == null)
            entity.setCreator(Helpers.getCurrentUser());

        if(getImage() != null && getImage().getSize() > 0) {
            entity.setImageLink(Helpers.uploadImage(getImage()));
        }
    }

    @Override
    public CommunityCreateForm<E> fromEntity(E entity) {
        name = entity.getName();
        description = entity.getDescription();

        return this;
    }
}
