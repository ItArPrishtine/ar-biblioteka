import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AccountComponent} from "./account.component";
import {RouterUrls} from "../../shared/constants/RouterUrls";

const routes: Routes = [
  {
    path: '',
    component: AccountComponent,
    children: [
      {
        path: '',
        redirectTo: RouterUrls.ACCOUNT.MANAGE,
        pathMatch: 'full'
      },
      {
        path: RouterUrls.ACCOUNT.MANAGE,
        loadChildren: () => import('./account-management/account-management.module').then(mod => mod.AccountManagementModule),
      },
      {
        path: RouterUrls.BIBLIOTEKA.BASE_MODULE,
        loadChildren: () => import('./biblioteka/biblioteka.module').then(mod => mod.BibliotekaModule),
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
