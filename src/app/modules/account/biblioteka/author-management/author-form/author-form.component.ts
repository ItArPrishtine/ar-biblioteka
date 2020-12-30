import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {AuthorService} from '../../../../../shared/services/biblioteka/author.service';
import {AuthorModel} from '../../../../../shared/models/book/author.model';
import {finalize} from "rxjs/operators";
import {CustomSnackbarService} from "../../../../../shared/services/snackbar-service.service";

@Component({
  selector: 'app-author-form',
  templateUrl: './author-form.component.html',
  styleUrls: ['./author-form.component.scss']
})
export class AuthorFormComponent implements OnInit {
  formGroup: FormGroup;
  imageSrc: string;
  imageFile: any;
  author: any;
  authorId: string;
  loading = false;

  constructor(public dialogRef: MatDialogRef<AuthorFormComponent>,
              public authorService: AuthorService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private snackBarService: CustomSnackbarService) {
  }

  ngOnInit(): void {
    if (this.authorId) {
      this.getAuthorDetails();
    } else {
      this.initForm();
    }
  }

  getAuthorDetails() {
    if (this.authorId) {
      this.authorService.getAuthorById(this.authorId).subscribe(result => {
        this.author = result;
        this.initForm();
      });
    }
  }

  initForm() {
    this.formGroup = new FormGroup({
      firstName: new FormControl(this.author ? this.author.firstName : ''),
      lastName: new FormControl(this.author ? this.author.lastName : ''),
      dateOfBirth: new FormControl(this.author ? this.author.dateOfBirth : ''),
      dateOfDeath: new FormControl(this.author ? this.author.dateOfDeath : ''),
      description: new FormControl(this.author ? this.author.description : ''),
      file: new FormControl(''),
    });

    this.imageSrc = this.author ? this.author.imageUrl : '';
  }

  createOrUpdateAuthor() {
    const formData: FormData = new FormData();
    const authorModel = new AuthorModel();
    this.loading = true;

    if (!this.formGroup.valid) {
      return;
    }

    const values = this.formGroup.value;

    authorModel.firstName = values.firstName;
    authorModel.lastName = values.lastName;
    authorModel.dateOfBirth = values.dateOfBirth;
    authorModel.dateOfDeath = values.dateOfDeath;
    authorModel.description = values.description;

    formData.append('file', this.imageFile);

    if (this.authorId) {
      authorModel.id = parseInt(this.authorId);

      formData.append('author', JSON.stringify(authorModel));

      this.authorService.updateAuthor(formData)
        .pipe(finalize(() => this.loading = false))
        .subscribe(
          (result) => {
            this.author = result;
            this.closeDialog();
            this.snackBarService.success('Autori u editua me sukses');
          },
          () => {
            this.snackBarService.success('Gabim gjate editimit te autorit');
          }
        );
    } else {
      formData.append('author', JSON.stringify(authorModel));

      this.authorService.createAuthor(formData)
        .pipe(finalize(() => this.loading = false))
        .subscribe(
          () => {
            this.closeDialog();
            this.snackBarService.success('Autori u krijua me sukses');
          },
          () => {
            this.snackBarService.success('Gabim gjate krijimit te autorit');
          }
        );
    }
  }

  closeDialog() {
    this.dialogRef.close(this.author);
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

  get f() {
    return this.formGroup.controls;
  }

}
