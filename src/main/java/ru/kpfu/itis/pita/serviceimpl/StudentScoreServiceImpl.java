package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.StudentScore;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.repository.StudentScoreRepository;
import ru.kpfu.itis.pita.service.StudentScoreService;

import java.util.List;

/**
 * Created by volkov on 09.05.2017.
 */
@Service("StudentScoreService")
public class StudentScoreServiceImpl implements StudentScoreService {

    @Autowired
    public StudentScoreServiceImpl(StudentScoreRepository studentScoreRepository) {
        this.studentScoreRepository = studentScoreRepository;
    }

    private StudentScoreRepository studentScoreRepository;
    @Override
    public StudentScore saveClass(StudentScore studentScore) {
        return studentScoreRepository.save(studentScore);
    }

    @Override
    public List<StudentScore> findAll() {
        return studentScoreRepository.findAll();
    }

    @Override
    public List<StudentScore> findAllBySemesterNumber(int semester_number) {
        return studentScoreRepository.findAllBySemesterNumber(semester_number);
    }

    @Override
    public List<StudentScore> findAllByStudent(User student) {
        return studentScoreRepository.findAllByStudent(student);
    }

    @Override
    public StudentScore findOne(int id) {
        return studentScoreRepository.findOne(id);
    }
}
