package com.vladik.rest.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CurrentController {

    private static final String CURRENT_AUTH = "/current";

    @GetMapping(CURRENT_AUTH)
    public String getCurrentUser() {
        // Отримати поточний контекст безпеки
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Отримати деталі користувача
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                String username = userDetails.getUsername();
                return "Current User: " + username;
            } else {
                // Якщо principal - це не UserDetails, можна використовувати principal.toString()
                String username = principal.toString();
                return "Current User: " + username;
            }
        } else {
            // Користувач не аутентифікований
            return "User not authenticated";
        }
    }
}
