package com.example.event.controller;

import com.example.event.constants.EventConstant;
import com.example.event.dto.EventDto;
import com.example.event.dto.ResponseDto;
import com.example.event.entity.Event;
import com.example.event.mapper.EventMapper;
import com.example.event.service.IEventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
@Validated
public class EventController {

    private IEventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<EventDto>> findAllEvents(@RequestParam String eventType){
        List<EventDto> eventDtoList = EventMapper.EventListToEventDtoListMapper(
                new ArrayList<EventDto>(), eventService.findAllOfEvent(eventType));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDtoList);
    }

    @GetMapping("/{eventReference}")
    public ResponseEntity<EventDto> findEvent(@PathVariable String eventReference){
        EventDto eventDto = EventMapper.EventToEventDtoMapper(new EventDto(), eventService.findEventByEventReference(eventReference));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDto);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEvent(@RequestBody @Valid EventDto eventDto){
        Event savedEvent = eventService.createEvent(eventDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(EventConstant.STATUS_201, EventConstant.MESSAGE_201));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateEvent(@RequestBody @Valid EventDto eventDto){
        Event updatedEvent = eventService.updateEvent(eventDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(EventConstant.STATUS_200, EventConstant.MESSAGE_200_UPDATE));
    }

    @DeleteMapping("/delete/{eventReference}")
    public ResponseEntity<ResponseDto> deleteEvent(@PathVariable String eventReference){
        boolean deleted = eventService.deleteEvent(eventReference);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(EventConstant.STATUS_200, EventConstant.MESSAGE_200_DELETE));
    }
}
