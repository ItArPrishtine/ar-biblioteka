import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthorModel} from "../../../../../shared/models/book/author.model";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AuthorService} from "../../../../../shared/services/biblioteka/author.service";
import {BookService} from "../../../../../shared/services/biblioteka/book.service";
import {BookModel} from "../../../../../shared/models/book/book.model";
import {EditionModel} from "../../../../../shared/models/book/edition.model";
import {finalize} from "rxjs/operators";
import {CustomSnackbarService} from "../../../../../shared/services/snackbar-service.service";

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent implements OnInit {
  formGroup: FormGroup;
  book: BookModel = new BookModel();
  bookId: string;
  editions = [];
  authors: AuthorModel[] = [];
  loading = false;

  constructor(public dialogRef: MatDialogRef<BookFormComponent>,
              public bookService: BookService,
              public authorService: AuthorService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private snackBarService: CustomSnackbarService
  ) {
  }

  ngOnInit(): void {
    this.getEditionsAndAuthors();
  }

  getAuthorsAndBookDetails() {
    this.authorService.getAllAuthors().subscribe(result => {
      this.authors = result;
      if (!this.bookId) {
        this.initForm();
        return;
      }
      this.getBookDetails();
    });
  }

  getBookDetails() {
    if (this.bookId) {
      this.bookService.getBookById(this.bookId).subscribe(result => {
        this.book = result;
        this.initForm();
      });
    }
  }

  getEditionsAndAuthors() {
    this.bookService.getEditions().subscribe(
      result => {
        this.editions = result;
        this.getAuthorsAndBookDetails();
      },
      error => {
        console.log(error);
      }
    )
  }

  initForm() {
    this.formGroup = new FormGroup({
      name: new FormControl(this.book ? this.book.name : ''),
      description: new FormControl(this.book ? this.book.description : ''),
      publicationYear: new FormControl(this.book ? this.book.publicationYear : ''),
      shelf: new FormControl(this.book ? this.book.shelf : ''),
      category: new FormControl(this.book && this.book.category ? this.book.category : ''),
      edition: new FormControl(this.book && this.book.edition ? this.book.edition.id.toString() : ''),
      author: new FormControl(this.book && this.book.author ? this.book.author.id.toString() : ''),
    });
  }

  createOrUpdateBook() {
    this.loading = true;
    const bookModel = new BookModel();

    if (!this.formGroup.valid) {
      return;
    }

    const values = this.formGroup.value;

    bookModel.name = values.name;
    bookModel.description = values.description;
    bookModel.category = values.category;
    bookModel.publicationYear = values.publicationYear;
    bookModel.shelf = values.shelf;
    bookModel.edition = new EditionModel();
    bookModel.edition.id = values.edition;
    bookModel.author = new AuthorModel();
    bookModel.author.id = values.author;

    if (this.bookId) {
      bookModel.id = parseInt(this.bookId);

      this.bookService.updateBook(bookModel)
        .pipe(finalize(() => this.loading = false))
        .subscribe(
          result => {
            this.book = result;
            this.snackBarService.success("Libri u ndryshua me sukses");
            this.closeDialog()
          },
          error => {
            this.snackBarService.error("Gabim gjate ndryshimit te librit");
          }
        );
    } else {
      this.bookService.createBook(bookModel)
        .pipe(finalize(() => this.loading = false))
        .subscribe(
          result => {
            this.book = result;
            this.snackBarService.success("Libri u shtua me sukses");
          },
          error => {
            this.snackBarService.error("Gabim gjate shtimit te librit");
          }
        );
    }
  }

  closeDialog() {
    this.dialogRef.close(this.book);
  }
}
