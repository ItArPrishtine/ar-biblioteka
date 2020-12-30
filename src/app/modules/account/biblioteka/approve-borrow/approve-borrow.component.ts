import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {BorrowRequestModel} from "../../../../shared/models/book/borrow-request.model";
import {BorrowService} from "../../../../shared/services/biblioteka/borrow.service";
import {BorrowModel} from "../../../../shared/models/book/borrow.model";
import {CustomSnackbarService} from "../../../../shared/services/snackbar-service.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-approve-borrow',
  templateUrl: './approve-borrow.component.html',
  styleUrls: ['./approve-borrow.component.scss']
})
export class ApproveBorrowComponent implements OnInit {
  elementToApprove: BorrowRequestModel;
  formGroup: FormGroup;
  loading = false;
  minDate = new Date();

  constructor(public dialogRef: MatDialogRef<ApproveBorrowComponent>,
              private snackBarService: CustomSnackbarService,
              private borrowService: BorrowService) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.formGroup = new FormGroup({
      borrowDate: new FormControl()
    })
  }

  borrowBook() {
    const borrow = new BorrowModel();
    borrow.borrowRequest = this.elementToApprove;
    borrow.borrowUntil = this.formGroup.value.borrowDate;

    if (!this.formGroup.valid) {
      return;
    }

    this.borrowService.borrow(borrow).subscribe(
      () => {
        this.dialogRef.close();
        this.snackBarService.success("Kerkesa u aprovua me sukses");
      }
    )
  }
}
