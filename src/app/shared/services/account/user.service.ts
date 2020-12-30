import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';
import { LoginModel } from '../../models/auth/login.model';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get<any>(RequestUrls.ACCOUNT.USERS.BASE_API);
  }

  createUser(user: any) {
    return this.http.post(RequestUrls.ACCOUNT.USERS.BASE_API, user);
  }

  updateUser(user: any) {
    return this.http.put(RequestUrls.ACCOUNT.USERS.BASE_API, user);
  }

  deleteUSer(userId: any) {
    return this.http.delete(RequestUrls.ACCOUNT.USERS.BASE_API + `/${userId}`);
  }

  changePassword(passwordModel: any) {
    return this.http.post(RequestUrls.ACCOUNT.USERS.CHANGE_PASSWORD, passwordModel);
  }
}
