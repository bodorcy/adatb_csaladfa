package com.csaladfa.service;

import com.csaladfa.DAO.UserDAO;
import com.csaladfa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    // CREATE: Register a new user
    public int createUser(User user) {
        return userDAO.createUser(user);
    }

    // READ: Get user by ID
    public Optional<User> getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    // READ: Get user by username
    public Optional<User> getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    // READ: Get all users
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // UPDATE: Update user details
    public int updateUser(Long id, User user) {
        return userDAO.updateUser(id, user);
    }

    // DELETE: Delete a user by ID
    public int deleteUser(Long id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.getUserByUsername(username);
    }
}
