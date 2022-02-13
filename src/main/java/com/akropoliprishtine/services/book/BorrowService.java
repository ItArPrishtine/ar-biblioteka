package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.enums.BorrowStatus;
import com.akropoliprishtine.enums.UserRolesEnum;
import com.akropoliprishtine.repositories.RoleRepository;
import com.akropoliprishtine.repositories.UserRepository;
import com.akropoliprishtine.repositories.book.BorrowRepository;
import com.akropoliprishtine.services.ApplicationUserService;
import com.akropoliprishtine.services.EmailService;
import com.akropoliprishtine.services.JwtUserDetailsService;
import com.akropoliprishtine.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
    
    @Autowired
    OrganizationService organizationService;

    public BorrowService(BorrowRepository borrowRepository,
                         ApplicationUserService userService,
                         JwtUserDetailsService jwtUserDetailsService,
                         RoleRepository roleRepository,
                         UserRepository userRepository,
                         OrganizationService organizationService,
                         BookService bookService) {
        this.borrowRepository = borrowRepository;
        this.userService = userService;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.organizationService = organizationService;
    }

    public List<Borrow> getAll(BorrowStatus status, Long userId, long organization) {
        Organization org;
        ApplicationUser loggedUser = this.userService.getLoggedUser();

        if (((loggedUser.getRole().getName().equals(UserRolesEnum.KK.label) || loggedUser.getRole().getName().equals(UserRolesEnum.ADMIN.label)) &&
                organization != 0)
        ) {
            org = organizationService.getOrganizationById(organization);
        } else {
            org = loggedUser.getOrganization();
        }


        if (status != null && userId == null) {
            return this.borrowRepository.findBorrowByBorrowStatusAndOrganization(status, org);
        }
        
        if (userId != null && status == null) {
            Optional<ApplicationUser> borrowUser = this.userService.getUserById(userId.longValue());
            return this.borrowRepository.findByApplicationUser(borrowUser.get());
        }
        
        if (userId != null) {
            Optional<ApplicationUser> borrowUser = this.userService.getUserById(userId.longValue());
            return this.borrowRepository.findBorrowByApplicationUserAndBorrowStatus(borrowUser.get(), status);
        }
        return this.borrowRepository.findAllByOrganization(org);
    }

    public Borrow borrow(Borrow borrow) {
        ApplicationUser borrowUser = jwtUserDetailsService.getUserFromToken();
        borrow.setOrganization(borrowUser.getOrganization());

        borrow.setApplicationUser(borrowUser);
        borrow.setBorrowStatus(BorrowStatus.BORROWED);

        Date currentDate = new Date();
        borrow.setBorrowFrom(currentDate);
        long dateUntil = currentDate.getTime() + 14 * 24 * 3600 * 1000;
        borrow.setBorrowUntil(new Date(dateUntil));

        if (checkIfBorrowExist(borrow)) {
            return null;
        }

        Borrow borrowed = borrowRepository.save(borrow);

        Role pg = roleRepository.findByName(UserRolesEnum.PG_BIBLIOTEKA.label);
        Role helper = roleRepository.findByName(UserRolesEnum.ND_BIBLIOTEKA.label);

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

    private boolean checkIfBorrowExist(Borrow borrow) {
        List<Borrow> borrows = this.getAll(null, null, 0);

        List<Boolean> foundBorrows = borrows.stream().map(item -> item.getBorrowStatus() == BorrowStatus.BORROWED
                        && Objects.equals(item.getBook().getId(), borrow.getBook().getId()))
                .collect(Collectors.toList());

        if (foundBorrows.contains(true)) {
            return true;
        }

        return false;
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
