import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../../shared/services/auth/authentication.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginModel} from "../../../shared/models/auth/login.model";
import {TokenService} from "../../../shared/services/auth/token.service";
import {Router} from "@angular/router";
import {RouterUrls} from "../../../shared/constants/RouterUrls";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  formGroup: FormGroup;

  constructor(private authService: AuthenticationService,
              private router: Router,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.formGroup = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    })
  }

  login() {
    if (this.formGroup.invalid) {
      return;
    }

    const loginModel: LoginModel = {
      username: this.formGroup.get('username').value,
      password: this.formGroup.get('password').value,
    };

    this.authService.authenticate(loginModel).subscribe(result => {
      this.tokenService.saveToken(result.token);
      this.router.navigateByUrl('/' + RouterUrls.ACCOUNT.BASE_MODULE);
    });
  }

}
