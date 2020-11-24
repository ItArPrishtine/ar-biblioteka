import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AuthorService} from "../../../../../shared/services/biblioteka/author.service";
import {AuthorModel} from "../../../../../shared/models/book/author.model";
import {AuthorFormComponent} from "../author-form/author-form.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.scss']
})
export class AuthorDetailsComponent implements OnInit {
  author: AuthorModel;

  constructor(private activeRoute: ActivatedRoute,
              private dialog: MatDialog,
              private authorService: AuthorService) { }

  ngOnInit(): void {
    this.getauthorDetails();
  }

  getauthorDetails() {
    const id = this.activeRoute.snapshot.params.id;
    this.authorService.getAuthorById(id).subscribe(result => {
      this.author = result;
    });
  }

  updateAuthor() {
    const dialogRef = this.dialog.open(AuthorFormComponent);
    dialogRef.componentInstance.authorId = this.author.id.toString();
  }

}
