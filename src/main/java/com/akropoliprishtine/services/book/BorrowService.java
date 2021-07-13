package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.enums.BorrowStatus;
import com.akropoliprishtine.repositories.book.BorrowRepository;
import com.akropoliprishtine.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    ApplicationUserService userService;

    public BorrowService(BorrowRepository borrowRepository,
                         ApplicationUserService userService) {
        this.borrowRepository = borrowRepository;
        this.userService = userService;
    }

    public List<Borrow> getAll(BorrowStatus status) {
        if (status != null) {
            return this.borrowRepository.findBorrowByBorrowStatus(status);
        }
        return this.borrowRepository.findAll();
    }

    public Borrow borrow(Borrow borrow) {
        borrow.setBorrowStatus(BorrowStatus.BORROWED);

        Date currentDate = new Date();
        borrow.setBorrowFrom(currentDate);
        long dateUntil = currentDate.getTime() + 14 * 24 * 3600 * 1000;
        borrow.setBorrowUntil(new Date(dateUntil));
        return borrowRepository.save(borrow);
    }

    public Borrow returnBorrow(Borrow borrow) {
        borrow.setBorrowStatus(BorrowStatus.RETURNED);
        borrow.setReturnedDate(new Date());
        return borrowRepository.save(borrow);
    }

    public Borrow borrowExist(Borrow borrow) {
        List<Borrow> borrowList = this.borrowRepository.findBorrowByBookAndBorrowStatus(borrow.getBook(), BorrowStatus.BORROWED);
        if (borrowList.size() != 0) {
            return borrowList.get(0);
        } else {
            return null;
        }
    }

    public Borrow getBorrowedBookOfUser(ApplicationUser user) {
        List<Borrow> borrowedBook = this.borrowRepository.findBorrowByApplicationUserAndBorrowStatus(user, BorrowStatus.BORROWED);
        if (borrowedBook.size() != 0) {
            return borrowedBook.get(0);
        } else {
            return null;
        }
    }
}
