package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.services.ApplicationUserService;
import com.akropoliprishtine.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account_user")
public class ApplicationUserController {
    @Autowired
    private ApplicationUserService applicationUserService;

    private JwtTokenUtil jwtTokenUtil;

    public ApplicationUserController(ApplicationUserService applicationUserService,
                                     JwtTokenUtil jwtTokenUtil) {
        applicationUserService = applicationUserService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/")
    public ApplicationUser createUser(@RequestBody JsonNode applicationUser) {
        return this.applicationUserService.createUser(applicationUser);
    }
    
    @PostMapping("/change_password")
    public ApplicationUser changePassword(@RequestBody JsonNode applicationUser) throws Exception {
        return this.applicationUserService.changePassword(applicationUser);
    }

    @GetMapping("/")
    public List<ApplicationUser> getUsers() {
        return this.applicationUserService.getUsers();
    }

    @PutMapping("/")
    public ApplicationUser updateUser(@RequestBody ApplicationUser applicationUser) {
        return this.applicationUserService.updateUser(applicationUser);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        this.applicationUserService.deleteUser(id);
    }

}
