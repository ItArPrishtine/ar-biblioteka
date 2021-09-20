import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RouterUrls} from '../../../shared/constants/RouterUrls';
import {EconomyComponent} from '../economy/economy.component';
import { PaymentListComponent } from './payment-list/payment-list.component';
import {PaymentDetailsComponent} from './payment-details/payment-details.component';
import {PaymentVerificationComponent} from './payment-verification/payment-verification.component';

const routes: Routes = [
  {
    path: '',
    component: EconomyComponent,
    children: [
      {
        path: '',
        redirectTo: RouterUrls.ECONOMY.PAYMENT,
        pathMatch: 'full'
      },
      {
        path: RouterUrls.ECONOMY.PAYMENT,
        component: PaymentListComponent
      },
      {
        path: RouterUrls.ECONOMY.PAYMENT + '/:id',
        component: PaymentDetailsComponent
      },
      {
        path: RouterUrls.ECONOMY.PAYMENT_VERIFY + '/:id',
        component: PaymentVerificationComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EconomyRoutingModule { }
