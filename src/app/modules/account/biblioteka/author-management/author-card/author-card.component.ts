import {Component, Input, OnInit} from '@angular/core';
import {AuthorModel} from '../../../../../shared/models/book/author.model';
import {IMAGEURLS} from '../../../../../shared/constants/GeneralConstant';
import {RouterUrls} from '../../../../../shared/constants/RouterUrls';

@Component({
  selector: 'app-author-card',
  templateUrl: './author-card.component.html',
  styleUrls: ['./author-card.component.scss']
})
export class AuthorCardComponent {
  @Input() author: AuthorModel;

  authorImage = IMAGEURLS.AUTHOR_AVATAR;
  detailsUrl = '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.AUTHORDETAILS;

  constructor() { }
}
