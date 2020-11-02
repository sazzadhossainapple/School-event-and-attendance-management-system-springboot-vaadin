package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.Event;
import com.example.springbootbackend.model.EventType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
    public List<Event> findEventByLocalDateTime(LocalDateTime localDateTime);
    public List<Event>findAllByPlace(String place);
    public List<Event> findEventByEventType(EventType eventType);
}
