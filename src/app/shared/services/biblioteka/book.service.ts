import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import {ResponsePageModel} from '../../models/shared/ResponsePage.model';
import {BookModel} from '../../models/book/book.model';
import {BookBorrowDTO} from '../../models/dto/BookBorrowDTO.model';

@Injectable()
export class BookService {

  constructor(private http: HttpClient) { }

  getBooks(pageNumber?: number, pageSize?: number, bookName?: string, authorId?: string, categoryId?: string) {
    if (!bookName) {
      bookName = '';
    }

    if (!authorId || authorId === 'autori') {
      authorId = '0';
    }

    if (!categoryId || categoryId === 'tegjitha') {
      categoryId = '0';
    }
    return this.http.get<BookBorrowDTO[]>(`${RequestUrls.BOOK.BOOK.BASE}?pageNumber=${pageNumber}&pageSize=${pageSize}&bookName=${bookName}&authorId=${authorId}&category=${categoryId}`);
  }

  getBookById(id: string) {
    return this.http.get<BookModel>(RequestUrls.BOOK.BOOK.BASE + `${id}`);
  }

  getEditions() {
    return this.http.get<any>(RequestUrls.BOOK.EDITION.BASE);
  }

  createBook(book: any) {
    return this.http.post<BookModel>(RequestUrls.BOOK.BOOK.BASE, book);
  }

  deleteBook(bookId: string) {
    return this.http.delete(RequestUrls.BOOK.BOOK.BASE + `${bookId}`);
  }

  updateBook(book: any) {
    return this.http.put<BookModel>(RequestUrls.BOOK.BOOK.BASE, book);
  }
}
