package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Subject;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 5:43 PM
 */
public interface SubjectService {

    Subject findCurrentByName(String name);
    Subject findById(int id);

    Subject save(Subject subject);
    List<Subject> findAllBySemesterNumber(int semesterNumber);
    List<Subject> findAllSortBySemesterNumber();
    List<Subject> findAll();
    public List<Subject> findAllBySemesterNumberLessThanOrderBySemesterNumber (int semesterNumber);
}
