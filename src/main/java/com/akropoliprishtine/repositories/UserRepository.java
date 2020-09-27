package com.akropoliprishtine.repositories;

import com.akropoliprishtine.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    public ApplicationUser findByUsernameAndPassword(String username, String password);

    ApplicationUser findByUsername(String username);
}