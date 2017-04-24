package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Course;
import ru.kpfu.itis.pita.entity.Semester;
import ru.kpfu.itis.pita.entity.Subject;
import ru.kpfu.itis.pita.repository.CourseRepository;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.repository.SemesterRepository;
import ru.kpfu.itis.pita.service.CourseService;

import java.util.Date;

/**
 * Created by 1 on 29.03.2017.
 */
@Service("courseService")
public class CourseServiceImpl extends CommunityServiceImpl<Course> implements CourseService{

    private CourseRepository courseRepository;
    private SemesterRepository semesterRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, GroupRepository groupRepository,
                             SemesterRepository semesterRepository) {
        super(courseRepository, groupRepository);
        this.courseRepository = courseRepository;
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
        return courseRepository.save(course);
    }

}
