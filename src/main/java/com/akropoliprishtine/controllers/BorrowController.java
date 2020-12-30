package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.services.book.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book_borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/")
    public List<Borrow> getBorrows() {
        return this.borrowService.getAll();
    }

    @PostMapping("/")
    public Borrow borrow(@RequestBody Borrow borrow) {
        return this.borrowService.borrow(borrow);
    }
}

