package com.csaladfa.controller;

import com.csaladfa.DAO.PersonRepository;
import com.csaladfa.model.Event;
import com.csaladfa.model.Person;
import com.csaladfa.service.EventService;
import com.csaladfa.service.FamilyTreeService;
import com.csaladfa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContentController {
    @Autowired
    PersonService personService;
    @Autowired
    EventService eventService;
    @Autowired
    FamilyTreeService familyTreeService;

    public String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/index")
    public String index(Model model){
        String username = getCurrentUsername();
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/family-tree")
    public String famliy_tree(Model model){
        model.addAttribute("familyMembers", personService.listPeople());
        model.addAttribute("trees", familyTreeService.listTrees());
        return "family-tree";
    }
    @GetMapping("/add-family-member")
    public String famly_tree(){
        return "edit";
    }

    @GetMapping("/delete-event")
    public String delet_event(){
        return "edit";
    }
    @GetMapping("/edit")
    public String list(Model model) {
        List<Person> people = personService.listPeople();
        List<Event> events = eventService.listEvents();
        model.addAttribute("familyMembers", people);
        model.addAttribute("events", events);
        return "edit";
    }
    @GetMapping("/assign-family-member-to-event")
    public String assign_person_to_event(){
        return "edit";
    }
}
