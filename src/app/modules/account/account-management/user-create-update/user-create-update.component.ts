import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AccountUserModel} from "../../../../shared/models/account/account-user.model";
import {UserService} from "../../../../shared/services/account/user.service";
import {RoleService} from "../../../../shared/services/account/role.service";
import {RoleModel} from "../../../../shared/models/account/role.model";

@Component({
  selector: 'app-user-create-update',
  templateUrl: './user-create-update.component.html',
  styleUrls: ['./user-create-update.component.scss']
})
export class UserCreateUpdateComponent implements OnInit {
  formGroup: FormGroup;
  roles: any[];
  user: AccountUserModel = new AccountUserModel();

  constructor(private userService: UserService,
              public dialogRef: MatDialogRef<UserCreateUpdateComponent>,
              public roleService: RoleService,
              @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    this.getRoles();
  }

  getRoles() {
    this.roleService.getRoles().subscribe(
      result => {
        this.roles = result;
        this.initForm();
      }
    );
  }

  initForm() {
    this.formGroup = new FormGroup({
      email: new FormControl(''),
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      role: new FormControl(''),
      dateOfBirth: new FormControl(''),
      description: new FormControl(''),
    });
  }

  createOrUpdateUser() {
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

    this.userService.createUser(this.user).subscribe(
      result => {
        this.closeDialog();
      }, error => {
        console.log(error);
    }
    )
  }

  closeDialog() {
    this.dialogRef.close(this.user);
  }

}
