import {Component, Input} from '@angular/core';
import {IMAGEURLS} from '../../../../../shared/constants/GeneralConstant';
import {BookBorrowDTO} from "../../../../../shared/models/dto/BookBorrowDTO.model";
import BuildUrlsUtils from '../../../../../shared/utils/BuildUrlsUtils';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.scss']
})
export class BookCardComponent {
  @Input() book: BookBorrowDTO;
  bookImage = IMAGEURLS.BOOK_TEST;

  public authorDetailsUrl(authorId: string) {
    return BuildUrlsUtils.authorDetailsUrl(authorId);
  }

  public bookDetailsUrl(bookId: string) {
    return BuildUrlsUtils.bookDetailsUrl(bookId);
  }

}
