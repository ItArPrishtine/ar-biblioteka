package com.akropoliprishtine.services;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.repositories.RoleRepository;
import com.akropoliprishtine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Role createRole(Role role) {
        return this.roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        if (role != null && role.getId() != null) {
            return this.roleRepository.save(role);
        }
        return null;
    }

    public void deleteRole(Role role) {
        this.roleRepository.delete(role);
    }
}
