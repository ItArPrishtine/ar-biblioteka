package com.akropoliprishtine.controllers.book;

import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.enums.BorrowStatus;
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
    public List<Borrow> getBorrows(@RequestParam(value = "status", required = false) BorrowStatus status) {
        return this.borrowService.getAll(status);
    }

    @PostMapping("/")
    public Borrow borrow(@RequestBody Borrow borrow) {
        return this.borrowService.borrow(borrow);
    }

    @PostMapping("/return")
    public Borrow returnBorrow(@RequestBody Borrow borrow) {
        return this.borrowService.returnBorrow(borrow);
    }

    @PostMapping("/user_borrow_exist")
    public Borrow borrowExist(@RequestBody Borrow borrow) {
        return this.borrowService.borrowExist(borrow);
    }
}

