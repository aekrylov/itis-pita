package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.User;

import java.util.List;

/**
 * Created by 1 on 29.03.2017.
 */
public interface CourseRepository  extends JpaRepository<Course, Integer>{

    Course findByName(String name);

    List<Course> findByMember(User user);
}
