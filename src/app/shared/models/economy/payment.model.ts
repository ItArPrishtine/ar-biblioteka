import { AccountUserModel } from "../account/account-user.model";
import { BaseModel } from "../base.model";
import {PaymentTypeEnum} from '../enums/payment-type.enum';

export class PaymentModel extends BaseModel {
  applicationUser: AccountUserModel;
  paymentDate: string;
  payedMonth: string;
  payedYear: string;
  paymentType: PaymentTypeEnum;
  price: number;
  description: string;
  verifiedFromUser: boolean;
  verifiedFromEconomy: boolean;
}
