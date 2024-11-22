package com.csaladfa.controller;

import com.csaladfa.DAO.PersonRepository;
import com.csaladfa.model.Person;
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
        model.addAttribute("username", getCurrentUsername());
        //List<FamilyMember> familyMembers = familyMemberService.findByUserId(getCurrentUserId());
        //model.addAttribute("familyMembers", familyMembers);
        return "edit";
    }
    @GetMapping("/add-family-member")
    public String famly_tree(){
        return "edit";
    }
    @GetMapping("/edit")
    public String showPeople(Model model) {
        List<Person> people = personService.listPeople();
        System.out.println(people);
        model.addAttribute("familyMembers", people);
        return "edit";
    }
}
