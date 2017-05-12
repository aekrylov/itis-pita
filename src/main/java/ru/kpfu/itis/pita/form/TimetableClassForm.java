package ru.kpfu.itis.pita.form;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/4/17 4:03 PM
 */
public class TimetableClassForm {

    private String subject;

    @Size(min = 1)
    private List<String> groups;

    private int teacherId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private List<LocalDateTime> startTimes = new ArrayList<>();
    private List<String> places = new ArrayList<>();

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public List<LocalDateTime> getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(List<LocalDateTime> startTimes) {
        this.startTimes = startTimes;
    }

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }
}
