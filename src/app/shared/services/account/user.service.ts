import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RequestUrls } from "../../constants/RequestUrls";
import { LoginModel } from "../../models/auth/login.model";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get<any>(RequestUrls.ACCOUNT.USERS.GET_LIST);
  }

  createUser(user: any) {
    return this.http.post(RequestUrls.ACCOUNT.USERS.CREATE, user);
  }
}
