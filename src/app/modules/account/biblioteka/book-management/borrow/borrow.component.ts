import {Component, OnInit} from '@angular/core';
import {BookModel} from "../../../../../shared/models/book/book.model";
import {MatDialogRef} from "@angular/material/dialog";
import {TokenService} from "../../../../../shared/services/auth/token.service";
import {CustomSnackbarService} from "../../../../../shared/services/snackbar-service.service";
import {BorrowService} from "../../../../../shared/services/biblioteka/borrow.service";
import {BorrowModel} from "../../../../../shared/models/book/borrow.model";
import {finalize} from "rxjs/operators";
import {AccountUserModel} from "../../../../../shared/models/account/account-user.model";
import {BorrowStatusEnum} from "../../../../../shared/models/enums/borrow-status.enum";

@Component({
  selector: 'app-borrow-request',
  templateUrl: './borrow.component.html',
  styleUrls: ['./borrow.component.scss']
})
export class BorrowComponent implements OnInit {
  book: BookModel;
  returnBook = false;
  borrow = new BorrowModel();
  borrowExist: BorrowModel;
  loading = false;
  borrowIsChecked = false;

  constructor(public dialogRef: MatDialogRef<BorrowComponent>,
              public borrowService: BorrowService,
              public snackBarService: CustomSnackbarService,
              public tokenService: TokenService) {
  }

  ngOnInit() {
    this.buildBorrowModel();
    this.checkIfAnyBorrowExist();
  }

  private buildBorrowModel() {
    this.borrow.book = this.book;
    this.borrow.applicationUser = new AccountUserModel();
    this.borrow.applicationUser.id = this.tokenService.getData().id;
  }

  checkIfAnyBorrowExist() {
    this.borrowService.userBorrowExist(this.borrow)
      .subscribe(
        result => {
          this.borrowExist = result;
          this.borrowIsChecked = true;
        }, error => {
          this.snackBarService.error('Gabim gjate validimit nese libri eshte i huazuar!');
        });
  }

  borrowBook() {
    this.loading = true;

    this.borrowService.borrow(this.borrow)
      .pipe(finalize(() => {
        this.loading = false;
        this.dialogRef.close();
      }))
      .subscribe(
      result => {
        this.snackBarService.success('Libri eshte huazuar me sukses.')
      }, error => {
        this.snackBarService.error('Gabim gjate procesit te huazimit!');
    });
  }

  returnBookRequest() {
    this.loading = true;

    this.borrowService.borrowReturn(this.borrowExist)
      .pipe(finalize(() => {
        this.loading = false;
        this.dialogRef.close();
      }))
      .subscribe(
        result => {
          this.snackBarService.success('Libri eshte kthyer me sukses.')
        }, error => {
          this.snackBarService.error('Gabim gjate procesit te kthimit!');
        });
  }
}
