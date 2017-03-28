package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.repository.EventRepository;
import ru.kpfu.itis.pita.service.EventService;
import ru.kpfu.itis.pita.entity.Event;
/**
 * Created by volkov on 28.03.2017.
 */
@Service("eventService")
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public boolean exists(String eventName) {
        return eventRepository.findByName(eventName) != null;
    }
}
