import { Component } from '@angular/core';
import {CustomSnackbarService} from "../../../../shared/services/snackbar-service.service";
import {BorrowRequestService} from "../../../../shared/services/biblioteka/borrow-request.service";
import {UserCreateUpdateComponent} from "../../account-management/user-create-update/user-create-update.component";
import {ApproveBorrowComponent} from "../approve-borrow/approve-borrow.component";
import {MatDialog} from "@angular/material/dialog";
import {BorrowRequestStatusEnum} from "../../../../shared/models/enums/BorrowRequestStatus.enum";
import {RejectBorrowComponent} from "../reject-borrow/reject-borrow.component";

@Component({
  selector: 'app-borrow-requests-management',
  templateUrl: './borrow-requests-management.component.html',
  styleUrls: ['./borrow-requests-management.component.scss']
})
export class BorrowRequestsManagementComponent {
  displayedColumns: string[] = ['username', 'borrowFrom', 'bookTitle', 'status', 'action'];
  dataSource: any;
  requestStatus = BorrowRequestStatusEnum;

  constructor(private borrowRequests: BorrowRequestService,
              private snackBarService: CustomSnackbarService,
              private dialog: MatDialog
  ) {
    this.getBorrowRequests();
  }

  getBorrowRequests() {
    this.borrowRequests.getBorrowRequests().subscribe(
      result => {
        this.dataSource = result;
      }, () => {
        this.snackBarService.error("Error gjate procesimit te kerkesave");
      }
    )
  }

  approveRequest(element: any) {
    const dialogRef = this.dialog.open(ApproveBorrowComponent);
    dialogRef.componentInstance.elementToApprove = element;
    dialogRef.afterClosed().subscribe(() => this.getBorrowRequests());
  }

  rejectRequest(element: any) {
    debugger;
    const dialogRef = this.dialog.open(RejectBorrowComponent);
    dialogRef.componentInstance.elementToReject = element;
    dialogRef.afterClosed().subscribe(() => this.getBorrowRequests());
  }
}
