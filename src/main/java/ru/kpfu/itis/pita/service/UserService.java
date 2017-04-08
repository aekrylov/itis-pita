package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.User;

/**
 * Created by 1 on 08.04.2017.
 */
public interface UserService {
    void save(User user);

    User findByEmail(String email);
}