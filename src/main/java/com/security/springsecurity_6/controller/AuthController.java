package com.security.springsecurity_6.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class AuthController {


    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello() {
        return "Hello World";
    }


    @GetMapping("/hello-secured")
    @PreAuthorize("hasAnyAuthority('CREATE')")
    public String helloSecured() {
        return "Hello World Secured";
    }

}
