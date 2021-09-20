import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { GeneralConstant } from 'src/app/shared/constants/GeneralConstant';
import { PaymentModel } from 'src/app/shared/models/economy/payment.model';
import { UserService } from 'src/app/shared/services/account/user.service';
import { TokenService } from 'src/app/shared/services/auth/token.service';
import { PaymentService } from 'src/app/shared/services/economy/payment.service';
import { CustomSnackbarService } from 'src/app/shared/services/snackbar-service.service';
import { PaymentFormComponent } from '../payment-form/payment-form.component';
import BuildUrlsUtils from '../../../../shared/utils/BuildUrlsUtils';
import {Router} from '@angular/router';
import {PaymentTypeEnum} from '../../../../shared/models/enums/payment-type.enum';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.scss']
})
export class PaymentListComponent implements OnInit {
  payments: PaymentModel[] = [];
  dateFormat = GeneralConstant.DATEFORMAT;
  currentUserRole: string;
  users = [];
  selectedUser: any;
  typeOfAdmin = false;
  mujore = PaymentTypeEnum.MUJORE;

  constructor(private paymentService: PaymentService,
              private dialog: MatDialog,
              private snackBarService: CustomSnackbarService,
              private tokenService: TokenService,
              private userService: UserService,
              private router: Router
  ) {
  }

  ngOnInit() {
    this.currentUserRole = this.tokenService.getData().role.name;
    if (this.currentUserRole === 'KF' || this.currentUserRole === 'PG tek Ekonomia' ||
    this.currentUserRole === 'ND tek Ekonomia' || this.currentUserRole === 'PGS Pishtari') {
      this.typeOfAdmin = true;
    }

    this.getUsers();
    this.getAllPayments();
  }

  getUsers() {
    this.userService.getUsers().subscribe(
      result => {
        this.users = result;
      }
    )
  }

  userSelected(event) {
    this.selectedUser = event.value;
    if (this.selectedUser === 'user') {
      this.getAllPayments();
      return;
    }
    this.getAllUserPayments();
  }

  getAllPayments() {
    if (this.typeOfAdmin) {
          this.paymentService.getPayments().subscribe(
            result => {
              this.payments = result;
              this.fillPayedMonths();
            }
          )
        } else {
          const userId = this.tokenService.getData().id;
          this.paymentService.getPayments(userId).subscribe(
            result => {
              this.payments = result;
              this.fillPayedMonths();
            }
          )
        }
  }

  getAllUserPayments() {
    this.paymentService.getPayments(this.selectedUser).subscribe(
      result => {
        this.payments = result;
        this.fillPayedMonths();
      }
    )
  }

  fillPayedMonths() {
    let yearsAndMonths = [];
    this.payments.forEach(item => {
      yearsAndMonths.push(item.payedMonth);
    });
  }

  openCreatePaymentModal() {
    const dialogRef = this.dialog.open(PaymentFormComponent);

    dialogRef.afterClosed().subscribe(() => this.getAllPayments());
  }

  navigateToDetails(paymentId) {
    this.router.navigateByUrl(BuildUrlsUtils.paymentDetailsUrl(paymentId));
  }
}
