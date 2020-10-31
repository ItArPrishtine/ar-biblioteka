package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Edition;
import com.akropoliprishtine.repositories.book.AuthorRepository;
import com.akropoliprishtine.repositories.book.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionService {

    @Autowired
    EditionRepository editionRepository;

    public EditionService(EditionRepository editionRepository) {
        this.editionRepository = editionRepository;
    }
    
    public List<Edition> saveEditions(List<Edition> editions) {
        return this.editionRepository.saveAll(editions);
    }

    public Edition saveEdition(Edition edition) {
        return this.editionRepository.save(edition);
    }

    public List<Edition> getEditions() {
        return this.editionRepository.findAll();
    }
}
