package ru.kpfu.itis.pita.service;

import ru.kpfu.itis.pita.entity.Event;
/**
 * Created by volkov on 28.03.2017.
 */
public class EventService {
    Event create(Event event);

    boolean exists(String eventName);
}
