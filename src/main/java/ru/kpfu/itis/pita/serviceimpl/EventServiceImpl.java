package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.repository.EventRepository;
import ru.kpfu.itis.pita.repository.GroupRepository;
import ru.kpfu.itis.pita.service.EventService;
import ru.kpfu.itis.pita.entity.Event;
/**
 * Created by volkov on 28.03.2017.
 */
@Service("eventServiceImpl")
public class EventServiceImpl  implements EventService {
    private EventRepository eventRepository;
    private GroupRepository groupRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }
    @Override
    public boolean exists(String name) {
        return groupRepository.findByName(name) != null;
    }

}
