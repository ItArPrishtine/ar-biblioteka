import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {RouterUrls} from "../../../shared/constants/RouterUrls";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  logOut() {
    localStorage.clear();
    this.router.navigateByUrl(RouterUrls.AUTHENTICATE.BASE_MODULE);
  }

}
