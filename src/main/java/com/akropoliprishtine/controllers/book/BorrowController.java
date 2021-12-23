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

    @GetMapping("/read")
    public List<Borrow> getBorrows(@RequestParam(value = "status", required = false) BorrowStatus status) {
        return this.borrowService.getAll(status);
    }

    @PostMapping("/borrow")
    public Borrow borrow(@RequestBody Borrow borrow) {
        return this.borrowService.borrow(borrow);
    }

    @PostMapping("/return")
    public Borrow returnBorrow(@RequestBody Borrow borrow) {
        return this.borrowService.returnBorrow(borrow);
    }

    @GetMapping("/borrowed/{bookId}")
    public Borrow checkIfBookBorrowed(@PathVariable Long bookId) {
        return this.borrowService.bookBorrowed(bookId);
    }

    @GetMapping("/user_current_borrows/{userId}")
    public Borrow getCurrentUserBorrow(@PathVariable Long userId) {
        return this.borrowService.getCurrentUserBorrow(userId);
    }

    @PostMapping("/extend-deadline")
    public Borrow extendDeadline(@RequestBody String borrowId) {
        return this.borrowService.extendDeadline(Long.parseLong(borrowId));
    }
}

