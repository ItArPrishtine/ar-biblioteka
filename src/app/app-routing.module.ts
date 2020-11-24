import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AppComponent} from "./app.component";
import {RouterUrls} from "./shared/constants/RouterUrls";
import {AuthenticateGuard} from "./shared/guards/authenticate.guard";
import {AccountGuard} from "./shared/guards/account.guard";


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
        loadChildren: () => import('./modules/authenticate/authenticate.module').then(mod => mod.AuthenticateModule),
        canActivate: [ AuthenticateGuard ]
      },
      {
        path: RouterUrls.ACCOUNT.BASE_MODULE,
        loadChildren: () => import('./modules/account/account.module').then(mod => mod.AccountModule),
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
