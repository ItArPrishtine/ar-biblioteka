import {Component, OnInit} from '@angular/core';
import {BookModel} from "../../../../../shared/models/book/book.model";
import {MatDialogRef} from "@angular/material/dialog";
import {BorrowRequestModel} from "../../../../../shared/models/book/borrow-request.model";
import {TokenService} from "../../../../../shared/services/auth/token.service";
import {AccountUserModel} from "../../../../../shared/models/account/account-user.model";
import {FormControl, FormGroup} from "@angular/forms";
import {finalize} from "rxjs/operators";
import {BorrowRequestService} from "../../../../../shared/services/biblioteka/borrow-request.service";
import {CustomSnackbarService} from "../../../../../shared/services/snackbar-service.service";
import {BorrowRequestStatusEnum} from "../../../../../shared/models/enums/BorrowRequestStatus.enum";

@Component({
  selector: 'app-borrow-request',
  templateUrl: './borrow-request.component.html',
  styleUrls: ['./borrow-request.component.scss']
})
export class BorrowRequestComponent implements OnInit {
  book: BookModel;
  formGroup: FormGroup;
  loading = false;
  cancelRequestLoading = false;
  cancelNewRequestLoading = false;
  minDate = new Date();
  borrowedBook: BorrowRequestModel;
  requestStatus = BorrowRequestStatusEnum;

  constructor(public dialogRef: MatDialogRef<BorrowRequestComponent>,
              public borrowRequestService: BorrowRequestService,
              public snackBarService: CustomSnackbarService,
              public tokenService: TokenService) {
  }


  ngOnInit() {
    this.checkIfRequestExist();
    this.initForm();
  }

  initForm() {
    this.formGroup = new FormGroup({
      borrowDate: new FormControl()
    })
  }

  checkIfRequestExist() {
    const userID = this.tokenService.getData().id;
    this.borrowRequestService.borrowExist(userID)
      .subscribe(
        (result) => {
          this.borrowedBook = result;
        },
        error => {
          console.log(error);
        }
      )
  }

  requestForBook() {
    this.loading = true;
    const borrowRequestModel = new BorrowRequestModel();
    const user = new AccountUserModel();
    user.id = this.tokenService.getData().id;

    borrowRequestModel.book = this.book;
    borrowRequestModel.applicationUser = user;
    borrowRequestModel.borrowFrom = this.formGroup.value.borrowDate;

    this.borrowRequestService.borrowRequest(borrowRequestModel)
      .pipe(finalize(() => this.loading = false))
      .subscribe(
        () => {
          this.dialogRef.close();
          this.snackBarService.success('Kerkesa u realizua me sukses');
        },
        () => {
          this.snackBarService.error('Gabim gjate procesimit te kerkeses');
        }
      )
  }

  cancelRequest() {
    this.cancelRequestLoading = true;
    const borrowRequestModel = new BorrowRequestModel();
    const user = new AccountUserModel();
    user.id = this.tokenService.getData().id;

    borrowRequestModel.book = this.book;
    borrowRequestModel.applicationUser = user;
    borrowRequestModel.borrowFrom = this.formGroup.value.borrowDate;

    this.borrowRequestService.cancelRequest(borrowRequestModel)
      .pipe(finalize(() => this.cancelRequestLoading = false))
      .subscribe(
        () => {
          this.dialogRef.close();
          this.snackBarService.success('Kerkesa u anulua me sukses');
        },
        () => {
          this.snackBarService.success('Gabim gjate anulimit te kerkeses');
        }
      )
  }

  cancelAndNewRequest() {
    this.cancelNewRequestLoading = true;
    const borrowRequestModel = new BorrowRequestModel();
    const user = new AccountUserModel();
    user.id = this.tokenService.getData().id;

    borrowRequestModel.book = this.book;
    borrowRequestModel.applicationUser = user;
    borrowRequestModel.borrowFrom = this.formGroup.value.borrowDate;

    this.borrowRequestService.cancelAndRequestNew(borrowRequestModel)
      .pipe(finalize(() => this.cancelNewRequestLoading = false))
      .subscribe(
        () => {
          this.dialogRef.close();
          this.snackBarService.success('Kerkesa juaj u realizua me sukses');
        },
        () => {
          this.snackBarService.error('Gabim gjate procesimit te kerkeses');
        }
      )
  }

}
