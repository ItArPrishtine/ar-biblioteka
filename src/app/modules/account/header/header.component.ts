import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {RouterUrls} from '../../../shared/constants/RouterUrls';
import {IMAGEURLS} from '../../../shared/constants/GeneralConstant';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  logoUrl = IMAGEURLS.LOGO;
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  logOut() {
    localStorage.clear();
    this.router.navigateByUrl(RouterUrls.AUTHENTICATE.BASE_MODULE);
  }

}
