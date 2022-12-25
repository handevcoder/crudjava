package com.example.crud.controller;

import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userSvc;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        User response = userSvc.createUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-user-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userID) {
        User response = userSvc.getUserById(userID);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-user/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> fetchUserList() {
        List<User> response = userSvc.fetchUserList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/ubdate-user-by-id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(@Validated @RequestBody User user,
                                           @PathVariable("id") Long userID) {
        User response = userSvc.updateUser(user, userID);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-user-by-id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userID) {
        String response = userSvc.deleteUserById(userID);
        return ResponseEntity.ok(response);
    }

}
