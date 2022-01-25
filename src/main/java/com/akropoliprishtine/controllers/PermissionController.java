package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.Permission;
import com.akropoliprishtine.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account_permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/read")
    public List<Permission> getPermissions() {
        return this.permissionService.getAll();
    }
}
