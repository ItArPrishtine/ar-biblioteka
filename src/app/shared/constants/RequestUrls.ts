import {environment} from "../../../environments/environment";

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
      GET_LIST: environment.api + '/book_author/list',
      CREATE: environment.api + '/book_author/create',
      UPDATE: environment.api + '/book_author/update',
      DETAILS: environment.api + '/book_author/details',
    }
  },
};
