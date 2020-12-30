import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import {BorrowRequestModel} from "../../models/book/borrow-request.model";
import {BorrowModel} from "../../models/book/borrow.model";

@Injectable()
export class BorrowService {

  constructor(private http: HttpClient) { }

  borrow(borrow: BorrowModel) {
    return this.http.post(RequestUrls.BOOK.BORROW.BORROW, borrow);
  }

  reject(borrow: BorrowRequestModel) {
    return this.http.post(RequestUrls.BOOK.BORROW.REJECT, borrow);
  }

  getBorrowRequests() {
    return this.http.get<BorrowRequestModel[]>(RequestUrls.BOOK.BORROW.REQUEST);
  }
}
