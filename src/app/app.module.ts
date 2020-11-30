import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthenticateGuard} from './shared/guards/authenticate.guard';
import {TokenService} from './shared/services/auth/token.service';
import {AccountGuard} from './shared/guards/account.guard';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpInterceptorService} from './shared/services/http-interceptor.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [
    AuthenticateGuard,
    AccountGuard,
    TokenService,
    {provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
