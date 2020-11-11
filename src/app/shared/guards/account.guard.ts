import {CanActivate, Router} from "@angular/router";
import {TokenService} from "../services/auth/token.service";
import {RouterUrls} from "../constants/RouterUrls";
import {Injectable} from "@angular/core";

@Injectable()
export class AccountGuard implements CanActivate {
  constructor(private tokenService: TokenService,
              private router: Router) { }

  canActivate() {
    if (this.tokenService.isTokenExpired()) {
        return true;
    }

    this.router.navigateByUrl('/' + RouterUrls.AUTHENTICATE.BASE_MODULE);
    return false;
  }
}
