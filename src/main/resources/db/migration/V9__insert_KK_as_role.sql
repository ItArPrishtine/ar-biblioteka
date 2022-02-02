INSERT INTO account_roles(name, description) VALUES ('KK', 'Kryetar Kombetar') ON CONFLICT DO NOTHING;

SELECT relatePermissionToRole('KK', 'CREATE_USERS');
SELECT relatePermissionToRole('KK', 'READ_USERS');
SELECT relatePermissionToRole('KK', 'UPDATE_USERS');
SELECT relatePermissionToRole('KK', 'DELETE_USERS');
SELECT relatePermissionToRole('KK', 'CHANGE_PASSWORD');

SELECT relatePermissionToRole('KK', 'READ_PAYMENTS');
SELECT relatePermissionToRole('KK', 'CREATE_PAYMENTS');
SELECT relatePermissionToRole('KK', 'UPDATE_PAYMENTS');
SELECT relatePermissionToRole('KK', 'DELETE_PAYMENTS');
SELECT relatePermissionToRole('KK', 'EXPORT_PDF');

SELECT relatePermissionToRole('KK', 'READ_BOOKS');
SELECT relatePermissionToRole('KK', 'CREATE_BOOKS');
SELECT relatePermissionToRole('KK', 'UPDATE_BOOKS');
SELECT relatePermissionToRole('KK', 'DELETE_BOOKS');

SELECT relatePermissionToRole('KK', 'READ_BORROWS');
SELECT relatePermissionToRole('KK', 'BORROW');
SELECT relatePermissionToRole('KK', 'BOOK_BORROWED');
SELECT relatePermissionToRole('KK', 'RETURN_BORROWED');
SELECT relatePermissionToRole('KK', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('KK', 'CREATE_COMMENT');
SELECT relatePermissionToRole('KK', 'READ_COMMENTS');
SELECT relatePermissionToRole('KK', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('KK', 'DELETE_COMMENT');

SELECT relatePermissionToRole('KK', 'READ_AUTHORS');
SELECT relatePermissionToRole('KK', 'CREATE_AUTHORS');
SELECT relatePermissionToRole('KK', 'UPDATE_AUTHORS');
SELECT relatePermissionToRole('KK', 'DELETE_AUTHORS');

SELECT relatePermissionToRole('KK', 'CHANGE_ROLE_PERMISSIONS');
SELECT relatePermissionToRole('KK', 'READ_PERMISSIONS');
SELECT relatePermissionToRole('KK', 'READ_ROLES');

SELECT relatePermissionToRole('KK', 'READ_BOOK_EDITION');
SELECT relatePermissionToRole('KK', 'READ_ORGANIZATIONS');


