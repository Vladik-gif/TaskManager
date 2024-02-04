package com.vladik.rest.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {
    @GetMapping("/info")
    public String info(Principal principal) {
        return principal.getName();
    }
}