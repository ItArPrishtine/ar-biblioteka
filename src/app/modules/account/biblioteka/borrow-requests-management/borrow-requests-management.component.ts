import {Component, OnInit} from '@angular/core';
import {CustomSnackbarService} from "../../../../shared/services/snackbar-service.service";
import {BorrowService} from "../../../../shared/services/biblioteka/borrow.service";
import {BorrowStatusEnum} from "../../../../shared/models/enums/borrow-status.enum";
import {TokenService} from "../../../../shared/services/auth/token.service";
import { GeneralConstant } from 'src/app/shared/constants/GeneralConstant';
import BuildUrlsUtils from '../../../../shared/utils/BuildUrlsUtils';
import {Router} from '@angular/router';

@Component({
  selector: 'app-borrow-requests-management',
  templateUrl: './borrow-requests-management.component.html',
  styleUrls: ['./borrow-requests-management.component.scss']
})
export class BorrowRequestsManagementComponent implements OnInit {
  displayedColumns: string[] = ['username', 'bookTitle', 'category', 'author', 'borrowUntil', 'returnedDate', 'borrowStatus'];
  dataSource: any;
  filters = [BorrowStatusEnum.BORROWED, BorrowStatusEnum.RETURNED];
  borrowedStatus = BorrowStatusEnum.BORROWED;

  constructor(private borrowService: BorrowService,
              private router: Router,
              private snackBarService: CustomSnackbarService) {
  }

  ngOnInit() {
    this.getBorrows();
  }

  public bookDetailsUrl(bookId: string) {
    return BuildUrlsUtils.bookDetailsUrl(bookId);
  }

  public navigateToDetails(bookID) {
      this.router.navigateByUrl(this.bookDetailsUrl(bookID));
  }

  getBorrows(status?: BorrowStatusEnum) {
    this.borrowService.getBorrows(status).subscribe(
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
