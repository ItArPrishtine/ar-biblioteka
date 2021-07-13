import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { finalize } from 'rxjs/operators';
import { AccountUserModel } from 'src/app/shared/models/account/account-user.model';
import { PaymentModel } from 'src/app/shared/models/economy/payment.model';
import { UserService } from 'src/app/shared/services/account/user.service';
import { PaymentService } from 'src/app/shared/services/economy/payment.service';
import { CustomSnackbarService } from 'src/app/shared/services/snackbar-service.service';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.scss']
})
export class PaymentFormComponent implements OnInit {
  loading: false;
  formGroup: FormGroup;
  users: AccountUserModel[] = [];

  constructor(private dialog: MatDialog, 
              private userService: UserService,
              private paymentService: PaymentService,
              private snackBarService: CustomSnackbarService) { }

  ngOnInit(): void {
    this.initForm();
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getUsers().subscribe(
      result => {
        this.users = result;
      }
    )
  }

  
  initForm() {
    this.formGroup = new FormGroup({
      applicationUser: new FormControl('', Validators.required),
      paymentDate: new FormControl('', Validators.required),
      payedMonth: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
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
    payment.payedMonth = value.payedMonth;
    payment.paymentDate = value.paymentDate;
    payment.description = value.description;
    payment.price = value.price;



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
