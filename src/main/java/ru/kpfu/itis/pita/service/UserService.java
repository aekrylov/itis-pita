package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.User;

import java.util.List;

/**
 * Created by 1 on 08.04.2017.
 */
public interface UserService {
    void save(User user);

    User findByEmail(String email);
    User findById(int id);

    List<User> findAllWorkers();

    boolean changePassword(User currentUser, String oldPassword, String password);
}