package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.services.ApplicationUserService;
import com.akropoliprishtine.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account_user")
public class ApplicationUserController {
    @Autowired
    private ApplicationUserService applicationUserService;

    public ApplicationUserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/create")
    public ApplicationUser createUser(@RequestBody JsonNode applicationUser) {
        return this.applicationUserService.createUser(applicationUser);
    }

    @PostMapping("/change_password")
    public ApplicationUser changePassword(@RequestBody JsonNode applicationUser) throws Exception {
        return this.applicationUserService.changePassword(applicationUser);
    }

    @GetMapping("/read")
    public List<ApplicationUser> getUsers(@RequestParam(defaultValue = "0", required = false) int organization) {
        return this.applicationUserService.getUsers(organization);
    }

    @GetMapping("/read/{id}")
    public Optional<ApplicationUser> getUserById(@PathVariable Long id) {
        return this.applicationUserService.getUserById(id);
    }

    @GetMapping("/p1/me")
    public Optional<ApplicationUser> getPersonalData() {
        return this.applicationUserService.getPersonalData();
    }

    @PutMapping("/update")
    public ApplicationUser updateUser(@RequestBody ApplicationUser applicationUser) {
        return this.applicationUserService.updateUser(applicationUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        this.applicationUserService.deleteUser(id);
    }

}
