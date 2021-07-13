import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AccountComponent} from './account.component';
import {RouterUrls} from '../../shared/constants/RouterUrls';
import { RedirectComponent } from './redirect.component';

const routes: Routes = [
  {
    path: '',
    component: AccountComponent,
    children: [
      {
        path: '',
        component: RedirectComponent
      },
      {
        path: RouterUrls.ACCOUNT.MANAGE,
        loadChildren: () => import('./account-management/account-management.module').then(mod => mod.AccountManagementModule),
      },
      {
        path: RouterUrls.BIBLIOTEKA.BASE_MODULE,
        loadChildren: () => import('./biblioteka/biblioteka.module').then(mod => mod.BibliotekaModule),
      },
      {
        path: RouterUrls.ECONOMY.BASE_MODULE,
        loadChildren: () => import('./economy/economy.module').then(mod => mod.EconomyModule),
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
