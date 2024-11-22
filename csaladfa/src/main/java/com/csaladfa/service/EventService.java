package com.csaladfa.service;

import com.csaladfa.DAO.EventRepository;
import com.csaladfa.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public String insertEvent(Event event){
        return eventRepository.createEvent(event);
    }
    public String deleteEvent(Integer id){
        return eventRepository.deleteEvent(id) == 1 ? "Succesfully deleted event!" : "Could not delete event.";
    }
    public String addPersonToEvent(Integer person_id, Integer event_id){
        return eventRepository.addPersonToEvent(person_id, event_id);
    }
}
