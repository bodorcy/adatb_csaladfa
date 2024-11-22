package com.csaladfa.DAO;

import com.csaladfa.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public class EventRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean eventAlreadyExists(Event event){
        String sql = "SELECT COUNT(*) FROM event WHERE id = ?";
        return 0 < jdbcTemplate.queryForObject(sql, int.class, event.getId());
    }

    public String createEvent(Event event){
        if(eventAlreadyExists(event))
            return "Event already exists.";

        String sql = "INSERT INTO event (type, date) VALUES(? ,?)";

        try{
            int rowsAffected = jdbcTemplate.update(sql, event.getType(), event.getDate().toString());

            if(rowsAffected > 0)
                return "Event added!";
            else
                return "Event could not be added.";
        }
        catch (DataIntegrityViolationException e) {
            return "Database error: " + e.toString();
        }
    }
    public String addPersonToEvent(Integer person_id, Integer event_id){
        String sql = "INSERT INTO part_of_event (event_id, person_id) VALUES(?, ?)";

        try{
            int rowsAffected = jdbcTemplate.update(sql, event_id, person_id);

            if(rowsAffected > 0)
                return "Succesfully added person to event!";
            else
                return "Could not add person to event.";
        }
        catch(DataIntegrityViolationException e){
            return "Database error: " + e.toString();
        }
    }
    public int deleteEvent(Integer id){
        String sql = "DELETE FROM event WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    public List<Event> listEvents(){
        String sql = "SELECT * FROM event";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Event> events = new ArrayList<>();

        for (Map<String, Object> row : rows){
            Event e = new Event();
            e.setId((Integer) row.get("id"));
            e.setDate((Date) row.get("date"));
            e.setType((String) row.get("type"));

            events.add(e);
        }

        return events;
    }
}
