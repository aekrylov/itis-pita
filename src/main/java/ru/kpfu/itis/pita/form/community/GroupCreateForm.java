package ru.kpfu.itis.pita.form.community;

import org.hibernate.validator.constraints.NotEmpty;
import ru.kpfu.itis.pita.entity.Group;
import ru.kpfu.itis.pita.entity.Interest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by taa on 30.03.17.
 */
public class GroupCreateForm extends CommunityCreateForm<Group> {

    @NotEmpty
    private String interests;

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public void toEntity(Group entity) {
        super.toEntity(entity);
        String[] separated_tags = interests.split(",");

        //todo save to DB?
        List<Interest> interests = Arrays.stream(separated_tags)
                .map(Interest::new)
                .collect(Collectors.toList());

        entity.setInterests(interests);
    }

    @Override
    public GroupCreateForm fromEntity(Group entity) {
        interests = String.join(", ", entity.getInterests().stream()
                .map(Interest::getName)
                .collect(Collectors.toList()));
        super.fromEntity(entity);
        return this;
    }
}
