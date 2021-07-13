package com.akropoliprishtine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBorrowDTO {
    private String id;
    private String name;
    private String category;
    private String publicationYear;
    private String authorFirstName;
    private String authorLastName;
    private String authorId;
    private String borrowStatus;
}
