package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.entities.book.BorrowRequest;
import com.akropoliprishtine.enums.BorrowRequestStatus;
import com.akropoliprishtine.services.book.BookService;
import com.akropoliprishtine.services.book.BorrowRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/book_borrow_request")
public class BorrowRequestController {

    @Autowired
    private BorrowRequestService borrowRequestService;

    @PostMapping("/")
    public BorrowRequest requestForBorrow(@RequestBody BorrowRequest borrowRequest) {
        borrowRequest.setBorrowRequestStatus(BorrowRequestStatus.REQUESTED);
        return this.borrowRequestService.requestForBorrow(borrowRequest);
    }
}

