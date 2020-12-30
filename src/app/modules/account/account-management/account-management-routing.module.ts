import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {UserListComponent} from './user-list/user-list.component';
import {RouterUrls} from '../../../shared/constants/RouterUrls';
import {ResetPasswordComponent} from "./reset-password/reset-password.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: RouterUrls.ACCOUNT.MANAGEMENT.USERS,
    pathMatch: 'full'
  },
  {
    path: RouterUrls.ACCOUNT.MANAGEMENT.USERS,
    component: UserListComponent
  },
  {
    path: RouterUrls.ACCOUNT.RESET_PASSWORD,
    component: ResetPasswordComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountManagementRoutingModule { }
