import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {BorrowRequestModel} from "../../../../shared/models/book/borrow-request.model";
import {BorrowService} from "../../../../shared/services/biblioteka/borrow.service";
import {BorrowModel} from "../../../../shared/models/book/borrow.model";
import {CustomSnackbarService} from "../../../../shared/services/snackbar-service.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-reject-borrow',
  templateUrl: './reject-borrow.component.html',
  styleUrls: ['./reject-borrow.component.scss']
})
export class RejectBorrowComponent implements OnInit {
  elementToReject: BorrowRequestModel;
  formGroup: FormGroup;
  loading = false;
  minDate = new Date();

  constructor(public dialogRef: MatDialogRef<RejectBorrowComponent>,
              private snackBarService: CustomSnackbarService,
              private borrowService: BorrowService) { }

  ngOnInit(): void {
  }

  rejectBook() {
    this.borrowService.reject(this.elementToReject).subscribe(
      () => {
        this.dialogRef.close();
        this.snackBarService.success("Kerkesa u anulua me sukses");
      }
    )
  }
}
