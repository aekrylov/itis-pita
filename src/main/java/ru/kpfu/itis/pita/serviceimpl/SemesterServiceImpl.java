package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Semester;
import ru.kpfu.itis.pita.repository.SemesterRepository;
import ru.kpfu.itis.pita.service.SemesterService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 7:38 PM
 */
@Service("semesterService")
public class SemesterServiceImpl implements SemesterService {

    private final SemesterRepository repository;

    @Autowired
    public SemesterServiceImpl(SemesterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Semester getCurrentSemester() {
        return repository.findCurrentSemester();
    }
}
