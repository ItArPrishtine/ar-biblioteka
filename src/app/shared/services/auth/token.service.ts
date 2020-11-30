import jwt_decode from 'jwt-decode';
import {Injectable} from '@angular/core';
import {GeneralConstant} from '../../constants/GeneralConstant';

@Injectable()
export class TokenService {

  constructor() { }

  public saveToken(token): void {
    localStorage.setItem(GeneralConstant.LOCALSTORAGE_TOKEN, token);
  }

  public geToken(): string {
    return localStorage.getItem(GeneralConstant.LOCALSTORAGE_TOKEN);
  }

  public getData(): any {
    const token = this.geToken();
    return jwt_decode(token);
  }

  public isTokenExpired() {
    if (!this.geToken()) {
      return;
    }
    const expirationDate = new Date(this.getData().exp);
    const currentDate = new Date();

    return currentDate > expirationDate;
  }
}
