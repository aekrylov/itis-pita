package ru.kpfu.itis.pita.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.entity.UserRole;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/17/17 3:51 PM
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    //Query is generated automatically based on method name
    User findByEmail(String email);
    User findByEmailAndPasswordHash(String email, String password_hash);
    List<User> findAllByRole(UserRole role);
}
