package com.akropoliprishtine.services;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class ApplicationUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApplicationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    public ApplicationUser loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(username);
        if (applicationUser == null || !checkPassword(applicationUser, password)) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser;
    }

    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<ApplicationUser> getUsers() {
        return this.userRepository.findAll();
    }

    public ApplicationUser createUser(ApplicationUser applicationUser) {
//        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        return this.userRepository.save(applicationUser);
    }

    public ApplicationUser updateUser(ApplicationUser applicationUser) {
        if (applicationUser != null && applicationUser.getId() != null) {
            return this.userRepository.save(applicationUser);
        }
        return null;
    }

    public void deleteUser(ApplicationUser applicationUser) {
        this.userRepository.delete(applicationUser);
    }

    private boolean checkPassword(ApplicationUser applicationUser, String password) {
        return passwordEncoder.matches(password, applicationUser.getPassword());
    }

}
