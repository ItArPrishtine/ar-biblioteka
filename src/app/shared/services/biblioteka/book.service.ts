import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import {ResponsePageModel} from '../../models/shared/ResponsePage.model';
import {BookModel} from '../../models/book/book.model';
import {AuthorModel} from "../../models/book/author.model";

@Injectable()
export class BookService {

  constructor(private http: HttpClient) { }

  getBooks(pageNumber?: number, pageSize?: number) {
    return this.http.get<ResponsePageModel<BookModel>>(`${RequestUrls.BOOK.BOOK.GET_LIST}?pageNumber=${pageNumber}&pageSize=${pageSize}`);
  }

  getBookById(id: string) {
    return this.http.get<BookModel>(RequestUrls.BOOK.BOOK.DETAILS + `/${id}`);
  }
}
