package com.akropoliprishtine.repositories.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.book.BorrowRequest;
import com.akropoliprishtine.enums.BorrowRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Long> {
    BorrowRequest findBorrowRequestByApplicationUser(ApplicationUser user);

    @Modifying
    @Transactional
    @Query( value = "delete from book_borrow_request br where br.user_id = ?1 and br.borrowRequestStatus = ?2", nativeQuery = true)
    void deleteAllByApplicationUserAndBorrowRequestStatus(Long userId, BorrowRequestStatus borrowRequestStatus);
}