import {BaseModel} from '../base.model';
import {AccountUserModel} from "../account/account-user.model";
import {BookModel} from "./book.model";
import {BorrowRequestStatusEnum} from "../enums/BorrowRequestStatus.enum";

export class BorrowRequestModel extends BaseModel {
  borrowFrom: Date;
  applicationUser: AccountUserModel;
  book: BookModel;
  borrowRequestStatus: BorrowRequestStatusEnum;
}
