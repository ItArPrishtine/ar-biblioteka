package com.akropoliprishtine.repositories.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.enums.BorrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findBorrowByBookAndBorrowStatus(Book book, BorrowStatus status);

    List<Borrow> findBorrowByApplicationUserAndBorrowStatus(ApplicationUser user, BorrowStatus status);
}