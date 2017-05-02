package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.AcademicGroup;
import ru.kpfu.itis.pita.entity.TimetableClass;
import ru.kpfu.itis.pita.entity.TimetableDate;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.repository.AcademicGroupRepository;
import ru.kpfu.itis.pita.repository.SemesterRepository;
import ru.kpfu.itis.pita.repository.TimetableClassRepository;
import ru.kpfu.itis.pita.service.TimetableService;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 4:40 PM
 */
@Service("timetableService")
public class TimetableServiceImpl implements TimetableService {

    private TimetableClassRepository classRepository;
    private SemesterRepository semesterRepository;
    private AcademicGroupRepository academicGroupRepository;

    @Autowired
    public TimetableServiceImpl(TimetableClassRepository classRepository, SemesterRepository semesterRepository, AcademicGroupRepository academicGroupRepository) {
        this.classRepository = classRepository;
        this.semesterRepository = semesterRepository;
        this.academicGroupRepository = academicGroupRepository;
    }

    @Override
    public TimetableClass saveClass(TimetableClass timetableClass) {
        return classRepository.save(timetableClass);
    }

    @Override
    public TimetableClass saveClass(TimetableClass timetableClass, List<String> groups) {
        timetableClass.setGroups(academicGroupRepository.findAllByNameIn(groups));
        return saveClass(timetableClass);
    }

    @Override
    public List<TimetableClass> findAll() {
        return classRepository.findAll();
    }

    @Override
    public List<TimetableClass> findAllByAcademicGroup(AcademicGroup group) {
        //todo and current semester
        return classRepository.findAllByGroupsContains(group);
    }

    @Override
    public List<TimetableClass> findAllByTeacher(User teacher) {
        return classRepository.findAllByTeacher(teacher);
    }

    @Override
    public void addDate(TimetableClass timetableClass, TimetableDate date) {
        if(!timetableClass.getDates().contains(date)){
            timetableClass.getDates().add(date);
        }
        classRepository.save(timetableClass);
    }

    @Override
    public void addDate(int classId, TimetableDate date) {
        addDate(classRepository.findOne(classId), date);
    }
}
