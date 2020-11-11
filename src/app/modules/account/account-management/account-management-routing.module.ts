import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {UserListComponent} from './user-list/user-list.component';
import {RouterUrls} from '../../../shared/constants/RouterUrls';

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
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountManagementRoutingModule { }
