package com.akropoliprishtine.services;

import com.akropoliprishtine.entities.Permission;
import com.akropoliprishtine.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> getAll() {
        return this.permissionRepository.findAll();
    }
}
