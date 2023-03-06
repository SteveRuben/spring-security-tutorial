package org.abodah.sample.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class WebController {

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome home!";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String restricted() {
        return "You found the secret lair!";
    }

    @RequestMapping("/private")
    @ResponseBody
    @Secured("hasRole('ADMIN')")
    public String privateHello() {
        return " found the secret lair!";
    }

    @RequestMapping("/me")
    @ResponseBody
    @PreAuthorize("authenticated")
    public String getMessage() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return "Hello " + authentication;
    }

    @PreAuthorize("hasAuthority('ADMIN')") // ADMIN
    @RequestMapping("/restricted")
    @ResponseBody
    public String adminAuthoritiesOnly() {
        return "You found the secret lair!";
    }

    @PreAuthorize("hasRole('ADMIN')") // ROLE_ADMIN
    @RequestMapping("/restricted_role")
    @ResponseBody
    public String adminRole() {
        return "You found the secret lair!";
    }
}
