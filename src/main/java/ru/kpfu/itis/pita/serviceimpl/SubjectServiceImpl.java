package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Subject;
import ru.kpfu.itis.pita.repository.SemesterRepository;
import ru.kpfu.itis.pita.repository.SubjectRepository;
import ru.kpfu.itis.pita.service.SubjectService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 5:44 PM
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository repository;
    private SemesterRepository semesterRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository repository, SemesterRepository semesterRepository) {
        this.repository = repository;
        this.semesterRepository = semesterRepository;
    }

    @Override
    public Subject findCurrentByName(String name) {
        return repository.findByNameAndSemester(name, semesterRepository.findCurrentSemester());
    }

    @Override
    public Subject findById(int id) {
        return repository.findOne(id);
    }

    @Override
    public Subject save(Subject subject) {
        if(subject.getSemester() == null) {
            subject.setSemester(semesterRepository.findCurrentSemester());
        }
        return repository.save(subject);
    }
}
