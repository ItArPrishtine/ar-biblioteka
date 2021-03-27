import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouterUrls } from 'src/app/shared/constants/RouterUrls';
import { RolesEnum } from 'src/app/shared/models/enums/roles.enum';
import { RoleService } from 'src/app/shared/services/account/role.service';
import { TokenService } from 'src/app/shared/services/auth/token.service';

@Component({
    template: ''
})
export class RedirectComponent implements OnInit {

    userRoles = RolesEnum;
    currentRole: any;

    constructor(private tokenService: TokenService, private router: Router) { }

    ngOnInit(): void {
        this.currentRole = this.tokenService.getData().role;
        this.redirectBasedOnRole();
    }

    redirectBasedOnRole() {
        const baseurl = '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/';
        const manageUrl = baseurl + RouterUrls.ACCOUNT.MANAGE;
        const bibliotekaUrl = baseurl + RouterUrls.BIBLIOTEKA.BASE_MODULE;

        switch(this.currentRole.name) {
            case this.userRoles.KF:
                this.router.navigateByUrl(manageUrl);
                break;
            case this.userRoles.PGS_AKSI:
                this.router.navigateByUrl(manageUrl);
                break;
            case this.userRoles.PGS_PENDA:
                this.router.navigateByUrl(manageUrl);
                break;
            case this.userRoles.PGS_PISHTARI:
                this.router.navigateByUrl(manageUrl);
                break;
            case this.userRoles.PG_IT:
                this.router.navigateByUrl(manageUrl);
                break;
            default:
                this.router.navigateByUrl(bibliotekaUrl);
                break;
        }
    }

}
