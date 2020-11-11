import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {AuthorFormComponent} from '../author-form/author-form.component';
import {AuthorService} from "../../../../../shared/services/biblioteka/author.service";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.scss']
})
export class AuthorListComponent implements OnInit {
  authors;

  constructor(private dialog: MatDialog,
              private authorService: AuthorService) { }

  ngOnInit(): void {
    this.getAuthors();
  }

  getAuthors() {
    this.authorService.getAuthors().subscribe(
      result => {
        this.authors = result;
      },
      error => {
        console.log(error);
      }
    )
  }

  createAuthor() {
    this.dialog.open(AuthorFormComponent);
  }

}
