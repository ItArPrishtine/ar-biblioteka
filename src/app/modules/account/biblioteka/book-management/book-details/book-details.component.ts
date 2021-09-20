import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {BookService} from '../../../../../shared/services/biblioteka/book.service';
import {BookModel} from '../../../../../shared/models/book/book.model';
import {GeneralConstant, IMAGEURLS} from "../../../../../shared/constants/GeneralConstant";
import {RouterUrls} from "../../../../../shared/constants/RouterUrls";
import {BookFormComponent} from "../book-form/book-form.component";
import {BorrowComponent} from "../borrow/borrow.component";
import {TokenService} from "../../../../../shared/services/auth/token.service";
import {BorrowModel} from "../../../../../shared/models/book/borrow.model";
import {FormControl, FormGroup} from "@angular/forms";
import {BookCommentService} from "../../../../../shared/services/biblioteka/book-comment.service";
import {CustomSnackbarService} from "../../../../../shared/services/snackbar-service.service";
import {BookCommentModel} from "../../../../../shared/models/book/book-comment.model";
import {AccountUserModel} from "../../../../../shared/models/account/account-user.model";
import {BorrowService} from "../../../../../shared/services/biblioteka/borrow.service";
import { RolesEnum } from 'src/app/shared/models/enums/roles.enum';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {
  book: BookModel;
  bookImage = IMAGEURLS.BOOK_TEST;
  commentedAvatar = IMAGEURLS.AUTHOR_AVATAR;
  authorDetailsUrl = '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.AUTHORDETAILS;
  thisUserBorrowedThisBook: boolean;
  currentUserName: string;
  currentUserId: number;
  formGroup: FormGroup;
  comments: BookCommentModel[] = [];
  borrowedBook: BorrowModel;
  bibliotekaAdmin = RolesEnum.PG_BIBLIOTEKA;
  bibliotekaNd = RolesEnum.ND_BIBLIOTEKA;
  currentUserRole: any;
  dateFormat = GeneralConstant.DATEFORMAT;

  constructor(private activeRoute: ActivatedRoute,
              private dialog: MatDialog,
              private router: Router,
              private tokenService: TokenService,
              private commentService: BookCommentService,
              private snackBar: CustomSnackbarService,
              private borrowService: BorrowService,
              private bookService: BookService) { }

  ngOnInit(): void {
    this.getBookDetails();
    this.initCommentForm();
    this.currentUserName = this.tokenService.getData().username;
    this.currentUserId = this.tokenService.getData().id;
    this.currentUserRole = this.tokenService.getData().role.name;
  }

  getAllComments() {
    this.commentService.getByBookId(this.book.id).subscribe(
      result => {
        this.comments = result;
      }, error => this.snackBar.error('Gabim gjate marrjes se komenteve')
    )
  }

  initCommentForm() {
    this.formGroup = new FormGroup({
      comment: new FormControl('')
    })
  }

  submitComment() {
    const comment = this.formGroup.value.comment;
    if (!comment) {
      return;
    }
    const bookComment = new BookCommentModel();
    bookComment.comment = comment;
    bookComment.book = this.book;
    bookComment.applicationUser = new AccountUserModel();
    bookComment.applicationUser.id = this.tokenService.getData().id;

    this.commentService.addComment(bookComment).subscribe(
      result => {
        this.comments.push(result);
        this.initCommentForm();
      },
      error => this.snackBar.error('Gabim gjate shtimit te komentit!'),
    )
  }

  deleteComment(commentId: number) {
    this.commentService.deleteComment(commentId).subscribe(
      result => {
        this.comments = this.comments.filter(item => item.id != commentId)
      }
    )
  }

  private getBorrowedOfUser() {
    const borrowModel = new BorrowModel();
    borrowModel.book = this.book;

    this.borrowService.userBorrowExist(borrowModel)
      .subscribe(
        result => {
          this.borrowedBook = result;

          if (!result) {
            this.thisUserBorrowedThisBook = false;
            return;
          }
          this.thisUserBorrowedThisBook = this.borrowedBook.applicationUser.id === this.currentUserId;
        }, error => {
          this.snackBar.error('Gabim gjate validimit nese libri eshte i huazuar!');
        });
  }

  getBookDetails() {
    const id = this.activeRoute.snapshot.params.id;
    this.bookService.getBookById(id).subscribe(result => {
      this.book = result;
      this.getBorrowedOfUser();
      this.getAllComments();
    });
  }

  updateBook() {
    const dialogRef = this.dialog.open(BookFormComponent);
    dialogRef.componentInstance.bookId = this.book.id.toString();

    dialogRef.afterClosed().subscribe(
      result => {
        if (result) {
          this.book = result;
        }
      }
    );
  }

  deleteBook() {
    this.bookService.deleteBook(this.book.id.toString()).subscribe(
      result => {
        this.router.navigateByUrl(RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE);
      }
    );
  }

  borrowRequest() {
    const dialogRef = this.dialog.open(BorrowComponent);
    dialogRef.componentInstance.book = this.book;
    dialogRef.afterClosed().subscribe(
      result => {
        this.getBorrowedOfUser();
      }
    )
  }

  returnBook() {
    const dialogRef = this.dialog.open(BorrowComponent);
    dialogRef.componentInstance.book = this.book;
    dialogRef.componentInstance.returnBook = true;
    dialogRef.afterClosed().subscribe(
      result => {
        this.getBorrowedOfUser();
      }
    )
  }

  extendDeadline() {
    this.borrowService.extendDeadline(this.borrowedBook).subscribe(
      () => {
        this.borrowedBook.extendedDeadline = true;
        this.snackBar.success("Deadline u shty me sukses");
      },
      () => this.snackBar.error("Deadline nuk mund te shtyhet")
    )
  }

}
