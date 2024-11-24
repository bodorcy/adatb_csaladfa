package com.csaladfa.DAO;

import com.csaladfa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class PersonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List<Person> getPeople(List<Map<String, Object>> rows) {
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
    public boolean personAlreadyExists(Person person){
        String checkSql = "SELECT COUNT(*) FROM person WHERE first_name = ? AND last_name = ? AND gender = ? AND date_of_birth = ?";
        return 0 < jdbcTemplate.queryForObject(checkSql, int.class, person.getFirst_name(), person.getLast_name(), person.getGender(), person.getDate_of_birth().toString());
    }

    public String createPerson(Person person){
        if (personAlreadyExists(person))
           return "Person already exists!";

        String sql = "INSERT INTO person (mother_id, father_id, first_name, last_name, gender, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, person.getMother_id(), person.getFather_id(), person.getFirst_name(), person.getLast_name(), person.getGender(), person.getDate_of_birth().toString());

            if(rowsAffected > 0)
                return "Person created!";
            else
                return "Person could not be created.";

        }
        catch (DataIntegrityViolationException e) {
            return "Database error: " + e.toString();
        }
    }
    public String updatePersonById(int id, Person person) {
        String checkSql = "SELECT COUNT(*) FROM person WHERE id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);

        if (count == 0) {
            return "Person with the given ID does not exist!";
        }

        String updateSql = "UPDATE person SET mother_id = ?, father_id = ?, first_name = ?, last_name = ?, gender = ?, date_of_birth = ? WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(
                    updateSql,
                    person.getMother_id(),
                    person.getFather_id(),
                    person.getFirst_name(),
                    person.getLast_name(),
                    person.getGender(),
                    person.getDate_of_birth().toString(),
                    id
            );

            if (rowsAffected > 0) {
                return "Person updated successfully!";
            } else {
                return "Person could not be updated.";
            }
        } catch (DataIntegrityViolationException e) {
            return "Database error: " + e.toString();
        }
    }

    public int deletePerson(Integer id){
        String sql = "DELETE FROM person WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }
    public Person getPersonById(Integer id){
        String sql = "SELECT * FROM person WHERE id = ?";
        Map<String, Object> row = jdbcTemplate.queryForMap(sql, id);

        Person person = new Person();
        person.setId((Integer) row.get("id"));
        person.setFirst_name((String) row.get("first_name"));
        person.setLast_name((String) row.get("last_name"));
        person.setGender((String) row.get("gender"));
        person.setDate_of_birth((Date) row.get("date_of_birth"));
        person.setMother_id((Integer) row.get("mother_id"));
        person.setFather_id((Integer) row.get("father_id"));

        return person;
    }

    public String updatePersonById(Integer id, Person person) {
        String checkSql = "SELECT COUNT(*) FROM person WHERE id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);

        if (count == 0) {
            return "Person with the given ID does not exist!";
        }
        String updateSql = "UPDATE person SET mother_id = ?, father_id = ?, first_name = ?, last_name = ?, gender = ?, date_of_birth = ? WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(
                    updateSql,
                    person.getMother_id(),
                    person.getFather_id(),
                    person.getFirst_name(),
                    person.getLast_name(),
                    person.getGender(),
                    person.getDate_of_birth().toString(),
                    id
            );

            if (rowsAffected > 0) {
                return "Person updated successfully!";
            } else {
                return "Person could not be updated.";
            }
        } catch (DataIntegrityViolationException e) {
            return "Database error: " + e.toString();
        }
    }


    public List<Person> listPeople(){
        String sql = "SELECT * FROM person";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        return getPeople(rows);
    }
    public List<Person> getSiblings(Integer id){
        String sql = "SELECT * FROM person WHERE (father_id = (SELECT father_id FROM person WHERE id = ?) OR mother_id = (SELECT mother_id FROM person WHERE id = ?)) AND NOT id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id, id, id);
        return getPeople(rows);
    }
    public List<Person> getParents(Integer id){
        String sql = "SELECT * FROM person WHERE id = (SELECT father_id FROM person WHERE id = ?) OR id = (SELECT mother_id FROM person WHERE id = ?)";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id, id);
        return getPeople(rows);
    }
    public List<Person> getChildren(Integer id){
        String sql = "SELECT * FROM person WHERE father_id = ? OR mother_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id, id);
        return getPeople(rows);
    }

    public Long countChildren(Integer id){
        Person p = getPersonById(id);
        String fatherOrMother = p.getGender().equals("m") ? "father_id" : "mother_id";
        String sql = "SELECT number_of_children FROM person JOIN (SELECT " + fatherOrMother + ", COUNT(" + fatherOrMother + ") AS number_of_children FROM person GROUP BY " + fatherOrMother + " HAVING " + fatherOrMother + " = ?) AS parent ON person.id = parent." + fatherOrMother;
        System.out.println(sql);
        try{
            Map<String, Object> row = jdbcTemplate.queryForMap(sql, id);

            return (Long) row.get("number_of_children");
        }
        catch (EmptyResultDataAccessException e){
            return 0L;
        }
    }
}
