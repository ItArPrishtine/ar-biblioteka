import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticateComponent } from './authenticate.component';
import { LoginComponent } from './login/login.component';
import { RouterModule } from "@angular/router";
import { AuthenticateRoutingModule } from "./authenticate-routing.module";
import { AuthenticationService } from "../../shared/services/auth/authentication.service";
import { ReactiveFormsModule } from "@angular/forms";
import { TokenService } from "../../shared/services/auth/token.service";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";

@NgModule({
  declarations: [AuthenticateComponent, LoginComponent],
  imports: [
    CommonModule,
    RouterModule,
    AuthenticateRoutingModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  providers: [
    AuthenticationService,
    TokenService
  ]
})
export class AuthenticateModule { }
