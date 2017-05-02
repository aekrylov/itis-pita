package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Subject;
import ru.kpfu.itis.pita.repository.SubjectRepository;
import ru.kpfu.itis.pita.service.SubjectService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 5:44 PM
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository repository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subject findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Subject findById(int id) {
        return repository.findOne(id);
    }
}
