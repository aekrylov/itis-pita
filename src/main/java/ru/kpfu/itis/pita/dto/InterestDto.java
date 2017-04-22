package ru.kpfu.itis.pita.dto;

import ru.kpfu.itis.pita.entity.Interest;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/18/17 10:56 AM
 */
public class InterestDto {

    private int id;
    private String text;
    private String value;

    public InterestDto(Interest interest) {
        id = interest.getId();
        text = interest.getName();
        value = interest.getName();
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
