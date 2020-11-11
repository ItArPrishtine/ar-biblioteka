import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BibliotekaComponent} from './biblioteka.component';
import {RouterModule} from '@angular/router';
import {AuthorManagementComponent} from './author-management/author-management.component';
import {AuthorListComponent} from './author-management/author-list/author-list.component';
import {AuthorFormComponent} from './author-management/author-form/author-form.component';
import {BibliotekaRoutingModule} from './biblioteka-routing.module';
import { AuthorCardComponent } from './author-management/author-card/author-card.component';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {AuthorService} from "../../../shared/services/biblioteka/author.service";
import { AuthorDetailsComponent } from './author-management/author-details/author-details.component';

@NgModule({
  declarations: [
    BibliotekaComponent,
    AuthorManagementComponent,
    AuthorListComponent,
    AuthorFormComponent,
    AuthorCardComponent,
    AuthorDetailsComponent
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
  ],
  providers: [
    AuthorService
  ],
  entryComponents: [
    AuthorFormComponent
  ]
})
export class BibliotekaModule {
}
