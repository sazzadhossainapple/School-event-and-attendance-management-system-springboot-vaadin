package com.example.springbootfrontend.service;

import com.example.springbootfrontend.model.Event;
import com.example.springbootfrontend.model.EventType;
import com.example.springbootfrontend.repository.EventRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EventEventService implements GenericEventService<Event,Long> {


    private EventRepository eventRepository;
    public EventEventService(EventRepository eventRepository){
        this.eventRepository= eventRepository;

    }



    public Event findById(Long id)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Event> response = restTemplate
                .exchange(
                        "https://schooleventmanagementsystem.herokuapp.com/api/v3/events" + "/" + id,
                        HttpMethod.GET,
                        null,
                        Event.class);
        Event event=response.getBody();
        return event;

    }

    public List<Event> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Event>> response = restTemplate
                .exchange(
                        "https://schooleventmanagementsystem.herokuapp.com/api/v3/events",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Event>>() {
                        }
                );
        List<Event>eventList=response.getBody();
        return eventList;

    }
    public Event saveEvent(Event event){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Event> studentHttpEntity =new HttpEntity<>(event);
        ResponseEntity<Event> eventResponseEntity = restTemplate
                .exchange(
                        "https://schooleventmanagementsystem.herokuapp.com/api/v3/events",
                        HttpMethod.POST,
                        studentHttpEntity,
                       Event.class);

        return eventResponseEntity.getBody();
    }

    public void delete(Event event){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Event> response =
                restTemplate.exchange("https://schooleventmanagementsystem.herokuapp.com/api/v3/events/{id}",
                        HttpMethod.DELETE,
                        new HttpEntity<>(event),
                        Event.class,
                        event.getId());
        response.getBody();

    }


    public long count() {
       return eventRepository.count();
    }

    public Map<EventType, Long> getStats() {
        HashMap<EventType, Long> stats = new HashMap<>();
        for (Event event : findAll()) {
            stats.put(event.getEventType(),event.getId());
        }
        return stats;
    }


}
