package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.book.BorrowRequest;
import com.akropoliprishtine.enums.BorrowRequestStatus;
import com.akropoliprishtine.services.book.BorrowRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book_borrow_request")
public class BorrowRequestController {

    @Autowired
    private BorrowRequestService borrowRequestService;

    @GetMapping("/")
    public List<BorrowRequest> getBorrowRequests() {
        return this.borrowRequestService.getAllRequests();
    }

    @PostMapping("/")
    public BorrowRequest requestForBorrow(@RequestBody BorrowRequest borrowRequest) {
        return this.borrowRequestService.requestForBorrow(borrowRequest);
    }

    @PostMapping("/reject")
    public BorrowRequest rejectRequest(@RequestBody BorrowRequest borrowRequest) {
        return this.borrowRequestService.rejectBorrow(borrowRequest);
    }

    @GetMapping("/{userId}")
    public BorrowRequest findByUserId(@PathVariable Long userId) {
        return this.borrowRequestService.findByUserId(userId);
    }

    @PostMapping("/cancel/new_request")
    public BorrowRequest cancelAndRequestNew(@RequestBody BorrowRequest borrowRequest) {
        borrowRequest.setBorrowRequestStatus(BorrowRequestStatus.REQUESTED);
        return this.borrowRequestService.requestForBorrowAndCancelOthers(borrowRequest);
    }

    @PostMapping("/cancel")
    public void cancelRequest(@RequestBody BorrowRequest borrowRequest) {
        this.borrowRequestService.cancelRequest(borrowRequest);
    }
}

