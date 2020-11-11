import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BibliotekaComponent} from './biblioteka.component';
import {RouterUrls} from '../../../shared/constants/RouterUrls';
import {AuthorManagementComponent} from './author-management/author-management.component';
import {AuthorDetailsComponent} from "./author-management/author-details/author-details.component";

const routes: Routes = [
  {
    path: '',
    component: BibliotekaComponent,
    children: [
      {
        path: '',
        redirectTo: RouterUrls.BIBLIOTEKA.AUTHOR,
        pathMatch: 'full'
      },
      {
        path: RouterUrls.BIBLIOTEKA.AUTHOR,
        component: AuthorManagementComponent
      },
      {
        path: RouterUrls.BIBLIOTEKA.AUTHORDETAILS + '/:id',
        component: AuthorDetailsComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BibliotekaRoutingModule { }
