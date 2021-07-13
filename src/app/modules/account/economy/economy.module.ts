import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import { MatMenuModule } from "@angular/material/menu";
import { MatTableModule } from "@angular/material/table";
import { BorrowService } from "../../../shared/services/biblioteka/borrow.service";
import { BookCommentService } from "../../../shared/services/biblioteka/book-comment.service";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ButtonSpinnerModule } from 'src/app/shared/components/button-spinner/button-spinner.module';
import { EconomyRoutingModule } from './economy-routing.module';
import { EconomyComponent } from './economy.component';
import { PaymentListComponent } from './payment-list/payment-list.component';
import { PaymentService } from 'src/app/shared/services/economy/payment.service';
import { PaymentFormComponent } from './payment-form/payment-form.component';
import { UserService } from 'src/app/shared/services/account/user.service';

@NgModule({
  declarations: [
    EconomyComponent,
    PaymentListComponent,
    PaymentFormComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    EconomyRoutingModule,
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
    PaymentService,
    UserService
  ],
  entryComponents: [
  ]
})
export class EconomyModule {
}
