package com.csaladfa.controller;

import com.csaladfa.model.User;
import com.csaladfa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.csaladfa.config.WebSecurityConfig.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Show login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Returns the login.html Thymeleaf template
    }

    // Handle login post request
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.getUserByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // You can add a session attribute or redirect to another page
            model.addAttribute("message", "Login successful!");
            return "home"; // Redirect to a homepage or dashboard
        } else {
            model.addAttribute("message", "Invalid username or password.");
            return "login"; // Return to login page
        }
    }

    // Show registration page
    @GetMapping("/register")
    public String registerPage() {
        return "register";  // Returns the register.html Thymeleaf template
    }

    // Handle registration post request
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.getUserByUsername(username).isPresent()) {
            model.addAttribute("message", "Username already taken.");
            return "register"; // Return to registration page
        }
        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(password);

        // Create and save the user
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        userService.createUser(newUser);  // Save user to DB

        model.addAttribute("message", "Registration successful!");
        return "login";  // Redirect to login page after successful registration
    }
}
