import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RouterUrls} from '../../../shared/constants/RouterUrls';
import {EconomyComponent} from '../economy/economy.component';
import { PaymentListComponent } from './payment-list/payment-list.component';

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
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EconomyRoutingModule { }
