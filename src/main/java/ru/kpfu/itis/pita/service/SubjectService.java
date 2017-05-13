package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Subject;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 5:43 PM
 */
public interface SubjectService {

    Subject findCurrentByName(String name);
    Subject findById(int id);

    Subject save(Subject subject);
}