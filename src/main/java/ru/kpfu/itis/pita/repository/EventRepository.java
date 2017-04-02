package ru.kpfu.itis.pita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.pita.entity.Event;
/**
 * Created by volkov on 28.03.2017.
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
}
