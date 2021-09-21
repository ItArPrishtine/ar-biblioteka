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

    public ApplicationUserController(ApplicationUserService applicationUserService,
                                     JwtTokenUtil jwtTokenUtil) {
        this.applicationUserService = applicationUserService;
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

    @GetMapping("/{id}")
    public Optional<ApplicationUser> getUserById(@PathVariable Long id) {
        return this.applicationUserService.getUserById(id);
    }

    @PutMapping("/")
    public ApplicationUser updateUser(@RequestBody ApplicationUser applicationUser) {
        return this.applicationUserService.updateUser(applicationUser);
    }

    @PutMapping("/user/e-sign")
    public Optional<ApplicationUser> eSign(@RequestParam(value = "file", required = true) MultipartFile file,
                                           @RequestParam Long id) {
        return this.applicationUserService.uploadESign(id, file);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        this.applicationUserService.deleteUser(id);
    }

}
