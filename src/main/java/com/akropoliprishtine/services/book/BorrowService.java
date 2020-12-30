package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.entities.book.BorrowRequest;
import com.akropoliprishtine.enums.BorrowRequestStatus;
import com.akropoliprishtine.repositories.book.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BorrowRequestService borrowRequestService;

    public BorrowService(BorrowRepository borrowRepository,
                         BorrowRequestService borrowRequestService) {
        this.borrowRepository = borrowRepository;
        this.borrowRequestService = borrowRequestService;
    }

    public List<Borrow> getAll() {
        return this.borrowRepository.findAll();
    }

    @Transactional
    public Borrow borrow(Borrow borrow) {
        BorrowRequest borrowRequest = borrow.getBorrowRequest();
        borrowRequest.setBorrowRequestStatus(BorrowRequestStatus.APPROVED);

        this.borrowRequestService.saveBorrowRequest(borrowRequest);
        return this.borrowRepository.save(borrow);
    }
}
