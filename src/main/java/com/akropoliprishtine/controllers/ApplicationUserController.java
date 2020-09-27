package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account_user")
public class ApplicationUserController {
    @Autowired
    private ApplicationUserService applicationUserService;

    @PostMapping("create")
    public ApplicationUser createUser(@RequestBody ApplicationUser applicationUser) {
        return this.applicationUserService.createUser(applicationUser);
    }

    @GetMapping("all")
    public List<ApplicationUser> getUsers() {
        return this.applicationUserService.getUsers();
    }

    @PutMapping
    public ApplicationUser updateUser(@RequestBody ApplicationUser applicationUser) {
        return this.applicationUserService.updateUser(applicationUser);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody ApplicationUser applicationUser) {
        this.applicationUserService.deleteUser(applicationUser);
    }
}
