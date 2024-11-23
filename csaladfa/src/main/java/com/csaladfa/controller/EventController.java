package com.csaladfa.controller;

import com.csaladfa.model.Event;
import com.csaladfa.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/add-event")
    public String addEvent(RedirectAttributes redirectAttributes,@RequestParam String event_name, @RequestParam String event_date, @RequestParam String event_type){
        Event e = new Event();
        e.setDate(event_date);
        e.setType(event_type);
        e.setName(event_name);

        redirectAttributes.addFlashAttribute("successMessage", eventService.insertEvent(e));

        return "redirect:/edit";
    }
    @PostMapping("/delete-event")
    public String deleteEvent(RedirectAttributes redirectAttributes, @RequestParam("event-delete") int id){
        redirectAttributes.addFlashAttribute("successMessage", eventService.deleteEvent(id));

        return "redirect:/edit";
    }
    @PostMapping("/assign-family-member-to-event")
    public String addPersonToEvent(RedirectAttributes redirectAttributes, @RequestParam("family-member-id") int person_id, @RequestParam("event-id") int event_id){
        redirectAttributes.addFlashAttribute("successMessage", eventService.addPersonToEvent(person_id, event_id));

        return "redirect:/edit";
    }
}
