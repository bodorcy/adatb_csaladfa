package com.csaladfa.DAO;

import com.csaladfa.model.Event;
import com.csaladfa.model.FamilyTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class FamilyTreeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean familyTreeAlreadyExists(String name){
        String sql = "SELECT COUNT(*) FROM family_tree WHERE name = ?";
        return 0 < jdbcTemplate.queryForObject(sql, Integer.class, name);
    }
    public boolean personAlreadyInFamilyTree(Integer p_id, Integer ft_id){
        String sql = "SELECT COUNT(*) FROM part_of_family WHERE person_id = ? AND family_id = ?";
        return 0 < jdbcTemplate.queryForObject(sql, Integer.class, p_id, ft_id);
    }
    public String createFamilyTree(FamilyTree ft){
        if(familyTreeAlreadyExists(ft.getName()))
            return "Family tree already exists!";
        String sql = "INSERT INTO family_tree (name) VALUES(?)";
        try{
            int rowsAffected = jdbcTemplate.update(sql, ft.getName());

            if(rowsAffected > 0)
                return "Family tree created: " + ft.getName();
            else
                return "Family tree could not be created";
        }
        catch (DataIntegrityViolationException e){
            return "Database error: " + e.toString();
        }
    }
    public int deleteFamilyTree(Integer id){
        String sql = "DELETE FROM family_tree WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    public String addPersonToFamilyTree(Integer person_id, Integer ft_id){
        if(personAlreadyInFamilyTree(person_id, ft_id))
            return "Person already in the selected family tree.";

        String sql = "INSERT INTO part_of_family (family_id, person_id) VALUES(?, ?)";

        try{
            int rowsAffected = jdbcTemplate.update(sql, ft_id, person_id);

            if(rowsAffected > 0)
                return "Succesfully added person to family!";
            else
                return "Could not add person to family.";
        }
        catch(DataIntegrityViolationException e){
            return "Database error: " + e.toString();
        }
    }
    public int deletePersonFromFamilyTree(Integer person_id, Integer ft_id){
        String sql = "DELETE FROM part_of_family WHERE family_id = ? AND person_id = ?";

        return jdbcTemplate.update(sql, ft_id, person_id);
    }

    public List<FamilyTree> listFamilyTrees(){
        String sql = "SELECT * FROM family_tree";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<FamilyTree> trees = new ArrayList<>();

        for (Map<String, Object> row : rows){
            FamilyTree ft = new FamilyTree();
            ft.setId((Integer) row.get("id"));
            ft.setName((String) row.get("name"));

            trees.add(ft);
        }

        return trees;
    }
}
