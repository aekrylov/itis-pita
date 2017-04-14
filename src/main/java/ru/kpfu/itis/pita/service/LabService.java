package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Lab;
import ru.kpfu.itis.pita.entity.User;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:55 PM
 */
public interface LabService {

    Lab create(Lab lab);
    boolean exists(String labName);
    List<Lab> getAll();
    List<Lab> getByUser(User user);
}
