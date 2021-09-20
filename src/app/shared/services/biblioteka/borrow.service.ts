import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import {BorrowModel} from "../../models/book/borrow.model";
import {Observable} from "rxjs";
import {BorrowStatusEnum} from "../../models/enums/borrow-status.enum";

@Injectable()
export class BorrowService {

  constructor(private http: HttpClient) { }

  borrow(borrow: BorrowModel) {
    return this.http.post<BorrowModel>(RequestUrls.BOOK.BORROW.BORROW, borrow);
  }

  borrowReturn(borrow: BorrowModel) {
    return this.http.post<BorrowModel>(RequestUrls.BOOK.BORROW.RETURN, borrow);
  }

  getBorrows(status?: BorrowStatusEnum) {
    let url;
    if (status) {
      url = RequestUrls.BOOK.BORROW.BORROW + `?status=${status}`;
    } else {
      url = RequestUrls.BOOK.BORROW.BORROW;
    }
    return this.http.get<BorrowModel[]>(url);
  }

  userBorrowExist(borrow: BorrowModel) {
    return this.http.post<BorrowModel>(RequestUrls.BOOK.BORROW.BORROW_USER_EXIST, borrow);
  }

  extendDeadline(borrow: BorrowModel) {
    debugger;
    return this.http.post<BorrowModel>(RequestUrls.BOOK.BORROW.EXTEND_DEADLINE + '/' + borrow.id, null);
  }
}
