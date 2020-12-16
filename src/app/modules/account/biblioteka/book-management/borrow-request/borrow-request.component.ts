import { Component, OnInit } from '@angular/core';
import {BookModel} from "../../../../../shared/models/book/book.model";
import {MatDialogRef} from "@angular/material/dialog";
import {BookService} from "../../../../../shared/services/biblioteka/book.service";
import {BorrowRequestModel} from "../../../../../shared/models/book/borrow-request.model";
import {TokenService} from "../../../../../shared/services/auth/token.service";
import {AccountUserModel} from "../../../../../shared/models/account/account-user.model";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-borrow-request',
  templateUrl: './borrow-request.component.html',
  styleUrls: ['./borrow-request.component.scss']
})
export class BorrowRequestComponent implements OnInit {
  book: BookModel;
  formGroup: FormGroup;

  constructor(public dialogRef: MatDialogRef<BorrowRequestComponent>,
              public bookService: BookService,
              public tokenService: TokenService) { }


  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.formGroup = new FormGroup({
      borrowDate: new FormControl()
    })
  }

  requestForBook() {
    const borrowRequestModel = new BorrowRequestModel();
    const user = new AccountUserModel();
    user.id = this.tokenService.getData().id;

    borrowRequestModel.book = this.book;
    borrowRequestModel.applicationUser = user;
    borrowRequestModel.borrowFrom = this.formGroup.value.borrowDate;

    this.bookService.borrowRequest(borrowRequestModel).subscribe(
      result => {
        this.dialogRef.close();
      },
      error => {
        console.log(error);
      }
    )
  }

}
