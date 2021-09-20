import {Component, OnInit, ViewChild} from '@angular/core';
import SignaturePad from 'signature_pad';
import {UserService} from '../../../../shared/services/account/user.service';
import {TokenService} from '../../../../shared/services/auth/token.service';
import {AccountUserModel} from '../../../../shared/models/account/account-user.model';
import {CustomSnackbarService} from '../../../../shared/services/snackbar-service.service';
import {GeneralConstant} from '../../../../shared/constants/GeneralConstant';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {
  @ViewChild('signaturePad') private signaturePad: SignaturePad;
  url: string;
  user: AccountUserModel;
  file: File;
  dateFormat = GeneralConstant.DATEFORMAT;

  signaturePadOptions: any = {
    'minWidth': 2,
    'canvasWidth': 500,
    'canvasHeight': 300
  };

  constructor(private userService: UserService,
              private tokenService: TokenService,
              private snackBarService: CustomSnackbarService) { }

  ngOnInit(): void {
    this.getUserDetails();
  }

  getUserDetails() {
    this.userService.getUserById(this.tokenService.getData().id).subscribe(
      result => {
        this.user = result
      }, error => {
        this.snackBarService.error('Gabim gjate marrjes se detajeve');
    })
  }

  saveUser() {
    const formData: FormData = new FormData();
    formData.append("id", this.tokenService.getData().id);
    formData.append("file", this.file);

    this.userService.updateUserESign(formData).subscribe(
      result => {
        this.user = result;
      }
    )
  }

  drawComplete() {
    this.url = this.signaturePad.toDataURL();
    this.file = this.dataURLtoFile(this.url,`esign-${this.tokenService.getData().id}`);
  }

  dataURLtoFile(dataurl, filename) {
    let arr = dataurl.split(','),
      mime = arr[0].match(/:(.*?);/)[1],
      bstr = atob(arr[1]),
      n = bstr.length,
      u8arr = new Uint8Array(n);

    while(n--){
      u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], filename, {type:mime});
  }
}
