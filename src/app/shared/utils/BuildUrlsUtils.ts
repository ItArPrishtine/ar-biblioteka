import {RouterUrls} from '../constants/RouterUrls';

export default class BuildUrlsUtils {

  public static authorDetailsUrl(authorId: string | number) {
    return '/' + RouterUrls.ACCOUNT.BASE_MODULE +  '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.AUTHORDETAILS + '/' + authorId;
  }

  public static bookDetailsUrl(bookId: string | number) {
    return '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BOOK_DETAILS + '/' + bookId;
  }

  public static paymentDetailsUrl(paymentId: string | number) {
    return '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.ECONOMY.BASE_MODULE + '/' + RouterUrls.ECONOMY.PAYMENT + '/' + paymentId;
  }

  public static verificationUrl(paymentId: string | number) {
    return '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.ECONOMY.BASE_MODULE + '/' + RouterUrls.ECONOMY.PAYMENT_VERIFY + '/' + paymentId;
  }
}
