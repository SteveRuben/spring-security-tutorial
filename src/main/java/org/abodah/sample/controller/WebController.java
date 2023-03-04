package org.abodah.sample.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome home!";
    }

    @RequestMapping("/restricted")
    @ResponseBody
    public String restricted() {
        return "You found the secret lair!";
    }

    @PreAuthorize("hasAuthority('Admin')")
    @RequestMapping("/restricted")
    @ResponseBody
    public String adminOnly() {
        return "You found the secret lair!";
    }
}
