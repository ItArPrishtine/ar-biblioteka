import {CanActivate, Router} from '@angular/router';
import {TokenService} from '../services/auth/token.service';
import {RouterUrls} from '../constants/RouterUrls';
import {Injectable} from '@angular/core';

@Injectable()
export class AuthenticateGuard implements CanActivate {
  constructor(private tokenService: TokenService,
              private router: Router) { }

  canActivate() {
    debugger;
    if (!this.tokenService.isTokenExpired()) {
        return true;
    }

    this.router.navigateByUrl('/' + RouterUrls.ACCOUNT.BASE_MODULE);
    return false;
  }
}
