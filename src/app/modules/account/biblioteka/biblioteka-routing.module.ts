import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BibliotekaComponent} from './biblioteka.component';
import {RouterUrls} from '../../../shared/constants/RouterUrls';
import {AuthorManagementComponent} from './author-management/author-management.component';
import {AuthorDetailsComponent} from './author-management/author-details/author-details.component';
import {BookManagementComponent} from "./book-management/book-management.component";
import {BookDetailsComponent} from "./book-management/book-details/book-details.component";
import {BorrowRequestsManagementComponent} from "./borrow-requests-management/borrow-requests-management.component";

const routes: Routes = [
  {
    path: '',
    component: BibliotekaComponent,
    children: [
      {
        path: '',
        redirectTo: RouterUrls.BIBLIOTEKA.BOOK,
        pathMatch: 'full'
      },
      {
        path: RouterUrls.BIBLIOTEKA.AUTHOR,
        component: AuthorManagementComponent
      },
      {
        path: RouterUrls.BIBLIOTEKA.AUTHORDETAILS + '/:id',
        component: AuthorDetailsComponent
      },
      {
        path: RouterUrls.BIBLIOTEKA.BOOK,
        component: BookManagementComponent
      },
      {
        path: RouterUrls.BIBLIOTEKA.BOOK_DETAILS + '/:id',
        component: BookDetailsComponent
      },
      {
        path: RouterUrls.BIBLIOTEKA.BORROW_REQUESTS,
        component: BorrowRequestsManagementComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BibliotekaRoutingModule { }
