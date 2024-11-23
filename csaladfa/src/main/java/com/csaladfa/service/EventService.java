package com.csaladfa.service;

import com.csaladfa.DAO.EventRepository;
import com.csaladfa.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if(Objects.equals(eventRepository.getEvent(event_id).getType(), "MARRIAGE") && eventRepository.personIsMarried(person_id))
            return "This person is already in a marriage!";

        return eventRepository.addPersonToEvent(person_id, event_id);
    }
    public List<Event> listEvents(){
        return eventRepository.listEvents();
    }
}
