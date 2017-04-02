package ru.kpfu.itis.pita.form;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.pita.entity.Interest;

import java.util.Collection;

/**
 * Created by taa on 30.03.17.
 */
public class GroupCreateForm {
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    Collection<Interest> interests;

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

    public Collection<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Collection<Interest> interests) {
        this.interests = interests;
    }
}
