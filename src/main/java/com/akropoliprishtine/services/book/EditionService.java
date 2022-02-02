package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Edition;
import com.akropoliprishtine.repositories.book.EditionRepository;
import com.akropoliprishtine.services.ApplicationUserService;
import com.amazonaws.services.appstream.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionService {

    @Autowired
    EditionRepository editionRepository;
    
    @Autowired
    ApplicationUserService userService;

    public EditionService(EditionRepository editionRepository,
                          ApplicationUserService userService) {
        this.editionRepository = editionRepository;
        this.userService = userService;
    }
    
    public List<Edition> getEditions() {
        return this.editionRepository.findAll();
    }

    public Edition saveEdition(Edition edition) {
        return this.editionRepository.save(edition);
    }
}
