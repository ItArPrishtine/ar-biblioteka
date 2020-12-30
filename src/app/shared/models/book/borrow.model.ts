import {BaseModel} from '../base.model';
import {BorrowRequestModel} from "./borrow-request.model";

export class BorrowModel extends BaseModel {
  borrowUntil: Date;
  borrowRequest: BorrowRequestModel;
}
