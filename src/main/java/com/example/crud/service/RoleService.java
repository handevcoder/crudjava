package com.example.crud.service;

import com.example.crud.model.Role;
import com.example.crud.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepo;

    public List<Role> injectRole(List<Role> role) {
        List<Role> roles = new ArrayList<>();
        try {
            roles = roleRepo.saveAll(role);
        } catch (Exception e) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public List<Role> getRoleRepo() {
        List<Role> roles = new ArrayList<>();
        try {
            roles = roleRepo.findAll();
        } catch (Exception e) {
            roles = new ArrayList<>();
            System.out.println(e.getMessage());
        }
        return roles;
    }
}
