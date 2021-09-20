import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {BookService} from '../../../../../shared/services/biblioteka/book.service';
import {BookFormComponent} from '../book-form/book-form.component';
import {BookBorrowDTO} from '../../../../../shared/models/dto/BookBorrowDTO.model';
import {AuthorService} from '../../../../../shared/services/biblioteka/author.service';
import {AuthorModel} from '../../../../../shared/models/book/author.model';
import {TokenService} from 'src/app/shared/services/auth/token.service';
import {RolesEnum} from 'src/app/shared/models/enums/roles.enum';
import {BookCategoryEnum} from '../../../../../shared/models/enums/book-category.enum';
import {finalize} from 'rxjs/operators';
import {OnscrollService} from '../../../../../shared/services/shared/onscroll.service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {
  books: BookBorrowDTO[] = [];
  authors: AuthorModel[] = [];
  pageNumber = 0;
  loading = true;
  listView = false;
  currentUserRole: any;

  bibliotekaAdmin = RolesEnum.PG_BIBLIOTEKA;
  bibliotekaNd = RolesEnum.ND_BIBLIOTEKA;

  bookCategories = [BookCategoryEnum.AKROPOLI, BookCategoryEnum.EF, BookCategoryEnum.K, BookCategoryEnum.ANGLEZE,
    BookCategoryEnum.BIOGRAFI, BookCategoryEnum.FILOZOFIK, BookCategoryEnum.HISTORI, BookCategoryEnum.L_HUAJ, BookCategoryEnum.L_SHQIPE,
    BookCategoryEnum.MITOLOGJI, BookCategoryEnum.PSIKOLOGJIK]

  filterFormGroup: FormGroup;
  filterFormSubmitted = false;

  constructor(private dialog: MatDialog,
              private bookService: BookService,
              private authorService: AuthorService,
              private onScrollService: OnscrollService,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    this.initFilterForm();
    this.getBooks();
    this.getAuthors();
    this.listenOnScroll();
    this.currentUserRole = this.tokenService.getData().role.name;
  }

  initFilterForm() {
    this.filterFormGroup = new FormGroup({
      category: new FormControl(),
      authorId: new FormControl(),
      bookName: new FormControl()
    });
  }

  clearFilters() {
    this.initFilterForm();
    this.getBooks(false);
  }

  public getBooks(onScroll?: boolean) {
    this.loading = true

    if (onScroll === false) {
      this.pageNumber = 0;
    }

    let filter;

    if (this.filterFormSubmitted) {
      filter = this.filterFormGroup.value;
    }

    setTimeout(() => {
      this.bookService.getBooks(this.pageNumber, 32, filter)
        .pipe(finalize(() => {
          this.loading = false;
        }))
        .subscribe(
          result => {
            const resultBooks = this.mapResults(result);

            if (onScroll) {
              this.books = this.books.concat(...resultBooks);
              return;
            }
            this.books = resultBooks;
          },
          error => {
            console.log(error);
          }
        );
    }, 1000);
  }

  private getAuthors() {
    this.authorService.getAllAuthors().subscribe(
      result => {
        this.authors = result;
      },
      error => {
        console.log(error);
      }
    );
  }

  private listenOnScroll() {
    this.onScrollService.onScrollTrigger().subscribe(
      result=> {
        if (result) {
          this.pageNumber++;
          this.getBooks(true);
        }
      }
    )
  }

  createBook() {
    this.dialog.open(BookFormComponent);
  }

  toogleCheckbox() {
    this.listView = !this.listView;
  }

  filterBooks() {
    this.books = [];
    this.filterFormSubmitted = true;
    this.getBooks(false);
  }

  private mapResults(results: any) {
    return results.map((element) => {
      const bookBorrowDto = new BookBorrowDTO();
      bookBorrowDto.id = element[0];
      bookBorrowDto.name = element[1];
      bookBorrowDto.category = element[2];
      bookBorrowDto.publicationYear = element[3]
      bookBorrowDto.authorFirstName = element[4];
      bookBorrowDto.authorLastName = element[5];
      bookBorrowDto.authorId = element[6];
      bookBorrowDto.borrowStatus = element[7];

      return bookBorrowDto
    });
  }

}
