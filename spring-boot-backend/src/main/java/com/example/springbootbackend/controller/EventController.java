package com.example.springbootbackend.controller;

import com.example.springbootbackend.exception.ResourceAlreadyExistException;
import com.example.springbootbackend.exception.ResourceDoseNotExistException;
import com.example.springbootbackend.model.Event;
import com.example.springbootbackend.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v3/events")
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService= eventService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id) {

        try {
            Event event= eventService.findById(id);
            return ResponseEntity.ok(event);
        } catch (ResourceDoseNotExistException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("")
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> eventList = eventService.findAll();
        return ResponseEntity.ok(eventList);
    }


    @PostMapping("")
    public ResponseEntity<Event> insertEvent(@RequestBody Event event) {
        try {
            Event insertEvent = eventService.insertEvent(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertEvent);
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> saveEvent(@RequestBody Event event,
                                          @PathVariable("id") long id) throws ResourceDoseNotExistException {

        Event currentUser = eventService.findById(id);
        if (currentUser == null) {
            return new ResponseEntity(new ResourceDoseNotExistException("Unable to update. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentUser.setLocalDateTime(event.getLocalDateTime());
        currentUser.setPlace(event.getPlace());
        currentUser.setEventType(event.getEventType());
        eventService.updateEvent(id,currentUser);
        return new ResponseEntity<Event>(currentUser, HttpStatus.OK);
    }

//@PutMapping(value = "{id}")
//@ResponseBody
//public Event updateEvent(@PathVariable long id, @RequestBody Event event) throws ResourceDoseNotExistException {
//    return eventService.updateEvent(id,event);
//}

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteEvent(@PathVariable long id) {
        try {
            boolean deleted = eventService.deleteById(id);
            return ResponseEntity.ok(id);
        } catch (ResourceDoseNotExistException e) {
            return ResponseEntity.notFound().build();

        }

    }

}
