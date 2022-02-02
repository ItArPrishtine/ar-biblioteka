package com.akropoliprishtine.services;

import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }
    public Role updateRole(Role role) {
        return this.roleRepository.save(role);
    }

    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }
    
    public Optional<Role> findById(Long roleId) {
        return this.roleRepository.findById(roleId);
    }
}
