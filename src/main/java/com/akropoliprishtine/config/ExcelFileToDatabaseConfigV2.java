package com.akropoliprishtine.config;

import com.akropoliprishtine.entities.book.*;
import com.akropoliprishtine.services.book.AuthorService;
import com.akropoliprishtine.services.book.BookService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class ExcelFileToDatabaseConfigV2 {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;


    public ExcelFileToDatabaseConfigV2(BookService bookService,
                                     AuthorService authorService)  {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Bean
    @Transactional
    public void readData() throws IOException, InvalidFormatException {
        List<Book> existedBooks = bookService.getBooks();
        List<Author> existedAuthors = authorService.getAuthors();
        List<Book> booksToAdd = new ArrayList<>();

        File excelFile = new ClassPathResource("regjistri-new.xlsx").getFile();
        Workbook workbook = WorkbookFactory.create(excelFile);

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        List<RegjistriDTO> registerList = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            RegjistriDTO regjistriDTO = new RegjistriDTO();

            regjistriDTO.setTitulli(row.getCell(1) != null ? row.getCell(1).toString() : "");
            regjistriDTO.setEmriAutorit(row.getCell(2) != null ? row.getCell(2).toString() : "");
            regjistriDTO.setKategoria(row.getCell(3) != null ? row.getCell(3).toString() : "");
            regjistriDTO.setShtepiaBotuese(row.getCell(4) != null ? row.getCell(4).toString() : "");
            regjistriDTO.setVitiProdhimit(row.getCell(5) != null ? row.getCell(5).toString() : "");
            registerList.add(regjistriDTO);
        }

        for (RegjistriDTO regjistriDTO : registerList) {

            Author author = new Author();
            author.setName(regjistriDTO.getEmriAutorit());
            author = saveAuthor(author, existedAuthors);

            Book book = new Book();
            book.setTitle(regjistriDTO.getTitulli());
            book.setPublicationYear(regjistriDTO.getVitiProdhimit());
            book.setCategory(regjistriDTO.getKategoria());
            book.setPublishingHouse(regjistriDTO.getShtepiaBotuese());
            book.setAuthor(author.getId().intValue());

            addBooksIfNotExist(booksToAdd, book, existedBooks);
        }

        System.out.println("AGON");
        System.out.println(booksToAdd);

        bookService.saveBooks(booksToAdd);
    }


    private Author saveAuthor(Author author, List<Author> existedAuthors) {
        Author existedAuthor = authorExists(existedAuthors, author);

        if (existedAuthor != null) {
            return existedAuthor;
        } else {
            Author savedAuthor = this.authorService.saveAuthorFromSheet(author);
            existedAuthors.add(author);
            return savedAuthor;
        }
    }

    public Author authorExists(List<Author> list, Author author){
        return list.stream().filter(item ->
                item.getName().equalsIgnoreCase(author.getName())
                && item.getSurname().equalsIgnoreCase(author.getSurname())
        ).findAny().orElse(null);
    }

    public void addBooksIfNotExist(List<Book> booksToAdd, Book book, List<Book> existedBooks){
        Book existedBook = existedBooks.stream().filter(item ->
                item.getTitle().equalsIgnoreCase(book.getTitle())
                        && item.getCategory().equalsIgnoreCase(book.getCategory())
        ).findAny().orElse(null);

        if (existedBook == null) {
            booksToAdd.add(book);
        }
    }

}
