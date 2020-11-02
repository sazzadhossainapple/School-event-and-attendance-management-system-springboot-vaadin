package com.example.springbootbackend.repository;
import com.example.springbootbackend.model.Event;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.springbootbackend.model.EventType.Chess;
import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;
    @Test
    public void  testCreatedEvent(){
        Event event= new Event(1238l, LocalDateTime.now(),"university",Chess);
        Event savedEvent = eventRepository.save(event);
        assertEquals(event,savedEvent);
    }
}
