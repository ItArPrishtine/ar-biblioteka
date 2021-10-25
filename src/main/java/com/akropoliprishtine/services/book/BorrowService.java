package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.enums.BorrowStatus;
import com.akropoliprishtine.repositories.RoleRepository;
import com.akropoliprishtine.repositories.UserRepository;
import com.akropoliprishtine.repositories.book.BorrowRepository;
import com.akropoliprishtine.services.ApplicationUserService;
import com.akropoliprishtine.services.EmailService;
import com.akropoliprishtine.services.JwtUserDetailsService;
import com.akropoliprishtine.services.RoleService;
import com.akropoliprishtine.utils.GeneralConstants;
import com.akropoliprishtine.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    ApplicationUserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    BookService bookService;

    public BorrowService(BorrowRepository borrowRepository,
                         ApplicationUserService userService,
                         JwtUserDetailsService jwtUserDetailsService,
                         RoleRepository roleRepository,
                         UserRepository userRepository,
                         BookService bookService) {
        this.borrowRepository = borrowRepository;
        this.userService = userService;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    public List<Borrow> getAll(BorrowStatus status) {
        if (status != null) {
            return this.borrowRepository.findBorrowByBorrowStatus(status);
        }
        return this.borrowRepository.findAll();
    }

    public Borrow borrow(Borrow borrow) {
        ApplicationUser borrowUser = jwtUserDetailsService.getUserFromToken();

        borrow.setApplicationUser(borrowUser);
        borrow.setBorrowStatus(BorrowStatus.BORROWED);

        Date currentDate = new Date();
        borrow.setBorrowFrom(currentDate);
        long dateUntil = currentDate.getTime() + 14 * 24 * 3600 * 1000;
        borrow.setBorrowUntil(new Date(dateUntil));

        Borrow borrowed = borrowRepository.save(borrow);

        Role pg = roleRepository.findByName("PG tek Biblioteka");
        Role helper = roleRepository.findByName("ND tek Biblioteka");

        List<ApplicationUser> pgUsers = userRepository.findAllByRole(pg);
        List<ApplicationUser> helperUsers = userRepository.findAllByRole(helper);

        pgUsers.forEach(user -> {
            emailService.sendBorrowEmailToLibrary(user.getEmail(), borrowed);
        });

        helperUsers.forEach(user -> {
            emailService.sendBorrowEmailToLibrary(user.getEmail(), borrowed);
        });

        emailService.sendBorrowEmailToClient(borrowed);

        return borrowed;
    }

    public Borrow returnBorrow(Borrow borrow) {
        ApplicationUser user = jwtUserDetailsService.getUserFromToken();

        borrow.setApplicationUser(user);

        borrow.setBorrowStatus(BorrowStatus.RETURNED);
        borrow.setReturnedDate(new Date());
        return borrowRepository.save(borrow);
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "schedule tasks using cron jobs - " + now);

        List<Borrow> currentBorrowed = borrowRepository.findBorrowByBorrowStatus(BorrowStatus.BORROWED);
        currentBorrowed.forEach(item -> {
            Date currentDate = new Date();
            Date borrowedUntil = item.getBorrowUntil();

            long milli = borrowedUntil.getTime() - currentDate.getTime();
            long daysLeft = (milli / (60*60*24*1000));

            this.emailService.sendEmailForBorrowDeadline(item, daysLeft, true);

            if (daysLeft == 2 || daysLeft == 0) {
                this.emailService.sendEmailForBorrowDeadline(item, daysLeft, false);
            }
        });
    }

    public Borrow extendDeadline(Long borrowId) {
        Optional<Borrow> borrow = borrowRepository.findById(borrowId);

        if (borrow.get().getExtendedDeadline()) {
            try {
                throw new Exception("Deadline cannot be extended two times !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ApplicationUser userExtending = jwtUserDetailsService.getUserFromToken();

        if (!Objects.equals(borrow.get().getApplicationUser().getId(), userExtending.getId())) {
            try {
                throw new Exception("Nuk mund te shtyni deadline-in e dikujt tjeter!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Date addedDate = GeneralUtils.addDaysToDate(borrow.get().getBorrowUntil(), 7);

        borrow.get().setExtendedDeadline(true);
        borrow.get().setBorrowUntil(addedDate);

        return borrowRepository.save(borrow.get());
    }


    public Borrow bookBorrowed(Long bookId) {
        Optional<Book> book = bookService.getBooksById(bookId);
        if (!book.isPresent()) {
            return null;
        }

        List<Borrow> borrowList = borrowRepository.findBorrowByBookAndBorrowStatus(book.get(), BorrowStatus.BORROWED);

        if (borrowList.size() != 0) {
            return borrowList.get(0);
        } else {
            return null;
        }
    }

    public Borrow getCurrentUserBorrow(Long userId) {
        Optional<ApplicationUser> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            return null;
        }

        List<Borrow> borrowList = borrowRepository.findBorrowByApplicationUserAndBorrowStatus(user.get(), BorrowStatus.BORROWED);

        if (borrowList.size() != 0) {
            return borrowList.get(0);
        } else {
            return null;
        }
    }
}
