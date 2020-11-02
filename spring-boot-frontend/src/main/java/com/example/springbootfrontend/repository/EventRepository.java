package com.example.springbootfrontend.repository;


import com.example.springbootfrontend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
//    public List<Event> findEventByLocalDateTime(LocalDateTime localDateTime);
//    public List<Event>findAllByPlace(String place);
//    public List<Event> findEventByEventType(EventType eventType);
}
