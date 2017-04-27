package ru.kpfu.itis.pita.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pita.entity.Event;
import ru.kpfu.itis.pita.repository.EventRepository;
import ru.kpfu.itis.pita.service.EventService;
/**
 * Created by volkov on 28.03.2017.
 */
@Service("eventServiceImpl")
public class EventServiceImpl extends BaseCommunityServiceImpl<Event> implements EventService {

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        super(eventRepository);
    }
}
