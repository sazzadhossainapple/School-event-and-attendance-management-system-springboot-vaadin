package com.example.springbootfrontend.service;

import com.example.springbootfrontend.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.example.springbootfrontend.model.EventType.Chess;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {
    @Autowired
    private EventEventService eventService;

    @Test
    public void testFindAll(){
        List<Event> eventList = eventService.findAll();
        assertNotNull(eventList);
       eventList.forEach(System.out::println);

    }


    @Test
    public void testFindById(){
       Event expectedEvent = new Event(2019000000199l, LocalDateTime.of(2020, Month.JUNE,5,3,0),"University", Chess);
       Event actualById= eventService.findById(2019000000199l);
        assertEquals(expectedEvent,actualById);

    }

}
