import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {AccountUserModel} from '../../../../shared/models/account/account-user.model';
import {UserService} from '../../../../shared/services/account/user.service';
import {RoleService} from '../../../../shared/services/account/role.service';
import {RoleModel} from '../../../../shared/models/account/role.model';
import {finalize} from "rxjs/operators";
import {CustomSnackbarService} from "../../../../shared/services/snackbar-service.service";

@Component({
  selector: 'app-user-create-update',
  templateUrl: './user-create-update.component.html',
  styleUrls: ['./user-create-update.component.scss']
})
export class UserCreateUpdateComponent implements OnInit {
  formGroup: FormGroup;
  roles: any[];
  user: AccountUserModel = new AccountUserModel();
  loading = false;
  maxBirthdayDate = new Date();

  constructor(private userService: UserService,
              public dialogRef: MatDialogRef<UserCreateUpdateComponent>,
              public roleService: RoleService,
              public snackBarService: CustomSnackbarService,
              @Inject(MAT_DIALOG_DATA) public data: any
  ) {
  }

  ngOnInit(): void {
    this.getRoles();
  }

  getRoles() {
    this.roleService.getRoles()
      .pipe(
        finalize(() => this.loading = false)
      )
      .subscribe(
        result => {
          this.roles = result;
          this.initForm();
        },
        error => this.snackBarService.error("Ky username ose email ekziston")
      );
  }

  initForm() {
    this.formGroup = new FormGroup({
      email: new FormControl(this.user ? this.user.email : ''),
      firstName: new FormControl(this.user ? this.user.firstName : ''),
      lastName: new FormControl(this.user ? this.user.lastName : ''),
      role: new FormControl(this.user && this.user.role ? this.user.role.id.toString() : ''),
      dateOfBirth: new FormControl(this.user ? this.user.dateOfBirth : ''),
      description: new FormControl(this.user ? this.user.description : ''),
    });
  }

  createOrUpdateUser() {
    this.loading = true;
    if (!this.formGroup.valid) {
      return;
    }

    const values = this.formGroup.value;
    const role: RoleModel = new RoleModel();
    role.id = values.role;

    this.user.email = values.email;
    this.user.firstName = values.firstName;
    this.user.lastName = values.lastName;
    this.user.dateOfBirth = values.dateOfBirth;
    this.user.description = values.description;
    this.user.role = role;

    if (!this.user.id) {
      this.userService.createUser(this.user)
        .pipe(finalize(() => this.loading = false))
        .subscribe(
          () => {
            this.closeDialog();
            this.snackBarService.success("Useri u shtua me sukses");
          },
          () => {
            this.snackBarService.error("Gabim gjate krijimit");
          }
        )
    } else {
      this.userService.updateUser(this.user)
        .pipe(finalize(() => this.loading = false))
        .subscribe(
          () => {
            this.closeDialog();
            this.snackBarService.success("Useri u ndryshua me sukses");
          },
          () => {
            this.snackBarService.error("Gabim gjate ndryshimit te te dhenave");
          }
        )
    }
  }

  closeDialog() {
    this.dialogRef.close(this.user);
  }

}
