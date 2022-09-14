package com.akropoliprishtine.repositories;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);

    ApplicationUser findByEmail(String email);

    @Override
    List<ApplicationUser> findAll();

    List<ApplicationUser> findAllByRole(Role role);
    
    List<ApplicationUser> findAllByOrganization(Organization organization);

    List<ApplicationUser> findAllByAllowProfessionalEmail(boolean allowedProffesionalEmail);

    List<ApplicationUser> findAllByRoleAndOrganization(Role role, Organization organization);
}
