package com.akropoliprishtine.dto;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.book.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BorrowsEmailDTO {
    private ApplicationUser applicationUser;
    private Book book;
    private Date borrowFrom;
    private Date borrowUntil;
    private long daysLeft;
}
