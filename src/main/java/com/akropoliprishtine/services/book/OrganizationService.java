package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository; 
    }

    public Organization getOrganization(String name) {
        return this.organizationRepository.findByName(name);
    }
    
    public Organization getOrganizationById(Long id) {
        return this.organizationRepository.findById(id).get();
    }
    
    public List<Organization> getOrganizations() {
        return this.organizationRepository.findAll();
    }
}
