package com.example.springbootbackend.service;
import com.example.springbootbackend.exception.ResourceAlreadyExistException;
import com.example.springbootbackend.model.Event;
import com.example.springbootbackend.repository.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static com.example.springbootbackend.model.EventType.Chess;
import static com.example.springbootbackend.model.EventType.DrawingCompetitions;
import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void deleteAll(){
        eventRepository.deleteAll();
    }

    @Test
    public void testInsertEvent() {
        Event event = new Event(1234, LocalDateTime.of(2017, Month.FEBRUARY, 23, 0, 0), "university", DrawingCompetitions);

        try {
            Event insertEvent = eventService.insertEvent(event);
            assertEquals(event, insertEvent);
        } catch (ResourceAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ResourceAlreadyExistException.class)
    public void  testInsertExitingEvent() throws ResourceAlreadyExistException{
        Event event1 = new Event(1234,LocalDateTime.of(2000,Month.JANUARY,12,0,0),"university",DrawingCompetitions);
       Event event2 = new Event(1234,LocalDateTime.of(2001,Month.JANUARY,12,0,0),"university", Chess);

        try {
        Event insertEvent = eventService.insertEvent(event1);
            assertEquals(event1,insertEvent);
        } catch (ResourceAlreadyExistException e) {
            e.printStackTrace();
        }
        try {
            Event insertEvent = eventService.insertEvent(event2);
            assertEquals(event2,insertEvent);
        } catch (ResourceAlreadyExistException e) {
            throw e;
        }
    }

}

