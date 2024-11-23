package com.csaladfa.controller;

import com.csaladfa.model.FamilyTree;
import com.csaladfa.service.FamilyTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FamilyTreeController {
    @Autowired
    FamilyTreeService familyTreeService;

    @PostMapping("/create-family-tree")
    public String createTree(RedirectAttributes redirectAttributes, @RequestParam("tree_name") String name){
        FamilyTree ft = new FamilyTree();
        ft.setName(name);
        redirectAttributes.addFlashAttribute("successMessage", familyTreeService.createFamilyTree(ft));

        return "redirect:/family-tree";
    }
    @PostMapping("/delete-family-tree")
    public String deleteTree(RedirectAttributes redirectAttributes, @RequestParam("tree_delete") Integer id){
        redirectAttributes.addFlashAttribute("successMessage", familyTreeService.deleteFamilyTree(id));

        return "redirect:/family-tree";
    }
    @PostMapping("/add-person-to-tree")
    public String addPersonToTree(RedirectAttributes redirectAttributes, @RequestParam("tree_id") Integer tree_id, @RequestParam("person_id") Integer person_id){
        redirectAttributes.addFlashAttribute("successMessage", familyTreeService.addPersonToFamilyTree(person_id, tree_id));

        return "redirect:/family-tree";
    }

    @PostMapping("/remove-person-from-tree")
    public String removePersonFromTree(RedirectAttributes redirectAttributes, @RequestParam("tree_id") Integer tree_id, @RequestParam("person_id") Integer person_id){
        redirectAttributes.addFlashAttribute("successMessage", familyTreeService.deletePersonFromFamilyTree(person_id, tree_id));

        return "redirect:/family-tree";
    }

}
