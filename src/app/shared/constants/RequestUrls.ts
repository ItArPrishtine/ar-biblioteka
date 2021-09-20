import {environment} from '../../../environments/environment';

export const RequestUrls = {
  AUTHENTICATION: {
    LOGIN: environment.api + '/p1/authenticate'
  },

  ACCOUNT: {
    USERS: {
      BASE_API: environment.api + '/account_user/',
      CHANGE_PASSWORD: environment.api + '/account_user/change_password',
    },
    ROLES: {
      GET_LIST: environment.api + '/account_role/list',
    }
  },

  BOOK: {
    AUTHOR: {
      GET_LIST: environment.api + '/book_author',
      GET_PAGING_LIST: environment.api + '/book_author/list',
      CREATE: environment.api + '/book_author/create',
      UPDATE: environment.api + '/book_author/update',
      DELETE: environment.api + '/book_author/delete',
      DETAILS: environment.api + '/book_author/details',
    },
    BOOK: {
      BASE: environment.api + '/book_book/',
      BY_AUTHOR: environment.api + '/book_book/author',
    },
    COMMENT: {
      BASE: environment.api + '/book_comment/',
      BY_BOOK: environment.api + '/book_comment/book',
    },
    EDITION: {
      BASE: environment.api + '/book_edition'
    },
    BORROW: {
      REQUEST: environment.api + '/book_borrow_request/',
      CANCEL: environment.api + '/book_borrow_request/cancel',
      REJECT: environment.api + '/book_borrow_request/reject',
      CANCEL_NEW_REQUEST: environment.api + '/book_borrow_request/cancel/new_request',
      BORROW: environment.api + '/book_borrow/',
      RETURN: environment.api + '/book_borrow/return',
      BORROW_USER_EXIST: environment.api + '/book_borrow/user_borrow_exist',
      EXTEND_DEADLINE: environment.api + '/book_borrow/extend-deadline',
    }
  },
  ECONOMY: {
    PAYMENT: {
      BASE: environment.api + '/economy_payment/',
      VERIFY: environment.api + '/economy_payment/verify'
    }
  }
};
