import {Component, Input, OnInit} from '@angular/core';
import {AuthorModel} from '../../../../../shared/models/book/author.model';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';
import {RouterUrls} from '../../../../../shared/constants/RouterUrls';
import {IMAGEURLS} from '../../../../../shared/constants/GeneralConstant';

@Component({
  selector: 'app-author-card',
  templateUrl: './author-card.component.html',
  styleUrls: ['./author-card.component.scss']
})
export class AuthorCardComponent implements OnInit {

  @Input() author: AuthorModel;
  detailsUrl = '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.AUTHORDETAILS;
  authorImage = IMAGEURLS.AUTHOR_AVATAR;

  constructor(private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    console.log(this.detailsUrl);
  }
}
