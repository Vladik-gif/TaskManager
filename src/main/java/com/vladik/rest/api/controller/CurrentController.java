package com.vladik.rest.api.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CurrentController {

    private static final String CURRENT_AUTH = "/current";

    @GetMapping(CURRENT_AUTH)
    @ResponseBody
    public String currentUser(@AuthenticationPrincipal UserDetails userDetails){
        return "User Details: " + userDetails.getUsername();
    }
}
