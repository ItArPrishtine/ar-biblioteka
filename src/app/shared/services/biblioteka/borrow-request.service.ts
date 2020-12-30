import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import {ResponsePageModel} from '../../models/shared/ResponsePage.model';
import {BookModel} from '../../models/book/book.model';
import {AuthorModel} from "../../models/book/author.model";
import {BorrowRequestModel} from "../../models/book/borrow-request.model";
import {Observable} from "rxjs";

@Injectable()
export class BorrowRequestService {

  constructor(private http: HttpClient) { }

  borrowRequest(borrowRequest: BorrowRequestModel) {
    return this.http.post(RequestUrls.BOOK.BORROW.REQUEST, borrowRequest);
  }

  getBorrowRequests() {
    return this.http.get<BorrowRequestModel[]>(RequestUrls.BOOK.BORROW.REQUEST);
  }

  borrowExist(userId) {
    return this.http.get<BorrowRequestModel>(RequestUrls.BOOK.BORROW.REQUEST + `${userId}`);
  }

  cancelRequest(borrowRequest: BorrowRequestModel) {
    return this.http.post(RequestUrls.BOOK.BORROW.CANCEL, borrowRequest);
  }

  cancelAndRequestNew(borrowRequest: BorrowRequestModel) {
    return this.http.post(RequestUrls.BOOK.BORROW.CANCEL_NEW_REQUEST, borrowRequest);
  }
}
