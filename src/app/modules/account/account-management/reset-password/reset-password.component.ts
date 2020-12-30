import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../../shared/services/account/user.service";
import {finalize} from "rxjs/operators";
import {CustomSnackbarService} from "../../../../shared/services/snackbar-service.service";
import {TokenService} from "../../../../shared/services/auth/token.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {
  formGroup: FormGroup;
  loading = false;

  constructor(private userService: UserService,
              private tokenService: TokenService,
              private snackBarService: CustomSnackbarService) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.formGroup = new FormGroup({
      oldPassword: new FormControl('', Validators.required),
      newPassword: new FormControl('', Validators.required),
      confirmPassword: new FormControl('', Validators.required),
    });
  }

  resetPassword() {
    const userId = this.tokenService.getData().id;
    const formValue = this.formGroup.value;

    if (!this.formGroup.valid) {
      return;
    }

    const model = {
      id: userId,
      oldPassword: formValue.oldPassword,
      newPassword: formValue.newPassword
    }

    if (formValue.newPassword != formValue.confirmPassword) {
      this.snackBarService.info('Passwordin juaj i ri nuk perputhet me paswordin e konfirmuar !');
      return;
    }

    this.userService.changePassword(model)
      .pipe(finalize(() => this.loading = false))
      .subscribe(
        () => {
          this.snackBarService.success('Passwordi u ndryshua me sukses')
        },
        () => {
          this.snackBarService.error('Gabim gjate ndryshimit te passwordit')
        })
  }
}
