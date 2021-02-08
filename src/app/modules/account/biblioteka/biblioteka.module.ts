import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BibliotekaComponent} from './biblioteka.component';
import {RouterModule} from '@angular/router';
import {AuthorManagementComponent} from './author-management/author-management.component';
import {AuthorListComponent} from './author-management/author-list/author-list.component';
import {AuthorFormComponent} from './author-management/author-form/author-form.component';
import {BibliotekaRoutingModule} from './biblioteka-routing.module';
import {AuthorCardComponent} from './author-management/author-card/author-card.component';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {AuthorService} from '../../../shared/services/biblioteka/author.service';
import {AuthorDetailsComponent} from './author-management/author-details/author-details.component';
import {BookManagementComponent} from './book-management/book-management.component';
import {BookListComponent} from './book-management/book-list/book-list.component';
import {BookCardComponent} from './book-management/book-card/book-card.component';
import {BookDetailsComponent} from './book-management/book-details/book-details.component';
import {BookFormComponent} from './book-management/book-form/book-form.component';
import {BookService} from "../../../shared/services/biblioteka/book.service";
import {MatTooltipModule} from "@angular/material/tooltip";
import {BorrowComponent} from './book-management/borrow/borrow.component';
import {ButtonSpinnerModule} from "../../../shared/components/button-spinner/button-spinner.module";
import { BorrowRequestsManagementComponent } from './borrow-requests-management/borrow-requests-management.component';
import {MatMenuModule} from "@angular/material/menu";
import {MatTableModule} from "@angular/material/table";
import {BorrowService} from "../../../shared/services/biblioteka/borrow.service";
import {BookCommentService} from "../../../shared/services/biblioteka/book-comment.service";
import {MatIconModule} from "@angular/material/icon";

@NgModule({
  declarations: [
    BibliotekaComponent,
    AuthorManagementComponent,
    AuthorListComponent,
    AuthorFormComponent,
    AuthorCardComponent,
    AuthorDetailsComponent,
    BookManagementComponent,
    BookListComponent,
    BookCardComponent,
    BookDetailsComponent,
    BookFormComponent,
    BorrowComponent,
    BorrowRequestsManagementComponent,
  ],
    imports: [
        CommonModule,
        RouterModule,
        BibliotekaRoutingModule,
        MatButtonModule,
        MatDialogModule,
        MatFormFieldModule,
        MatSelectModule,
        ReactiveFormsModule,
        MatInputModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatTooltipModule,
        ButtonSpinnerModule,
        MatMenuModule,
        MatTableModule,
        MatIconModule,
    ],
  providers: [
    AuthorService,
    BookService,
    BorrowService,
    BookCommentService
  ],
  entryComponents: [
    AuthorFormComponent,
    BorrowComponent
  ]
})
export class BibliotekaModule {
}
