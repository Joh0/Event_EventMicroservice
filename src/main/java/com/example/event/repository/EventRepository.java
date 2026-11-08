package com.example.event.repository;

import com.example.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByEventType(String eventType);

    Optional<Event> findByEventReference(String eventReference);
}
