package com.example.event.service.Impl;

import com.example.event.dto.EventDto;
import com.example.event.entity.Event;
import com.example.event.exception.EventAlreadyExistException;
import com.example.event.exception.EventDoesNotExistException;
import com.example.event.mapper.EventMapper;
import com.example.event.repository.EventRepository;
import com.example.event.service.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImpl implements IEventService {

    private EventRepository eventRepository;

    @Override
    public List<Event> findAllOfEvent(String eventType){
        List<Event> eventList = eventRepository.findAllByEventType(eventType);
        if(eventList.isEmpty()){
            throw new EventDoesNotExistException("No event exists of type: " + eventType);
        }
        return eventList;
    }

    @Override
    public Event findEventByEventReference(String eventReference) {
        return eventRepository.findByEventReference(eventReference).orElseThrow(
                () -> new EventDoesNotExistException("No event exists of reference: " + eventReference)
        );

    }

    @Override
    public Event createEvent(EventDto eventDto) {
        Optional<Event> eventOptional = eventRepository.findByEventReference(eventDto.getEventReference());
        if (eventOptional.isPresent()) {
            throw new EventAlreadyExistException("Event already exist of reference: " + eventDto.getEventReference());
        }
        Event event = EventMapper.EventDtoToEventMapper(eventDto, new Event());
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(EventDto eventDto) {
        Event event = findEventByEventReference(eventDto.getEventReference());
        EventMapper.EventDtoToEventMapper(eventDto, event);
        return eventRepository.save(event);
    }

    @Override
    public boolean deleteEvent(String eventReference) {
        Event event = findEventByEventReference(eventReference);
        eventRepository.deleteById(event.getId());
        return true;
    }

}
