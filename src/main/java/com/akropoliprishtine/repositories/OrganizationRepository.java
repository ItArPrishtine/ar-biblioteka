package com.akropoliprishtine.repositories;

import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByName(String name);
}
