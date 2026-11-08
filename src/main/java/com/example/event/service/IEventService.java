package com.example.event.service;

import com.example.event.dto.EventDto;
import com.example.event.entity.Event;

import java.util.List;

public interface IEventService {

    List<Event> findAllOfEvent(String eventType);

    Event findEventByEventReference(String eventReference);

    Event createEvent(EventDto eventDto);

    Event updateEvent(EventDto eventDto);

    boolean deleteEvent(String eventReference);

}
