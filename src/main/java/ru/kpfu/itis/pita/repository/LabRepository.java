package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.Lab;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 6:14 PM
 */

public interface LabRepository extends JpaRepository<Lab, Integer> {
}
