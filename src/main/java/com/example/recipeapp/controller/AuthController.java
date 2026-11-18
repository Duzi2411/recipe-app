package com.example.recipeapp.controller;

import com.example.recipeapp.dto.RegisterRequest;
import com.example.recipeapp.entity.AppUser;
import com.example.recipeapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("registerRequest") RegisterRequest request,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            AppUser user = userService.register(
                    request.getUsername(),
                    request.getEmail(),
                    request.getPassword()
            );

            return "redirect:/login?registered";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {
            AppUser user = userService.findByUsername(username);

            if (!passwordEncoder.matches(password, user.getPassword())) {
                model.addAttribute("error", "Невалидно потребителско име или парола.");
                return "login";
            }

            session.setAttribute("user_id", user.getId());
            session.setAttribute("user_role", user.getRole().name());

            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Невалидно потребителско име или парола.");
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
