package com.example.event.mapper;


import com.example.event.dto.EventDto;
import com.example.event.entity.Event;

import java.util.List;

public class EventMapper {

    public static Event EventDtoToEventMapper(EventDto eventDto, Event event){
        event.setEventReference(eventDto.getEventReference());
        event.setEventType(eventDto.getEventType());
        event.setPayoutRatio(eventDto.getPayoutRatio());
        return event;
    }

    public static EventDto EventToEventDtoMapper(EventDto eventDto, Event event){
        eventDto.setEventReference(event.getEventReference());
        eventDto.setEventType(event.getEventType());
        eventDto.setPayoutRatio(event.getPayoutRatio());
        return eventDto;
    }

    public static List<EventDto> EventListToEventDtoListMapper(List<EventDto> eventDtoList, List<Event> eventList){
        for(Event e : eventList){
            EventDto eventDto = EventToEventDtoMapper(new EventDto(), e);
            eventDtoList.add(eventDto);
        }
        return eventDtoList;
    }

}
