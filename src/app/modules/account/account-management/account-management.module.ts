import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { UserCreateUpdateComponent } from './user-create-update/user-create-update.component';
import {RouterModule} from '@angular/router';
import {AccountManagementRoutingModule} from './account-management-routing.module';
import {MatTableModule} from '@angular/material/table';
import {UserService} from '../../../shared/services/account/user.service';
import {MatButtonModule} from '@angular/material/button';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {RoleService} from '../../../shared/services/account/role.service';
import {MatSelectModule} from '@angular/material/select';
import {ButtonSpinnerModule} from "../../../shared/components/button-spinner/button-spinner.module";
import {MatMenuModule} from "@angular/material/menu";
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import {SignaturePadModule} from 'angular2-signaturepad';

@NgModule({
  declarations: [
    UserListComponent,
    UserCreateUpdateComponent,
    ResetPasswordComponent,
    UserDetailsComponent
  ],
    imports: [
        CommonModule,
        RouterModule,
        AccountManagementRoutingModule,
        MatTableModule,
        MatButtonModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatDialogModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatSelectModule,
        ButtonSpinnerModule,
        MatMenuModule,
        SignaturePadModule
    ],
  providers: [
    UserService,
    RoleService,
    MatDatepickerModule
  ],
  entryComponents: [
    UserCreateUpdateComponent
  ]
})
export class AccountManagementModule { }
