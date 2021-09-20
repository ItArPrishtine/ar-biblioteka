import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { finalize } from 'rxjs/operators';
import { AccountUserModel } from 'src/app/shared/models/account/account-user.model';
import { PaymentModel } from 'src/app/shared/models/economy/payment.model';
import { UserService } from 'src/app/shared/services/account/user.service';
import { PaymentService } from 'src/app/shared/services/economy/payment.service';
import { CustomSnackbarService } from 'src/app/shared/services/snackbar-service.service';
import {PayedMonthEnum} from '../../../../shared/models/enums/payed-month.enum';
import {PayedYearEnum} from '../../../../shared/models/enums/payed-year.enum';
import {PaymentTypeEnum} from '../../../../shared/models/enums/payment-type.enum';
import {TokenService} from '../../../../shared/services/auth/token.service';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.scss']
})
export class PaymentFormComponent implements OnInit {
  loading: false;
  formGroup: FormGroup;
  signed = false;
  todayDate = new Date();
  currentUser: AccountUserModel;
  users: AccountUserModel[] = [];
  mujore = PaymentTypeEnum.MUJORE
  payedYear = [PayedYearEnum.TWENTYTWENTY, PayedYearEnum.TWENTYTWENTYONE, PayedYearEnum.TWENTYTWENTYTWO];
  paymentType = [PaymentTypeEnum.MUJORE, PaymentTypeEnum.MARKETING, PaymentTypeEnum.DHURIM];
  paymentMonths = [PayedMonthEnum.JANUARY, PayedMonthEnum.FEBRUARY, PayedMonthEnum.MARCH, PayedMonthEnum.APRIL, PayedMonthEnum.MAY,
    PayedMonthEnum.JUNE, PayedMonthEnum.JULY, PayedMonthEnum.AUGUST, PayedMonthEnum.SEPTEMBER, PayedMonthEnum.OCTOBER,
    PayedMonthEnum.NOVEMBER, PayedMonthEnum.DECEMBER]

  constructor(private dialog: MatDialog,
              private userService: UserService,
              private paymentService: PaymentService,
              private tokenService: TokenService,
              private snackBarService: CustomSnackbarService) { }

  ngOnInit(): void {
    this.initForm();
    this.getAllUsers();
    this.getCurrentUser();
  }

  getAllUsers() {
    this.userService.getUsers().subscribe(
      result => {
        this.users = result;
      }
    );
  }

  getCurrentUser() {
    const id = this.tokenService.getData().id;

    this.userService.getUserById(id).subscribe(
      result => {
        this.currentUser = result;
      }
    );
  }

  clickedCheckbox(event) {
    this.signed = event.checked;
  }

  initForm() {
    this.formGroup = new FormGroup({
      applicationUser: new FormControl('', Validators.required),
      paymentDate: new FormControl('', Validators.required),
      paymentType: new FormControl('', Validators.required),
      payedMonth: new FormControl(''),
      price: new FormControl('', Validators.required),
      payedYear: new FormControl(''),
      description: new FormControl('', Validators.required),
    });
  }

  closeDialog() {
    this.dialog.closeAll();
  }

  createPayment() {
    if (!this.formGroup.valid) {
      return;
    }

    const value = this.formGroup.value;
    const payment = new PaymentModel();
    payment.applicationUser = new AccountUserModel();

    payment.applicationUser.id = value.applicationUser;
    payment.payedMonth = value.payedMonth ? value.payedMonth : null;
    payment.paymentType = value.paymentType;
    payment.paymentDate = value.paymentDate;
    payment.payedYear = value.payedYear.length ? value.payedYear : null;
    payment.description = value.description;
    payment.price = value.price;
    payment.verifiedFromEconomy = this.signed;

    this.paymentService.createPayment(payment).pipe
    (finalize(() => this.loading = false))
    .subscribe(() => {
      this.closeDialog();
      this.snackBarService.success("Pagesa u shtua me sukses");
    }, () => {
      this.snackBarService.error("Gabim gjate shtimit te pageses");
    })
  }


}
