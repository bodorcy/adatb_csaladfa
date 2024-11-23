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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/")
    public String index(Model model, @RequestParam(name = "family-member-id", required = false) Integer id) {
        // Add family members list for dropdown menu
        model.addAttribute("familyMembers", personService.listPeople());
        model.addAttribute("trees", familyTreeService.listTrees());
        model.addAttribute("username", getCurrentUsername());

        if (id != null) {
            // If a family member is selected, populate additional data
            model.addAttribute("person", personService.getPersonById(id));
            model.addAttribute("parents", personService.getParents(id));
            model.addAttribute("children", personService.getChildren(id));
            model.addAttribute("events", eventService.listPersonsEvents(id));
        } else {
            // Add default empty attributes for initial load
            model.addAttribute("person", null);
            model.addAttribute("parents", null);
            model.addAttribute("children", null);
            model.addAttribute("events", null);
        }

        return "index"; // Render the index.html template
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
