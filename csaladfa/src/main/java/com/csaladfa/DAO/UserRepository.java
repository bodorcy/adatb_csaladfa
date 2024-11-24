package com.csaladfa.DAO;

import com.csaladfa.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean userAlreadyExists(String username){
        String checkSql = "SELECT COUNT(*) FROM user WHERE username = ?";
        return 1 == jdbcTemplate.queryForObject(checkSql, int.class, username);

    }

    public int createUser(User user) {
        String sql = "INSERT INTO user (username, password) VALUES (?, ?)";

        try {
            int rowsAffected = jdbcTemplate.update(sql, user.getUsername(), user.getPassword());

            if (rowsAffected > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (DataIntegrityViolationException e) {
            return 0;
        }
    }

    public Optional<User> findUserByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try {
            Map<String, Object> row = jdbcTemplate.queryForMap(sql, username);
            User user = new User(
                    (String) row.get("username"),
                    (String) row.get("password")
            );
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int updateUser(Long id, User user) {
        String sql = "UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), id);
    }

    public int deleteUser(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
