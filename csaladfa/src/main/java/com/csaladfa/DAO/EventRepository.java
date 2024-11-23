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

    public boolean personAlreadyInEvent(Integer person_id, Integer event_id){
        String sql = "SELECT COUNT(*) FROM part_of_event WHERE event_id = ? AND person_id = ?";
        return 0 < jdbcTemplate.queryForObject(sql, int.class, person_id, event_id);
    }
    public Event getEvent(Integer id){
        Map<String, Object> row = jdbcTemplate.queryForMap("SELECT * FROM event WHERE id = ?", id);

        Event e = new Event();
        e.setId((Integer) row.get("id"));
        e.setDate((Date) row.get("date"));
        e.setType((String) row.get("type"));

        return e;
    }

    public String createEvent(Event event){
        String sql = "INSERT INTO event (type, date) VALUES(? ,?)";

        try{
            int rowsAffected = jdbcTemplate.update(sql, event.getType(), event.getDate().toString());

            if(rowsAffected > 0)
                return "Event added with id: " + event.getId();
            else
                return "Event could not be added.";
        }
        catch (DataIntegrityViolationException e) {
            return "Database error: " + e.toString();
        }
    }
    public String addPersonToEvent(Integer person_id, Integer event_id){
        if(personAlreadyInEvent(person_id, event_id))
            return "Person already in the selected even.";

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
    public List<Event> listEventsOfPerson(Integer person_id){
        String sql = "SELECT * FROM event WHERE id = ?";

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
    public boolean personIsMarried(Integer person_id){

        Integer marriages = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM event JOIN part_of_event ON event.id = part_of_event.event_id WHERE person_id = ? AND type = 'MARRIAGE'",
                Integer.class, person_id);

        Integer divorces = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM event JOIN part_of_event ON event.id = part_of_event.event_id WHERE person_id = ? AND type = 'DIVORCE'",
                Integer.class, person_id);

        if(marriages == 0)
            return false;
        if(divorces == 0)
            return true;

        String sql = "SELECT " +
                "DATEDIFF(d.date, m.date) AS date_difference " +
                "FROM " +
                "(SELECT date FROM event JOIN part_of_event ON event.id = part_of_event.event_id WHERE person_id = ? AND type = 'MARRIAGE' ORDER BY date DESC LIMIT 1) m " +
                "JOIN " +
                "(SELECT date FROM event JOIN part_of_event ON event.id = part_of_event.event_id WHERE person_id = ? AND type = 'DIVORCE' ORDER BY date DESC LIMIT 1) d " +
                "ON 1 = 1;";

        Integer diff_in_days = jdbcTemplate.queryForObject(sql, Integer.class, person_id, person_id);

        return diff_in_days != null && diff_in_days <= 0;
    }
}
