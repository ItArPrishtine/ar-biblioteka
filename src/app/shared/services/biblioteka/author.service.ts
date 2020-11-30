import { Injectable } from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import {AuthorModel} from '../../models/book/author.model';
import {ResponsePageModel} from '../../models/shared/ResponsePage.model';

@Injectable()
export class AuthorService {

  constructor(private http: HttpClient) { }

  createAuthor(author: any) {
    return this.http.post(RequestUrls.BOOK.AUTHOR.CREATE, author);
  }

  deleteAuthor(authorId: string) {
    return this.http.delete(RequestUrls.BOOK.AUTHOR.DELETE + `/${authorId}`);
  }

  updateAuthor(author: any) {
    return this.http.put(RequestUrls.BOOK.AUTHOR.UPDATE, author);
  }

  getAuthors(pageNumber?: number, pageSize?: number) {
    return this.http.get<ResponsePageModel<AuthorModel>>(`${RequestUrls.BOOK.AUTHOR.GET_LIST}?pageNumber=${pageNumber}&pageSize=${pageSize}`);
  }

  getAuthorById(id: string) {
    return this.http.get<AuthorModel>(RequestUrls.BOOK.AUTHOR.DETAILS + `/${id}`);
  }

  getAuthorBooks(id: string) {
    return this.http.get<any>(RequestUrls.BOOK.BOOK.BY_AUTHOR + `/${id}`);
  }
}
