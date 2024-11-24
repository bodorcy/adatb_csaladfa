package com.csaladfa.controller;

import com.csaladfa.model.Person;
import com.csaladfa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

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
    @PostMapping("/edit-family-member")
    public String editFamilyMember(RedirectAttributes redirectAttributes,
                                   @RequestParam Integer family_member_id,
                                   @RequestParam(required = false) Integer new_father,
                                   @RequestParam(required = false) Integer new_mother,
                                   @RequestParam(required = false) String new_first_name,
                                   @RequestParam(required = false) String new_last_name,
                                   @RequestParam(required = false) String new_gender,
                                   @RequestParam(required = false) String new_date_of_birth) {

        if (Objects.equals(family_member_id, new_father) || Objects.equals(family_member_id, new_mother)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Person cannot be their own ascendant!");
            return "redirect:/edit";
        }

        Person existingPerson = personService.getPersonById(family_member_id);
        if (existingPerson == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Person with the given ID does not exist!");
            return "redirect:/edit";
        }

        // Update fields only if new values are provided
        existingPerson.setFather_id(new_father != null ? new_father : (existingPerson.getFather_id() != null ? existingPerson.getFather_id() : null));
        existingPerson.setMother_id(new_mother != null ? new_mother : (existingPerson.getMother_id() != null ? existingPerson.getMother_id() : null));
        existingPerson.setFirst_name(!new_first_name.equals("") ? new_first_name : existingPerson.getFirst_name());
        existingPerson.setLast_name(!new_last_name.equals("") ? new_last_name : existingPerson.getLast_name());
        existingPerson.setGender(!new_gender.equals("") ? String.valueOf(new_gender.charAt(0)) : existingPerson.getGender());
        existingPerson.setDate_of_birth(!new_date_of_birth.equals("") ? new_date_of_birth : existingPerson.getDate_of_birth().toString());

        String resultMessage = personService.updatePersonById(family_member_id, existingPerson);

        redirectAttributes.addFlashAttribute("successMessage", resultMessage);

        return "redirect:/edit";
    }

    @PostMapping("/delete-family-member")
    public String deleteFamilyMember(@RequestParam("family-member-delete") int familyMemberId, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("successMessage", personService.deletePerson(familyMemberId));

        return "redirect:/edit";
    }

}
