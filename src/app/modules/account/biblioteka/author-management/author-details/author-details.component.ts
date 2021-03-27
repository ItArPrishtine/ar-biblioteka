import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthorService} from '../../../../../shared/services/biblioteka/author.service';
import {AuthorModel} from '../../../../../shared/models/book/author.model';
import {AuthorFormComponent} from '../author-form/author-form.component';
import {MatDialog} from '@angular/material/dialog';
import {IMAGEURLS} from '../../../../../shared/constants/GeneralConstant';
import {DeleteFormComponent} from '../../../shared/delete-form/delete-form.component';
import {RouterUrls} from '../../../../../shared/constants/RouterUrls';
import { RolesEnum } from 'src/app/shared/models/enums/roles.enum';
import { TokenService } from 'src/app/shared/services/auth/token.service';

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.scss']
})
export class AuthorDetailsComponent implements OnInit {
  author: AuthorModel;
  books: any[];
  avatarImage = IMAGEURLS.AUTHOR_AVATAR;
  bookImage = IMAGEURLS.BOOK;
  booksUrl = '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BOOK_DETAILS;
  currentUserRole: any;
  bibliotekaAdmin = RolesEnum.PG_BIBLIOTEKA;
  bibliotekaNd = RolesEnum.ND_BIBLIOTEKA;

  constructor(private activeRoute: ActivatedRoute,
              private dialog: MatDialog,
              private router: Router,
              private authorService: AuthorService,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    this.getauthorDetails();
    this.currentUserRole = this.tokenService.getData().role.name;
  }

  getauthorDetails() {
    const id = this.activeRoute.snapshot.params.id;
    this.authorService.getAuthorById(id).subscribe(result => {
      this.author = result;
      this.getAuthorBooks();
    });
  }

  deleteAuthor() {
    const dialogRef = this.dialog.open(DeleteFormComponent);
    dialogRef.componentInstance.elementToDelete = 'Author';
    dialogRef.afterClosed().subscribe(
      result => {
        if (result === true) {
          this.deleteThisAuthor();
        }
      }
    );
  }

  updateAuthor() {
    const dialogRef = this.dialog.open(AuthorFormComponent);
    dialogRef.componentInstance.authorId = this.author.id.toString();

    dialogRef.afterClosed().subscribe(
      result => {
        if (result) {
          this.author = result;
        }
      }
    );
  }

  deleteThisAuthor() {
    this.authorService.deleteAuthor(this.author.id.toString()).subscribe(
      result => {
        this.router.navigateByUrl(RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.AUTHOR);
      }
    );
  }

  getAuthorBooks() {
    this.authorService.getAuthorBooks(this.author.id.toString()).subscribe(
      result => {
        this.books = result;
      }
    );
  }
}
