import {ChangeDetectorRef, Component} from '@angular/core';
import {UserService} from '../../../../shared/services/account/user.service';
import {UserCreateUpdateComponent} from '../user-create-update/user-create-update.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})

export class UserListComponent {
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'dateOfBirth', 'role'];
  dataSource: any;

  constructor(private userService: UserService,
              private dialog: MatDialog
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

    dialogRef.afterClosed().subscribe(result => {
      this.getAllUsers();
    });
  }
}
