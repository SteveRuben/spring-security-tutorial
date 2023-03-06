package org.abodah.sample.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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

    @RequestMapping("/private/{hello}")
    @ResponseBody
    public String privateHello(@PathVariable("{hello}") String hello) {
        return hello + " found the secret lair!";
    }

    @RequestMapping("/me")
    @ResponseBody
    @PreAuthorize("authenticated")
    public String getMessage() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return "Hello " + authentication;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/restricted")
    @ResponseBody
    public String adminAuthoritiesOnly() {
        return "You found the secret lair!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/restricted_role")
    @ResponseBody
    public String adminRole() {
        return "You found the secret lair!";
    }
}
