package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Role;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    GeneralUtils generalUtils;

    public BorrowService(BorrowRepository borrowRepository,
                         ApplicationUserService userService,
                         JwtUserDetailsService jwtUserDetailsService,
                         RoleRepository roleRepository,
                         UserRepository userRepository) {
        this.borrowRepository = borrowRepository;
        this.userService = userService;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
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

    public Borrow borrowExist(Borrow borrow) {
        List<Borrow> borrowList = this.borrowRepository.findBorrowByBookAndBorrowStatus(borrow.getBook(), BorrowStatus.BORROWED);
        if (borrowList.size() != 0) {
            return borrowList.get(0);
        } else {
            return null;
        }
    }

    public Borrow checkIfUserHasAnyBorrow() {
        ApplicationUser user = jwtUserDetailsService.getUserFromToken();

        List<Borrow> borrowList = this.borrowRepository.findBorrowByApplicationUserAndBorrowStatus(user, BorrowStatus.BORROWED);
        if (borrowList.size() != 0) {
            return borrowList.get(0);
        } else {
            return null;
        }
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

            if (daysLeft == 2 || daysLeft == 0) {
                this.emailService.sendEmailForBorrowDeadline(item, daysLeft);
            }
        });
    }

    public void extendDeadline(Long borrowId) {
        Borrow borrow = borrowRepository.getOne(borrowId);

        if (borrow.getExtendedDeadline()) {
            try {
                throw new Exception("Deadline cannot be extended two times !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Date addedDate = GeneralUtils.addDaysToDate(borrow.getBorrowUntil(), 7);

        borrow.setExtendedDeadline(true);
        borrow.setBorrowUntil(addedDate);

        borrowRepository.save(borrow);
    }
}
