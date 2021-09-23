//package com.akropoliprishtine.config;
//
//import com.akropoliprishtine.entities.book.*;
//import com.akropoliprishtine.services.book.AuthorService;
//import com.akropoliprishtine.services.book.BookService;
//import com.akropoliprishtine.services.book.EditionService;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.formula.functions.T;
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import javax.transaction.Transactional;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Configuration
//public class ExcelFileToDatabaseConfig {
//
//    @Autowired
//    BookService bookService;
//
//    @Autowired
//    EditionService editionService;
//
//    @Autowired
//    AuthorService authorService;
//
//    public ExcelFileToDatabaseConfig(BookService bookService,
//                                     EditionService editionService,
//                                     AuthorService authorService)  {
//        this.bookService = bookService;
//        this.editionService = editionService;
//        this.authorService = authorService;
//    }
//
//    @Bean
//    @Transactional
//    public void readData() throws IOException, InvalidFormatException {
//        List<Book> existedBooks = bookService.getBooks();
//        List<Author> existedAuthors = authorService.getAuthors();
//        List<Edition> existedEditions = editionService.getEditions();
//        List<Book> booksToAdd = new ArrayList<>();
//
//        File excelFile = new ClassPathResource("regjistri.xlsx").getFile();
//        Workbook workbook = WorkbookFactory.create(excelFile);
//
//        // Getting the Sheet at index zero
//        Sheet sheet = workbook.getSheetAt(0);
//
//        Iterator<Row> rowIterator = sheet.rowIterator();
//        List<RegjistriDTO> registerList = new ArrayList<>();
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//
//            RegjistriDTO regjistriDTO = new RegjistriDTO();
//
//            regjistriDTO.setTitulli(row.getCell(1) != null ? row.getCell(1).toString() : "");
//            regjistriDTO.setEmriAutorit(row.getCell(2) != null ? row.getCell(2).toString() : "");
//            regjistriDTO.setKategoria(row.getCell(3) != null ? row.getCell(3).toString() : "");
//            regjistriDTO.setShtepiaBotuese(row.getCell(4) != null ? row.getCell(4).toString() : "");
//            regjistriDTO.setVitiProdhimit(row.getCell(5) != null ? row.getCell(5).toString() : "");
//            registerList.add(regjistriDTO);
//        }
//
//        for (RegjistriDTO regjistriDTO : registerList) {
//
//            Author author = new Author();
//            author.setFirstName(regjistriDTO.getEmriAutorit());
//            author.setLastName("");
//            author = saveAuthor(author, existedAuthors);
//
//            Edition edition = new Edition();
//            edition.setName(regjistriDTO.getShtepiaBotuese());
//            edition = saveEdition(edition, existedEditions);
//
//            Book book = new Book();
//            book.setName(regjistriDTO.getTitulli());
//            book.setPublicationYear(regjistriDTO.getVitiProdhimit());
//            book.setCategory(regjistriDTO.getKategoria());
//            book.setAuthor(author);
//            book.setEdition(edition);
//
//            addBooksIfNotExist(booksToAdd, book, existedBooks);
//        }
//
//        System.out.println("AGON");
//        System.out.println(booksToAdd);
//
//        bookService.saveBooks(booksToAdd);
//    }
//
//
//    private Author saveAuthor(Author author, List<Author> existedAuthors) {
//        Author existedAuthor = authorExists(existedAuthors, author);
//
//        if (existedAuthor != null) {
//            return existedAuthor;
//        } else {
//            Author savedAuthor = this.authorService.saveAuthor(author);
//            existedAuthors.add(author);
//            return savedAuthor;
//        }
//    }
//
//    private Edition saveEdition(Edition edition, List<Edition> existedEditions) {
//        Edition existedEdition = editionExists(existedEditions, edition);
//
//        if (existedEdition != null) {
//            return existedEdition;
//        } else {
//            Edition savedEdition = this.editionService.saveEdition(edition);
//            existedEditions.add(edition);
//            return savedEdition;
//        }
//    }
//
//
//    public Edition editionExists(List<Edition> list, Edition edition){
//        return list.stream().filter(item -> item.getName().equalsIgnoreCase(edition.getName())).findAny().orElse(null);
//    }
//
//    public Author authorExists(List<Author> list, Author author){
//        return list.stream().filter(item ->
//                item.getFirstName().equalsIgnoreCase(author.getFirstName())
//                && item.getLastName().equalsIgnoreCase(author.getLastName())
//        ).findAny().orElse(null);
//    }
//
//    public void addBooksIfNotExist(List<Book> booksToAdd, Book book, List<Book> existedBooks){
//        Book existedBook = existedBooks.stream().filter(item ->
//                item.getName().equalsIgnoreCase(book.getName())
//                        && item.getCategory().equalsIgnoreCase(book.getCategory())
//        ).findAny().orElse(null);
//
//        if (existedBook == null) {
//            booksToAdd.add(book);
//        }
//    }
//
//}
