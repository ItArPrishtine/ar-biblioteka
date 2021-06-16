import {Component, OnInit} from '@angular/core';
import {CustomSnackbarService} from "../../../../shared/services/snackbar-service.service";
import {BorrowService} from "../../../../shared/services/biblioteka/borrow.service";
import {BorrowStatusEnum} from "../../../../shared/models/enums/borrow-status.enum";
import {TokenService} from "../../../../shared/services/auth/token.service";
import { GeneralConstant } from 'src/app/shared/constants/GeneralConstant';

@Component({
  selector: 'app-borrow-requests-management',
  templateUrl: './borrow-requests-management.component.html',
  styleUrls: ['./borrow-requests-management.component.scss']
})
export class BorrowRequestsManagementComponent implements OnInit {
  displayedColumns: string[] = ['username', 'bookTitle', 'category', 'author', 'borrowUntil', 'borrowStatus'];
  dataSource: any;
  filters = [BorrowStatusEnum.BORROWED, BorrowStatusEnum.RETURNED];
  dateFormat = GeneralConstant.DATEFORMAT;

  constructor(private borrowService: BorrowService,
              private tokenService: TokenService,
              private snackBarService: CustomSnackbarService) {
  }

  ngOnInit() {
    this.getBorrows();
  }

  getBorrows(status?: BorrowStatusEnum) {
    const userId = this.tokenService.getData().id;
    this.borrowService.getBorrows(userId, status).subscribe(
      result => {
        this.dataSource = result;
      }, () => {
        this.snackBarService.error("Error gjate procesimit te kerkesave");
      }
    )
  }

  filter(event) {
    const value = event.value;
    if (value == 'Filtron') return;
    this.getBorrows(value);
  }

}
