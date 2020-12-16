import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthorModel} from "../../../../../shared/models/book/author.model";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AuthorService} from "../../../../../shared/services/biblioteka/author.service";
import {BookService} from "../../../../../shared/services/biblioteka/book.service";
import {BookModel} from "../../../../../shared/models/book/book.model";
import {EditionModel} from "../../../../../shared/models/book/edition.model";

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent implements OnInit {
  formGroup: FormGroup;
  imageSrc: string;
  imageFile: any;
  book: BookModel = new BookModel();
  bookId: string;
  editions = [];
  authors: AuthorModel[] = [];

  constructor(public dialogRef: MatDialogRef<BookFormComponent>,
              public bookService: BookService,
              public authorService: AuthorService,
              @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    this.getEditionsAndAuthors();
  }

  getAuthorsAndBookDetails() {
    this.authorService.getAllAuthors().subscribe(result => {
      this.authors = result;
      if (!this.book.id) {
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
      file: new FormControl(''),
      name: new FormControl(this.book ? this.book.name : ''),
      description: new FormControl(this.book ? this.book.description : ''),
      publicationYear: new FormControl(this.book ? this.book.publicationYear : ''),
      category: new FormControl(this.book && this.book.category ? this.book.category  : ''),
      edition: new FormControl(this.book && this.book.edition ? this.book.edition.id.toString() : ''),
      author: new FormControl(this.book && this.book.author ? this.book.author.id.toString()  : ''),
    });

    this.imageSrc = this.book ? this.book.imageUrl : '';
  }

  createOrUpdateBook() {
    const formData: FormData = new FormData();
    const bookModel = new BookModel();

    if (!this.formGroup.valid) {
      return;
    }

    const values = this.formGroup.value;

    // authorModel.description = values.description;

    formData.append('file', this.imageFile);

    bookModel.name = values.name;
    bookModel.description = values.description;
    bookModel.category = values.category;
    bookModel.publicationYear = values.publicationYear;
    bookModel.edition = new EditionModel();
    bookModel.edition.id = values.edition;
    bookModel.author = new AuthorModel();
    bookModel.author.id = values.author;

    if (this.bookId) {
      bookModel.id = parseInt(this.bookId);

      formData.append('book', JSON.stringify(bookModel));

      this.bookService.updateBook(formData).subscribe(
        result => this.closeDialog(),
        error => console.log(error)
      );
    } else {
      formData.append('book', JSON.stringify(bookModel));
      this.bookService.createBook(formData).subscribe(
        result => this.closeDialog(),
        error => console.log(error)
      );
    }
  }

  closeDialog() {
    this.dialogRef.close();
  }

  onFileChange(event) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);
      this.imageFile = file;

      reader.onload = () => {
        this.imageSrc = reader.result as string;
      };
    }
  }

  get f(){
    return this.formGroup.controls;
  }

}
