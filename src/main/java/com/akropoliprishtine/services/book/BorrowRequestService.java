package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.book.BorrowRequest;
import com.akropoliprishtine.enums.BorrowRequestStatus;
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

    public List<BorrowRequest> getAllRequests() {
        return this.borrowRequestRepository.findAll();
    }

    public BorrowRequest requestForBorrow(BorrowRequest borrowRequest) {
        borrowRequest.setBorrowRequestStatus(BorrowRequestStatus.REQUESTED);
        return this.saveBorrowRequest(borrowRequest);
    }

    public BorrowRequest saveBorrowRequest(BorrowRequest borrowRequest) {
        return this.borrowRequestRepository.save(borrowRequest);
    }

    public BorrowRequest rejectBorrow(BorrowRequest borrowRequest) {
        borrowRequest.setBorrowRequestStatus(BorrowRequestStatus.REJECTED);
        return this.borrowRequestRepository.save(borrowRequest);
    }

    public BorrowRequest requestForBorrowAndCancelOthers(BorrowRequest borrowRequest) {
        this.cancelRequest(borrowRequest);
        return this.borrowRequestRepository.save(borrowRequest);
    }

    public void cancelRequest (BorrowRequest borrowRequest) {
        ApplicationUser user = borrowRequest.getApplicationUser();
        this.borrowRequestRepository.deleteAllByApplicationUserAndBorrowRequestStatus(user.getId(), BorrowRequestStatus.REQUESTED);
    }

    public BorrowRequest findByUserId(Long userId) {
        ApplicationUser user = new ApplicationUser();
        user.setId(userId);
        return this.borrowRequestRepository.findBorrowRequestByApplicationUser(user);
    }
}
