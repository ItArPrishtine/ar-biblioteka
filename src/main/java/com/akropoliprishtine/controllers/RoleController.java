package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account_role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public List<Role> getRoles() {
        return this.roleService.getRoles();
    }
    
    @PutMapping("/change-permissions")
    public Role updateRole(@RequestBody Role role) {
        return this.roleService.updateRole(role);
    }
}
