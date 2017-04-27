package ru.kpfu.itis.pita.form;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

/**
 * Created by 1 on 29.03.2017.
 */
public class CourseCreateForm extends CommunityCreateForm {

    private String schedule;

    @NotNull
    @NumberFormat
    private int capacity;

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
