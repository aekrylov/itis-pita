package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.TimetableClass;
import ru.kpfu.itis.pita.entity.TimetableDate;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 4:35 PM
 */
public interface TimetableDateRepository extends JpaRepository<TimetableDate, Integer> {

    List<TimetableDate> findAllByTimetableClass(TimetableClass timetableClass);
}
