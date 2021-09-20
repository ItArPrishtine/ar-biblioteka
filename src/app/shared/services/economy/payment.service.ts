import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import {ResponsePageModel} from '../../models/shared/ResponsePage.model';
import {BookModel} from '../../models/book/book.model';
import { PaymentModel } from '../../models/economy/payment.model';

@Injectable()
export class PaymentService {

  constructor(private http: HttpClient) { }

  getPayments(value?: number) {
    if (!value) {
      value = 0;
    }
    return this.http.get<PaymentModel[]>(`${RequestUrls.ECONOMY.PAYMENT.BASE}?userId=${value}`);
  }

  getPayment(id: number) {
    return this.http.get<PaymentModel>(`${RequestUrls.ECONOMY.PAYMENT.BASE}${id}`);
  }

  verifyPayment(payment: PaymentModel) {
    return this.http.put<PaymentModel>(RequestUrls.ECONOMY.PAYMENT.VERIFY, payment.id);
  }

  createPayment(payment: any) {
    return this.http.post<PaymentModel>(RequestUrls.ECONOMY.PAYMENT.BASE, payment);
  }

  deletePayment(id: string) {
    return this.http.delete(RequestUrls.ECONOMY.PAYMENT.BASE + `${id}`);
  }

  updatePayment(payment: any) {
    return this.http.put<PaymentModel>(RequestUrls.ECONOMY.PAYMENT.BASE, payment);
  }
}
