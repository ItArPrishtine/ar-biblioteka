package com.akropoliprishtine.services;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.repositories.UserRepository;
import com.akropoliprishtine.utils.GeneralConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static java.util.Collections.emptyList;

@Service
public class ApplicationUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailServiceImpl emailService;

    ObjectMapper objectMapper;

    public ApplicationUserService(UserRepository userRepository,
                                  ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }
    
    
    public ApplicationUser loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(username);
        if (applicationUser == null || !checkPassword(applicationUser, password)) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser;
    }

    public ApplicationUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<ApplicationUser> getUsers() {
        return this.userRepository.findAll();
    }

    public ApplicationUser createUser(JsonNode jsonNode) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setFirstName(jsonNode.get("firstName").textValue());
        applicationUser.setLastName(jsonNode.get("lastName").textValue());
        applicationUser.setEmail(jsonNode.get("email").textValue());
        applicationUser.setDateOfBirth(jsonNode.get("dateOfBirth").textValue());
        applicationUser.setDescription(jsonNode.get("description").textValue());
        applicationUser.setRole(objectMapper.convertValue(jsonNode.get("role"), Role.class));
        String username = applicationUser.getFirstName() + applicationUser.getLastName();
        applicationUser.setUsername(username.toLowerCase());
        applicationUser.setPassword(GeneralConstants.DEFAULT_USER_PASSWORD);

        return this.userRepository.save(applicationUser);
// TODO        this.emailService.accountCreated(user.getEmail());
    }

    public ApplicationUser changePassword (JsonNode jsonNode) throws Exception {
        ApplicationUser user = this.userRepository.getOne(Long.parseLong(jsonNode.get("id").toString()));

        if (checkPassword(user, jsonNode.get("oldPassword").textValue())) {
            String encodedPassword = passwordEncoder.encode(jsonNode.get("newPassword").textValue());
            user.setPassword(encodedPassword);
            return this.userRepository.save(user);
        } {
            throw new Exception("The old password is not the same");
        }
    }

    public ApplicationUser updateUser(ApplicationUser applicationUser) {
        if (applicationUser != null && applicationUser.getId() != null) {
            return this.userRepository.save(applicationUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    private boolean checkPassword(ApplicationUser applicationUser, String password) {
        return passwordEncoder.matches(password, applicationUser.getPassword());
    }

}
