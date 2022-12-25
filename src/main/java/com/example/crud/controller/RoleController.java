package com.example.crud.controller;

import com.example.crud.model.Role;
import com.example.crud.model.response.MessageResponse;
import com.example.crud.repository.RoleRepository;
import com.example.crud.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleSvc;

    @Autowired
    RoleRepository roleRepo;

    @PostMapping("/inject/role")
    public ResponseEntity<?> injectRole(@Valid @RequestBody List<Role> roleRequest) {
        return ResponseEntity.ok(roleSvc.injectRole(roleRequest));
    }

    @PostMapping("/get-all-role")
    public ResponseEntity<?> getAllRole() {
        return ResponseEntity.ok(roleSvc.getRoleRepo());
    }

    @PostMapping("/deleterolesbyid")
    public ResponseEntity<?> deleteRolesById(@RequestBody Role role) {
        roleRepo.deleteById(role.getId());
        return ResponseEntity.ok(new MessageResponse("Delete Succesfully"));
    }

}
