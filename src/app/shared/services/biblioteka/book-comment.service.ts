import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import {ResponsePageModel} from '../../models/shared/ResponsePage.model';
import {BookModel} from '../../models/book/book.model';
import {BookCommentModel} from "../../models/book/book-comment.model";

@Injectable()
export class BookCommentService {

  constructor(private http: HttpClient) { }

  addComment(comment: BookCommentModel) {
    return this.http.post<BookCommentModel>(RequestUrls.BOOK.COMMENT.BASE, comment);
  }

  updateComment(comment: BookCommentModel) {
    return this.http.put<BookCommentModel>(RequestUrls.BOOK.COMMENT.BASE, comment);
  }

  getByBookId(bookId: any) {
    return this.http.get<BookCommentModel[]>(RequestUrls.BOOK.COMMENT.BY_BOOK + `/${bookId}`);
  }

  deleteComment(bookId: number) {
    return this.http.delete(RequestUrls.BOOK.COMMENT.BASE + `${bookId}`);
  }
}
