package com.csaladfa.service;

import com.csaladfa.DAO.FamilyTreeRepository;
import com.csaladfa.model.FamilyTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyTreeService {
    @Autowired
    private FamilyTreeRepository familyTreeRepository;

    public String createFamilyTree(FamilyTree ft){
        return familyTreeRepository.createFamilyTree(ft);
    }
    public String deleteFamilyTree(Integer id){
        return familyTreeRepository.deleteFamilyTree(id) == 1 ? "Succesfully deleted family tree." : "Could not delete family tree.";
    }
    public String addPersonToFamilyTree(Integer p_id, Integer ft_id){
        return familyTreeRepository.addPersonToFamilyTree(p_id, ft_id);
    }
    public String deletePersonFromFamilyTree(Integer p_id, Integer ft_id){
        return familyTreeRepository.deletePersonFromFamilyTree(p_id, ft_id) == 1 ? "Successfully deleted person from family." : "Could not delete person from family.";
    }
    public List<FamilyTree> listTrees(){
        return familyTreeRepository.listFamilyTrees();
    }
}
