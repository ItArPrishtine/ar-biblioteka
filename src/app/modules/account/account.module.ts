import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountComponent } from './account.component';
import {AccountRoutingModule} from "./account-routing.module";
import { HeaderComponent } from './header/header.component';
import {_MatMenuDirectivesModule, MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";



@NgModule({
  declarations: [AccountComponent, HeaderComponent],
  imports: [
    CommonModule,
    AccountRoutingModule,
    _MatMenuDirectivesModule,
    MatButtonModule,
    MatMenuModule
  ],
})
export class AccountModule { }
