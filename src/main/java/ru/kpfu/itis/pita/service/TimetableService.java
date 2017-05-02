package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.AcademicGroup;
import ru.kpfu.itis.pita.entity.TimetableClass;
import ru.kpfu.itis.pita.entity.TimetableDate;
import ru.kpfu.itis.pita.entity.User;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 4:35 PM
 */
public interface TimetableService {

    TimetableClass saveClass(TimetableClass timetableClass);
    TimetableClass saveClass(TimetableClass timetableClass, List<String> groups);

    List<TimetableClass> findAll();
    List<TimetableClass> findAllByAcademicGroup(AcademicGroup group);
    List<TimetableClass> findAllByTeacher(User teacher);

    void addDate(TimetableClass timetableClass, TimetableDate date);
    void addDate(int classId, TimetableDate date);

}
