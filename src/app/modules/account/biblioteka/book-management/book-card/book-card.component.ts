import {Component, Input} from '@angular/core';
import {BookModel} from '../../../../../shared/models/book/book.model';
import {IMAGEURLS} from '../../../../../shared/constants/GeneralConstant';
import {RouterUrls} from '../../../../../shared/constants/RouterUrls';
import {BookBorrowDTO} from "../../../../../shared/models/dto/BookBorrowDTO.model";

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.scss']
})
export class BookCardComponent {
  @Input() book: BookBorrowDTO;
  bookImage = IMAGEURLS.BOOK_TEST;
  authorDetailsUrl = '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.AUTHORDETAILS;

  detailsUrl = '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BOOK_DETAILS;

}
