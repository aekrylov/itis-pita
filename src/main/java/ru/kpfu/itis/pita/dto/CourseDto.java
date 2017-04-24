package ru.kpfu.itis.pita.dto;

import ru.kpfu.itis.pita.entity.Course;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/19/17 5:54 PM
 */
public class CourseDto extends CommunityDto {

    private int capacity;

    public CourseDto(Course course) {
        super(course);
        capacity = course.getCapacity();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
