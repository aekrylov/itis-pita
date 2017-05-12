package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Student;
import ru.kpfu.itis.pita.repository.StudentRepository;
import ru.kpfu.itis.pita.service.StudentService;

import java.util.List;

/**
 * Created by volkov on 10.05.2017.
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    @Override
    public List<Student> findAllByStartYear(int year) {
        return studentRepository.findAllByStartYear( year);
    }

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

}
