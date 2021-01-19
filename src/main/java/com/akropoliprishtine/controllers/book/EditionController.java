package com.akropoliprishtine.controllers.book;

import com.akropoliprishtine.entities.book.Edition;
import com.akropoliprishtine.services.book.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book_edition")
public class EditionController {

    @Autowired
    private EditionService editionService;

    @GetMapping()
    public List<Edition> getEditions() {
        return this.editionService.getEditions();
    }

}

