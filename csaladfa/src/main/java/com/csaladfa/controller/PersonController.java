package com.csaladfa.controller;

import com.csaladfa.model.Person;
import com.csaladfa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PersonController {
    @Autowired
    PersonService personService;
    public String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/add-family-member")
    public String addFamilyMember(RedirectAttributes redirectAttributes,
                                  @RequestParam String first_name,
                                  @RequestParam String last_name,
                                  @RequestParam String gender,
                                  @RequestParam String date_of_birth,
                                  @RequestParam(required = false, defaultValue = "None") String mother,
                                  @RequestParam(required = false, defaultValue = "None") String father
    ){
        Person p = new Person();
        p.setFirst_name(first_name);
        p.setLast_name(last_name);
        p.setGender(String.valueOf(gender.charAt(0)));
        p.setDate_of_birth(date_of_birth);
        p.setMother_id(mother.equals("None") ? null: Integer.parseInt(mother));
        p.setFather_id(father.equals("None") ? null : Integer.parseInt(father));

        redirectAttributes.addFlashAttribute("successMessage", personService.addPerson(p));

        return "redirect:/edit";
    }
    @PostMapping("/delete-family-member")
    public String deleteFamilyMember(@RequestParam("family-member-delete") int familyMemberId, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("successMessage", personService.deletePerson(familyMemberId));

        return "redirect:/edit";
    }

}
