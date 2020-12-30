import {ChangeDetectorRef, Component} from '@angular/core';
import {UserService} from '../../../../shared/services/account/user.service';
import {UserCreateUpdateComponent} from '../user-create-update/user-create-update.component';
import {MatDialog} from '@angular/material/dialog';
import {DeleteFormComponent} from "../../shared/delete-form/delete-form.component";
import {AccountUserModel} from "../../../../shared/models/account/account-user.model";
import {CustomSnackbarService} from "../../../../shared/services/snackbar-service.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})

export class UserListComponent {
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'dateOfBirth', 'role', 'action'];
  dataSource: any;

  constructor(private userService: UserService,
              private dialog: MatDialog,
              private snackBarService: CustomSnackbarService
  ) {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getUsers().subscribe(
      result => {
        this.dataSource = result;
      }
    )
  }

  openCreateUserModal() {
    const dialogRef = this.dialog.open(UserCreateUpdateComponent);

    dialogRef.afterClosed().subscribe(() => this.getAllUsers());
  }

  deleteUser(user: AccountUserModel) {
    const dialogRef = this.dialog.open(DeleteFormComponent);

    dialogRef.componentInstance.elementToDelete = user.username;
    dialogRef.afterClosed().subscribe(
      result => {
        if (result === true) {
          this.deleteUserRequest(user);
        }
      }
    );
  }

  updateUser(user: AccountUserModel) {
    const dialogRef = this.dialog.open(UserCreateUpdateComponent);

    dialogRef.componentInstance.user = user;
  }

  deleteUserRequest(user: AccountUserModel) {
    this.userService.deleteUSer(user.id).subscribe(
      () => {
        this.snackBarService.success('Useri u fshi me sukses!');
        this.getAllUsers();
      },
      () => this.snackBarService.error('Ka ndodhur nje gabim gjate fshirjes se userit!')
    )
  }
}
