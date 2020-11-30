import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RequestUrls } from '../../constants/RequestUrls';

@Injectable()
export class RoleService {

  constructor(private http: HttpClient) { }

  getRoles() {
    return this.http.get<any>(RequestUrls.ACCOUNT.ROLES.GET_LIST);
  }
}
