package com.csaladfa.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

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
    @GetMapping("/edit")
    public String edit(Model model){
        //List<FamilyMember> familyMembers = familyMemberService.findAll();
        //List<Event> events = eventService.findAll();
        //model.addAttribute("familyMembers", familyMembers);
        //model.addAttribute("events", events);
        return "edit";
    }
    @GetMapping("/family-tree")
    public String famly_tree(Model model){
        //List<FamilyMember> familyMembers = familyMemberService.findByUserId(getCurrentUserId());
        //model.addAttribute("familyMembers", familyMembers);
        return "family-tree";
    }
}
