import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuthenticateComponent} from './authenticate.component';
import {LoginComponent} from './login/login.component';
import {RouterModule} from '@angular/router';
import {AuthenticateRoutingModule} from './authenticate-routing.module';
import {AuthenticationService} from '../../shared/services/auth/authentication.service';
import {ReactiveFormsModule} from '@angular/forms';
import {TokenService} from '../../shared/services/auth/token.service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {ButtonSpinnerModule} from "../../shared/components/button-spinner/button-spinner.module";

@NgModule({
  declarations: [
    AuthenticateComponent,
    LoginComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    AuthenticateRoutingModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    ButtonSpinnerModule
  ],
  providers: [
    AuthenticationService,
    TokenService
  ]
})
export class AuthenticateModule {
}
