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

    @PostMapping("create")
    public Role createRole(@RequestBody Role role) {
        return this.roleService.createRole(role);
    }

    @GetMapping("list")
    public List<Role> getRoles() {
        return this.roleService.getRoles();
    }

    @PutMapping
    public Role updateRole(@RequestBody Role role) {
        return this.roleService.updateRole(role);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody Role role) {
        this.roleService.deleteRole(role);
    }
}
