package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceAlreadyExistException;
import com.example.springbootbackend.exception.ResourceDoseNotExistException;
import com.example.springbootbackend.model.Event;
import com.example.springbootbackend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class EventService {
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findById(long id) throws ResourceDoseNotExistException {

        Optional<Event> optionalEvent= eventRepository.findById(id);
        if(optionalEvent.isPresent())
        {
            return optionalEvent.get();
        }
        else {
            throw new ResourceDoseNotExistException(id + "");
        }
    }


    public List<Event> findAll() {
        List<Event>eventList = new ArrayList<>();
        eventRepository.findAll().forEach(eventList:: add);
        return eventList;
    }


    public boolean deleteById(long id) throws ResourceDoseNotExistException{

        Optional<Event> optionalEvent = eventRepository.findById(id);
        optionalEvent.ifPresent(event -> eventRepository.deleteById(id));
        optionalEvent.orElseThrow(() -> new ResourceDoseNotExistException(id + ""));
        return true;

    }

    public Event insertEvent(Event event) throws ResourceAlreadyExistException {
        Optional<Event> optionalEvent= eventRepository.findById(event.getId());
        if (optionalEvent.isPresent()) {
            throw  new ResourceAlreadyExistException(event.getId() + "");
        } else {
            return eventRepository.save(event);
        }

    }

    public Event updateEvent(long id ,Event event) throws ResourceDoseNotExistException {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {

            event.setId(id);
            return eventRepository.save(event);

        } else {
            throw  new ResourceDoseNotExistException(id+ "");
        }
    }
}
