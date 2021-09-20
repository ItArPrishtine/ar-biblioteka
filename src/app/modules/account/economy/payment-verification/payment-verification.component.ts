import { Component, OnInit } from '@angular/core';
import {PaymentModel} from '../../../../shared/models/economy/payment.model';
import {GeneralConstant} from '../../../../shared/constants/GeneralConstant';
import {ActivatedRoute} from '@angular/router';
import {PaymentService} from '../../../../shared/services/economy/payment.service';
import {CustomSnackbarService} from '../../../../shared/services/snackbar-service.service';
import {AccountUserModel} from '../../../../shared/models/account/account-user.model';
import {TokenService} from '../../../../shared/services/auth/token.service';
import {UserService} from '../../../../shared/services/account/user.service';
import {PaymentTypeEnum} from '../../../../shared/models/enums/payment-type.enum';

@Component({
  selector: 'app-payment-verification',
  templateUrl: './payment-verification.component.html',
  styleUrls: ['./payment-verification.component.scss']
})
export class PaymentVerificationComponent implements OnInit {
  payment: PaymentModel;
  user: AccountUserModel;
  dateFormat = GeneralConstant.DATEFORMAT;
  signed: boolean;
  mujore = PaymentTypeEnum.MUJORE;

  constructor(private activatedRoute: ActivatedRoute,
              private paymentService: PaymentService,
              private snackBarService: CustomSnackbarService,
              private userService: UserService,
              private route: ActivatedRoute,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    this.getPaymentById();
    this.getCurrentUser();
  }

  clickedCheckbox(event) {
    if (this.user.esign) {
      this.signed = event.checked;
    } else {
      this.snackBarService.error("Ju se pari duhet te vendosni nenshkrimin tek profili juaj!");
    }
  }

  getCurrentUser() {
    const id = this.tokenService.getData().id;

    this.userService.getUserById(id).subscribe(
      result => {
        this.user = result;
      },
      error => {
        this.snackBarService.error('Gabim gjate marrjes se user-it');
      }
    )
  }

  getPaymentById() {
    const id = this.activatedRoute.snapshot.params.id;

    this.paymentService.getPayment(id).subscribe(
      result => {
        this.payment = result;
        this.signed = this.payment.verifiedFromUser;
      },
      error => {
        this.snackBarService.error('Gabim gjate marrjes se pageses');
      }
    )
  }

  savePayment() {
    if (this.signed) {
      this.payment.verifiedFromUser = this.signed;

      this.paymentService.verifyPayment(this.payment).subscribe(
        result => {
          this.snackBarService.success("Pagesa eshte nenshkruar me sukses");
        }
      )
    }

  }
}
