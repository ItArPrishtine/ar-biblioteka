import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {BookService} from '../../../../../shared/services/biblioteka/book.service';
import {BookModel} from '../../../../../shared/models/book/book.model';
import {IMAGEURLS} from "../../../../../shared/constants/GeneralConstant";
import {RouterUrls} from "../../../../../shared/constants/RouterUrls";

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {
  book: BookModel;
  bookImage = IMAGEURLS.BOOK_TEST;
  commentedAvatar = IMAGEURLS.AUTHOR_AVATAR;
  authorDetailsUrl = '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.AUTHORDETAILS;

  constructor(private activeRoute: ActivatedRoute,
              private dialog: MatDialog,
              private router: Router,
              private bookService: BookService) { }

  ngOnInit(): void {
    this.getBookDetails();
  }

  getBookDetails() {
    const id = this.activeRoute.snapshot.params.id;
    this.bookService.getBookById(id).subscribe(result => {
      this.book = result;
    });
  }

}
