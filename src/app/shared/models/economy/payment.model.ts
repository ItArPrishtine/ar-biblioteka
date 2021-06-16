import { AccountUserModel } from "../account/account-user.model";
import { BaseModel } from "../base.model";

export class PaymentModel extends BaseModel {
    applicationUser: AccountUserModel;
    paymentDate: string;
    payedMonth: string;
    price: number;
    description: string;
}