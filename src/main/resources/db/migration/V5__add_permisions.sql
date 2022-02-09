-- USER
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Lexoje perdorues',  '/api/account_user/read', 'READ_USERS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Krijoj perdorues',  '/api/account_user/create', 'CREATE_USERS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Ndryshoje perdorues',  '/api/account_user/update', 'UPDATE_USERS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Fshij perdorues',  '/api/account_user/delete', 'DELETE_USERS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Ndryshoj fjalekalim',  '/api/account_user/change_password', 'CHANGE_PASSWORD') ON CONFLICT DO NOTHING;

-- PAYMENT
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Shtoje te ardhura ekonomike',  '/api/economy_payment/read', 'READ_PAYMENTS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Lexoje te ardhurat ekonomike',  '/api/economy_payment/create', 'CREATE_PAYMENTS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Ndryshoj te ardhurat ekonomike',  '/api/economy_payment/update', 'UPDATE_PAYMENTS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Fshij te ardhura ekonomike',  '/api/economy_payment/delete', 'DELETE_PAYMENTS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Gjenero PDF',  '/api/economy_payment/export/pdf', 'EXPORT_PDF') ON CONFLICT DO NOTHING;

-- BOOKS
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Shoh listen e librave',  '/api/book_book/read', 'READ_BOOKS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Krijoj liber te ri',  '/api/book_book/create', 'CREATE_BOOKS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Ndryshoj te dhenat e librit',  '/api/book_book/update', 'UPDATE_BOOKS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Fshij liber',  '/api/book_book/delete', 'DELETE_BOOKS') ON CONFLICT DO NOTHING;

-- BORROW
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Lexoje huazimet',  '/api/book_borrow/read', 'READ_BORROWS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Huazoj liber',  '/api/book_borrow/borrow', 'BORROW') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Shiko nese libri eshte huazuar',  '/api/book_borrow/borrowed', 'BOOK_BORROWED') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Kthej librin e huazuar',  '/api/book_borrow/return', 'RETURN_BORROWED') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Shtyj Deadline-in e librit te huazuar per 1 jave',  '/api/book_borrow/extend-deadline', 'EXTEND_DEADLINE') ON CONFLICT DO NOTHING;

-- BOOK_COMMENTS
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Komentoj ne liber',  '/api/book_comment/create', 'CREATE_COMMENT') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Lexoje komentet',  '/api/book_comment/read', 'READ_COMMENTS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Ndryshoj komentin ne liber',  '/api/book_comment/update', 'UPDATE_COMMENT') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Fshij Komentin',  '/api/book_comment/delete', 'DELETE_COMMENT') ON CONFLICT DO NOTHING;

-- BOOK_COMMENTS
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Krijoje autore te ri',  '/api/book_author/create', 'CREATE_AUTHORS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Lexoje autore',  '/api/book_author/read', 'READ_AUTHORS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Ndryshoj te dhenat e autorit',  '/api/book_author/update', 'UPDATE_AUTHORS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Fshij autor',  '/api/book_author/delete', 'DELETE_AUTHORS') ON CONFLICT DO NOTHING;

-- ROLED AND PERMISSIONS
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Ndryshoje rolet dhe privilegjet',  '/api/account_role/change-permissions', 'CHANGE_ROLE_PERMISSIONS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Lexoje listen e te drejtave',  '/api/account_permissions/read', 'READ_PERMISSIONS') ON CONFLICT DO NOTHING;
INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Lexoje listen e roleve',  '/api/account_role/list', 'READ_ROLES') ON CONFLICT DO NOTHING;

INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Lexoje listen e shtepive te botimit',  '/book_edition/read', 'READ_BOOK_EDITION') ON CONFLICT DO NOTHING;


