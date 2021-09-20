import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {UserListComponent} from './user-list/user-list.component';
import {RouterUrls} from '../../../shared/constants/RouterUrls';
import {ResetPasswordComponent} from "./reset-password/reset-password.component";
import {UserDetailsComponent} from './user-details/user-details.component';

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
  },
  {
    path: RouterUrls.ACCOUNT.PROFILE + '/:id',
    component: UserDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountManagementRoutingModule { }
