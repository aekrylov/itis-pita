package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.Semester;
import ru.kpfu.itis.pita.entity.Subject;
import ru.kpfu.itis.pita.repository.CourseRepository;
import ru.kpfu.itis.pita.repository.SemesterRepository;
import ru.kpfu.itis.pita.service.CourseService;

import java.util.Date;

/**
 * Created by 1 on 29.03.2017.
 */
@Service("courseService")
public class CourseServiceImpl extends BaseCommunityServiceImpl<Course> implements CourseService{

    private final SemesterRepository semesterRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             SemesterRepository semesterRepository) {
        super(courseRepository);
        this.semesterRepository = semesterRepository;
    }

    @Override
    public Course create(Course course) {
        if(course.getSubject() == null) {
            Semester semester = new Semester(new Date().toString(), new Date());  //TODO
            semester = semesterRepository.save(semester);

            Subject subject = new Subject(course.getName());
            subject.setSemester(semester);
            course.setSubject(subject);
        }
        return super.create(course);
    }
}
