import {Component, ElementRef, OnInit, ViewChild, ViewChildren} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {PaymentService} from '../../../../shared/services/economy/payment.service';
import {PaymentModel} from '../../../../shared/models/economy/payment.model';
import {CustomSnackbarService} from '../../../../shared/services/snackbar-service.service';
import SignaturePad from 'signature_pad';
import {GeneralConstant} from '../../../../shared/constants/GeneralConstant';
import BuildUrlsUtils from '../../../../shared/utils/BuildUrlsUtils';

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrls: ['./payment-details.component.scss']
})
export class PaymentDetailsComponent implements OnInit {
  payment: PaymentModel;
  dateFormat = GeneralConstant.DATEFORMAT;

  constructor(private activatedRoute: ActivatedRoute,
              private paymentService: PaymentService,
              private router: Router,
              private snackBarService: CustomSnackbarService) { }

  ngOnInit(): void {
    this.getPaymentById();
  }

  getPaymentById() {
    const id = this.activatedRoute.snapshot.params.id;

    this.paymentService.getPayment(id).subscribe(
      result => {
        this.payment = result;
      },
      error => {
          this.snackBarService.error('Gabim gjate marrjes se pageses');
      }
    )
  }

  navigateToVerification(paymentId) {
    this.router.navigateByUrl(BuildUrlsUtils.verificationUrl(paymentId));
  }
}
