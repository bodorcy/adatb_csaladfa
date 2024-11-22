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
    public String addPerson(Person person){
        return personRepository.createPerson(person);
    }
    public List<Person> listPeople(){
        return personRepository.listPeople();
    }
    public String deletePerson(Integer id){
        return personRepository.deletePerson(id) == 1 ? "Succesfully deleted person." : "Person not found.";
    }

}
