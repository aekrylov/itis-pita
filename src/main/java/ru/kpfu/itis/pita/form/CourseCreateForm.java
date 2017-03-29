package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 1 on 29.03.2017.
 */
public class CourseCreateForm {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private String schedule;

    private MultipartFile image;

    @NotEmpty
    @NumberFormat
    private int capacity;

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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
