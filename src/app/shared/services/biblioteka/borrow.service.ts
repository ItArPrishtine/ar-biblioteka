import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import {BorrowModel} from "../../models/book/borrow.model";
import {Observable} from "rxjs";

@Injectable()
export class BorrowService {

  constructor(private http: HttpClient) { }

  borrow(borrow: BorrowModel) {
    return this.http.post<BorrowModel>(RequestUrls.BOOK.BORROW.BORROW, borrow);
  }

  borrowReturn(borrow: BorrowModel) {
    return this.http.post<BorrowModel>(RequestUrls.BOOK.BORROW.RETURN, borrow);
  }

  getBorrows() {
    return this.http.get<BorrowModel[]>(RequestUrls.BOOK.BORROW.BORROW);
  }

  userBorrowExist(borrow: BorrowModel) {
    return this.http.post<BorrowModel>(RequestUrls.BOOK.BORROW.BORROW_USER_EXIST, borrow);
  }
}
