package com.csaladfa.DAO;

import com.csaladfa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class PersonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public boolean personAlreadyExists(Person person){
        String checkSql = "SELECT COUNT(*) FROM person WHERE first_name = ? AND last_name = ? AND gender = ? AND date_of_birth = ?";
        return 0 < jdbcTemplate.queryForObject(checkSql, int.class, person.getFirst_name(), person.getLast_name(), person.getGender(), person.getDate_of_birth().toString());
    }

    public String createPerson(Person person){
        if (personAlreadyExists(person))
           return "Person already added to family tree!";

        String sql = "INSERT INTO person (mother_id, father_id, first_name, last_name, gender, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, person.getMother_id(), person.getFather_id(), person.getFirst_name(), person.getLast_name(), person.getGender(), person.getDate_of_birth().toString());

            if(rowsAffected > 0)
                return "Person added to family tree!";
            else
                return "Person could not be added to family tree.";

        }
        catch (DataIntegrityViolationException e){
            return "Database error"; //bad practice :)
        }
    }
    public int deletePerson(Integer id){
        String sql = "DELETE FROM person WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

    public List<Person> listPeople(){
        String sql = "SELECT * FROM person";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Person> people = new ArrayList<>();

        for (Map<String, Object> row : rows){
            Person person = new Person();
            person.setId((Integer) row.get("id"));
            person.setFirst_name((String) row.get("first_name"));
            person.setLast_name((String) row.get("last_name"));
            person.setGender((String) row.get("gender"));
            person.setDate_of_birth((Date) row.get("date_of_birth"));
            person.setMother_id((Integer) row.get("mother_id"));
            person.setFather_id((Integer) row.get("father_id"));

            people.add(person);
        }

        return people;
    }
}
