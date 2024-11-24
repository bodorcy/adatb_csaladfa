package com.csaladfa.service;

import com.csaladfa.DAO.PersonRepository;
import com.csaladfa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public boolean doesPersonExist(Person person){
        return personRepository.personAlreadyExists(person);
    }
    public Person getPersonById(Integer id){
        return personRepository.getPersonById(id);
    }
    public String addPerson(Person person){
        return personRepository.createPerson(person);
    }
    public List<Person> listPeople(){
        return personRepository.listPeople();
    }
    public List<Person> getParents(Integer id){
        return personRepository.getParents(id);
    }
    public List<Person> getChildren(Integer id){
        return personRepository.getChildren(id);
    }
    public Long countChildren(Integer id) {
        Long n =  personRepository.countChildren(id);

        return n == null ? 0 : n;
    }
    public String deletePerson(Integer id){
        return personRepository.deletePerson(id) == 1 ? "Succesfully deleted person." : "Person not found.";
    }
    public String updatePersonById(Integer id, Person person){
        return personRepository.updatePersonById(id, person);
    }
}
