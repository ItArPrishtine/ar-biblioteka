import {Component, OnInit} from '@angular/core';
import {CustomSnackbarService} from "../../../../shared/services/snackbar-service.service";
import {MatDialog} from "@angular/material/dialog";
import {BorrowService} from "../../../../shared/services/biblioteka/borrow.service";

@Component({
  selector: 'app-borrow-requests-management',
  templateUrl: './borrow-requests-management.component.html',
  styleUrls: ['./borrow-requests-management.component.scss']
})
export class BorrowRequestsManagementComponent implements OnInit {
  displayedColumns: string[] = ['username', 'bookTitle', 'category', 'author', 'borrowUntil', 'borrowStatus'];
  dataSource: any;

  constructor(private borrowService: BorrowService,
              private snackBarService: CustomSnackbarService) {
  }

  ngOnInit() {
    this.getBorrows();
  }

  getBorrows() {
    this.borrowService.getBorrows().subscribe(
      result => {
        this.dataSource = result;
      }, () => {
        this.snackBarService.error("Error gjate procesimit te kerkesave");
      }
    )
  }

}
