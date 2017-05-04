package ru.kpfu.itis.pita.misc;

import java.time.LocalTime;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/4/17 4:42 PM
 */
public enum LessonStartTime {
    FIRST("08:30"), SECOND("10:10"), THIRD("11:50"),
    FOURTH("13:35"), FIFTH("15:20"), SIXTH("17:00"), SEVENTH("18:40");

    private String string;
    private LocalTime time;

    LessonStartTime(String string) {
        this.string = string;
        this.time = LocalTime.parse(string);
    }

    public String getString() {
        return string;
    }

    public LocalTime getTime() {
        return time;
    }
}
