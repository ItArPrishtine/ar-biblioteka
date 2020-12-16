package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.entities.book.BorrowRequest;
import com.akropoliprishtine.enums.BorrowRequestStatus;
import com.akropoliprishtine.repositories.book.BorrowRepository;
import com.akropoliprishtine.repositories.book.BorrowRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRequestService {

    @Autowired
    BorrowRequestRepository borrowRequestRepository;

    public BorrowRequestService(BorrowRequestRepository borrowRequestRepository) {
        this.borrowRequestRepository = borrowRequestRepository;
    }
    
    public BorrowRequest requestForBorrow(BorrowRequest borrowRequest) {
        return this.borrowRequestRepository.save(borrowRequest);
    }
}
