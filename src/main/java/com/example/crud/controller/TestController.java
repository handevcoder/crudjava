package com.example.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public String allAcces(){
        return "This is a public Content.";
    }


    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public String userAcces(){
        return "This is a User Content.";
    }

    @GetMapping("/super")
    @PreAuthorize("hasRole('SUPER')")
    public String superAcces(){
        return "This is a Super Board.";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAcces(){
        return "This is a Admin Board.";
    }

}
