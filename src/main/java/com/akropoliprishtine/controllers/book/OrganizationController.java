package com.akropoliprishtine.controllers.book;

import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.services.book.AuthorService;
import com.akropoliprishtine.services.book.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/read")
    public List<Organization> getOrganizations() {
        return this.organizationService.getOrganizations();
    }
}
