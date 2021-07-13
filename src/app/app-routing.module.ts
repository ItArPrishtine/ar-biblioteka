import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {RouterUrls} from './shared/constants/RouterUrls';
import {AuthenticateGuard} from './shared/guards/authenticate.guard';
import {AccountGuard} from './shared/guards/account.guard';


const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    children: [
      {
        path: '',
        redirectTo: RouterUrls.AUTHENTICATE.BASE_MODULE,
        pathMatch: 'full'
      },
      {
        path: RouterUrls.AUTHENTICATE.BASE_MODULE,
        loadChildren: () => import('src/app/modules/authenticate/authenticate.module').then(m => m.AuthenticateModule),
        canActivate: [ AuthenticateGuard ]
      },
      {
        path: RouterUrls.ACCOUNT.BASE_MODULE,
        loadChildren: () => import('src/app/modules/account/account.module').then(m => m.AccountModule),
        canActivate: [ AccountGuard ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
