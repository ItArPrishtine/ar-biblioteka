import {environment} from '../../../environments/environment';

export const RequestUrls = {
  AUTHENTICATION: {
    LOGIN: environment.api + '/p1/authenticate'
  },

  ACCOUNT: {
    USERS: {
      GET_LIST: environment.api + '/account_user/list',
      CREATE: environment.api + '/account_user/create'
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
    CATEGORY: {
      BASE: environment.api + '/book_category'
    },
    EDITION: {
      BASE: environment.api + '/book_edition'
    },
    BORROW: {
      REQUEST: environment.api + '/book_borrow_request/'
    }
  },
};
