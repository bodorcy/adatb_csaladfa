package com.csaladfa.controller;

import com.csaladfa.DAO.UserRepository;
import com.csaladfa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Handle login post request
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findUserByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // You can add a session attribute or redirect to another page
            model.addAttribute("message", "Login successful!");
            return "index"; // Redirect to a homepage or dashboard
        } else {
            model.addAttribute("message", "Invalid username or password.");
            return "login"; // Return to login page
        }
    }
    @PostMapping("/register")
    public String createUser(@RequestParam String username, @RequestParam String password, Model model){
        User u = new User(username, passwordEncoder.encode(password));
        int succes =  userRepository.createUser(u);
        if(succes != 1) {
            model.addAttribute("message", "Username already taken!");
            return "register";
        };
        return "login";
    }
}
