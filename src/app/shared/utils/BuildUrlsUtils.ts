import {RouterUrls} from '../constants/RouterUrls';

export default class BuildUrlsUtils {

  public static authorDetailsUrl(authorId: string | number) {
    return '/' + RouterUrls.ACCOUNT.BASE_MODULE +  '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.AUTHORDETAILS + '/' + authorId;
  }

  public static bookDetailsUrl(bookId: string | number) {
    return '/' + RouterUrls.ACCOUNT.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BASE_MODULE + '/' + RouterUrls.BIBLIOTEKA.BOOK_DETAILS + '/' + bookId;
  }
}
