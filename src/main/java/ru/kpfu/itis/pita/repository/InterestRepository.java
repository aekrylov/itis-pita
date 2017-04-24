package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.Interest;

import java.util.List;

/**
 * Created by taa on 01.04.17.
 */
public interface InterestRepository extends JpaRepository<Interest, Integer>{

    List<Interest> findAll();
    Interest findByName(String name);
}
