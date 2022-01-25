-- ADMIN PERMISSIONS
SELECT relatePermissionToRole('ADMIN', 'CREATE_USERS');
SELECT relatePermissionToRole('ADMIN', 'READ_USERS');
SELECT relatePermissionToRole('ADMIN', 'UPDATE_USERS');
SELECT relatePermissionToRole('ADMIN', 'DELETE_USERS');
SELECT relatePermissionToRole('ADMIN', 'CHANGE_PASSWORD');

SELECT relatePermissionToRole('ADMIN', 'READ_PAYMENTS');
SELECT relatePermissionToRole('ADMIN', 'CREATE_PAYMENTS');
SELECT relatePermissionToRole('ADMIN', 'UPDATE_PAYMENTS');
SELECT relatePermissionToRole('ADMIN', 'DELETE_PAYMENTS');
SELECT relatePermissionToRole('ADMIN', 'EXPORT_PDF');

SELECT relatePermissionToRole('ADMIN', 'READ_BOOKS');
SELECT relatePermissionToRole('ADMIN', 'CREATE_BOOKS');
SELECT relatePermissionToRole('ADMIN', 'UPDATE_BOOKS');
SELECT relatePermissionToRole('ADMIN', 'DELETE_BOOKS');

SELECT relatePermissionToRole('ADMIN', 'READ_BORROWS');
SELECT relatePermissionToRole('ADMIN', 'BORROW');
SELECT relatePermissionToRole('ADMIN', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ADMIN', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ADMIN', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ADMIN', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ADMIN', 'READ_COMMENTS');
SELECT relatePermissionToRole('ADMIN', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ADMIN', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ADMIN', 'READ_AUTHORS');
SELECT relatePermissionToRole('ADMIN', 'CREATE_AUTHORS');
SELECT relatePermissionToRole('ADMIN', 'UPDATE_AUTHORS');
SELECT relatePermissionToRole('ADMIN', 'DELETE_AUTHORS');

SELECT relatePermissionToRole('ADMIN', 'CHANGE_ROLE_PERMISSIONS');
SELECT relatePermissionToRole('ADMIN', 'READ_PERMISSIONS');
SELECT relatePermissionToRole('ADMIN', 'READ_ROLES');

-- KF PERMISSIONS
SELECT relatePermissionToRole('KF', 'CREATE_USERS');
SELECT relatePermissionToRole('KF', 'READ_USERS');
SELECT relatePermissionToRole('KF', 'UPDATE_USERS');
SELECT relatePermissionToRole('KF', 'DELETE_USERS');
SELECT relatePermissionToRole('KF', 'CHANGE_PASSWORD');

SELECT relatePermissionToRole('KF', 'READ_PAYMENTS');
SELECT relatePermissionToRole('KF', 'CREATE_PAYMENTS');
SELECT relatePermissionToRole('KF', 'UPDATE_PAYMENTS');
SELECT relatePermissionToRole('KF', 'DELETE_PAYMENTS');
SELECT relatePermissionToRole('KF', 'EXPORT_PDF');

SELECT relatePermissionToRole('KF', 'READ_BOOKS');
SELECT relatePermissionToRole('KF', 'CREATE_BOOKS');
SELECT relatePermissionToRole('KF', 'UPDATE_BOOKS');
SELECT relatePermissionToRole('KF', 'DELETE_BOOKS');

SELECT relatePermissionToRole('KF', 'READ_BORROWS');
SELECT relatePermissionToRole('KF', 'BORROW');
SELECT relatePermissionToRole('KF', 'BOOK_BORROWED');
SELECT relatePermissionToRole('KF', 'RETURN_BORROWED');
SELECT relatePermissionToRole('KF', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('KF', 'CREATE_COMMENT');
SELECT relatePermissionToRole('KF', 'READ_COMMENTS');
SELECT relatePermissionToRole('KF', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('KF', 'DELETE_COMMENT');

SELECT relatePermissionToRole('KF', 'READ_AUTHORS');

SELECT relatePermissionToRole('KF', 'CHANGE_ROLE_PERMISSIONS');
SELECT relatePermissionToRole('KF', 'READ_PERMISSIONS');§
SELECT relatePermissionToRole('KF', 'READ_ROLES');

SELECT relatePermissionToRole('KF', 'READ_AUTHORS');
SELECT relatePermissionToRole('KF', 'CREATE_AUTHORS');
SELECT relatePermissionToRole('KF', 'UPDATE_AUTHORS');
SELECT relatePermissionToRole('KF', 'DELETE_AUTHORS');

-- PGS PISHTARI
SELECT relatePermissionToRole('PGS PISHTARI', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PGS PISHTARI', 'READ_BOOKS');

SELECT relatePermissionToRole('PGS PISHTARI', 'READ_BORROWS');
SELECT relatePermissionToRole('PGS PISHTARI', 'BORROW');
SELECT relatePermissionToRole('PGS PISHTARI', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PGS PISHTARI', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PGS PISHTARI', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PGS PISHTARI', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PGS PISHTARI', 'READ_COMMENTS');
SELECT relatePermissionToRole('PGS PISHTARI', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PGS PISHTARI', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PGS PISHTARI', 'READ_AUTHORS');

-- PGS AKSI
SELECT relatePermissionToRole('PGS AKSI', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PGS AKSI', 'READ_BOOKS');

SELECT relatePermissionToRole('PGS AKSI', 'READ_BORROWS');
SELECT relatePermissionToRole('PGS AKSI', 'BORROW');
SELECT relatePermissionToRole('PGS AKSI', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PGS AKSI', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PGS AKSI', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PGS AKSI', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PGS AKSI', 'READ_COMMENTS');
SELECT relatePermissionToRole('PGS AKSI', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PGS AKSI', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PGS AKSI', 'READ_AUTHORS');

-- PGS PENDA
SELECT relatePermissionToRole('PGS PENDA', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PGS PENDA', 'READ_BOOKS');

SELECT relatePermissionToRole('PGS PENDA', 'READ_BORROWS');
SELECT relatePermissionToRole('PGS PENDA', 'BORROW');
SELECT relatePermissionToRole('PGS PENDA', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PGS PENDA', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PGS PENDA', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PGS PENDA', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PGS PENDA', 'READ_COMMENTS');
SELECT relatePermissionToRole('PGS PENDA', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PGS PENDA', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PGS PENDA', 'READ_AUTHORS');

-- Biblioteka
SELECT relatePermissionToRole('PG tek Biblioteka', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Biblioteka', 'READ_BOOKS');
SELECT relatePermissionToRole('PG tek Biblioteka', 'CREATE_BOOKS');
SELECT relatePermissionToRole('PG tek Biblioteka', 'UPDATE_BOOKS');
SELECT relatePermissionToRole('PG tek Biblioteka', 'DELETE_BOOKS');

SELECT relatePermissionToRole('PG tek Biblioteka', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Biblioteka', 'BORROW');
SELECT relatePermissionToRole('PG tek Biblioteka', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Biblioteka', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Biblioteka', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Biblioteka', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Biblioteka', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Biblioteka', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Biblioteka', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Biblioteka', 'READ_AUTHORS');
SELECT relatePermissionToRole('PG tek Biblioteka', 'CREATE_AUTHORS');
SELECT relatePermissionToRole('PG tek Biblioteka', 'UPDATE_AUTHORS');
SELECT relatePermissionToRole('PG tek Biblioteka', 'DELETE_AUTHORS');
---
SELECT relatePermissionToRole('ND tek Biblioteka', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Biblioteka', 'READ_BOOKS');
SELECT relatePermissionToRole('ND tek Biblioteka', 'CREATE_BOOKS');
SELECT relatePermissionToRole('ND tek Biblioteka', 'UPDATE_BOOKS');
SELECT relatePermissionToRole('ND tek Biblioteka', 'DELETE_BOOKS');

SELECT relatePermissionToRole('ND tek Biblioteka', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Biblioteka', 'BORROW');
SELECT relatePermissionToRole('ND tek Biblioteka', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Biblioteka', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Biblioteka', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Biblioteka', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Biblioteka', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Biblioteka', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Biblioteka', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Biblioteka', 'READ_AUTHORS');
SELECT relatePermissionToRole('ND tek Biblioteka', 'CREATE_AUTHORS');
SELECT relatePermissionToRole('ND tek Biblioteka', 'UPDATE_AUTHORS');
SELECT relatePermissionToRole('ND tek Biblioteka', 'DELETE_AUTHORS');

-- IT
SELECT relatePermissionToRole('PG tek IT', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek IT', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek IT', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek IT', 'BORROW');
SELECT relatePermissionToRole('PG tek IT', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek IT', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek IT', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek IT', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek IT', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek IT', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek IT', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek IT', 'READ_AUTHORS');
----
SELECT relatePermissionToRole('ND tek IT', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek IT', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek IT', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek IT', 'BORROW');
SELECT relatePermissionToRole('ND tek IT', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek IT', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek IT', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek IT', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek IT', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek IT', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek IT', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek IT', 'READ_AUTHORS');

-- EKONOMIA
SELECT relatePermissionToRole('PG tek Ekonomia', 'READ_USERS');
SELECT relatePermissionToRole('PG tek Ekonomia', 'READ_PAYMENTS');
SELECT relatePermissionToRole('PG tek Ekonomia', 'CREATE_PAYMENTS');
SELECT relatePermissionToRole('PG tek Ekonomia', 'UPDATE_PAYMENTS');
SELECT relatePermissionToRole('PG tek Ekonomia', 'DELETE_PAYMENTS');
SELECT relatePermissionToRole('PG tek Ekonomia', 'EXPORT_PDF');
SELECT relatePermissionToRole('PG tek Ekonomia', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Ekonomia', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Ekonomia', 'BORROW');
SELECT relatePermissionToRole('PG tek Ekonomia', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Ekonomia', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Ekonomia', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Ekonomia', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Ekonomia', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Ekonomia', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Ekonomia', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Ekonomia', 'READ_AUTHORS');
---
SELECT relatePermissionToRole('ND tek Ekonomia', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Ekonomia', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Ekonomia', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Ekonomia', 'BORROW');
SELECT relatePermissionToRole('ND tek Ekonomia', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Ekonomia', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Ekonomia', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Ekonomia', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Ekonomia', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Ekonomia', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Ekonomia', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Ekonomia', 'READ_AUTHORS');

-- TEATRI
SELECT relatePermissionToRole('PG tek Teatri', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Teatri', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Teatri', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Teatri', 'BORROW');
SELECT relatePermissionToRole('PG tek Teatri', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Teatri', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Teatri', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Teatri', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Teatri', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Teatri', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Teatri', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Teatri', 'READ_AUTHORS');
---
SELECT relatePermissionToRole('ND tek Teatri', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Teatri', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Teatri', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Teatri', 'BORROW');
SELECT relatePermissionToRole('ND tek Teatri', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Teatri', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Teatri', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Teatri', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Teatri', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Teatri', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Teatri', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Teatri', 'READ_AUTHORS');

-- Teknika
SELECT relatePermissionToRole('PG tek Teknika', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Teknika', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Teknika', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Teknika', 'BORROW');
SELECT relatePermissionToRole('PG tek Teknika', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Teknika', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Teknika', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Teknika', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Teknika', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Teknika', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Teknika', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Teknika', 'READ_AUTHORS');
--
SELECT relatePermissionToRole('ND tek Teknika', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Teknika', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Teknika', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Teknika', 'BORROW');
SELECT relatePermissionToRole('ND tek Teknika', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Teknika', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Teknika', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Teknika', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Teknika', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Teknika', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Teknika', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Teknika', 'READ_AUTHORS');

-- PG VULLNETARIZMI
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'READ_BOOKS');

SELECT relatePermissionToRole('PG i Vullnetarizmit', 'READ_BORROWS');
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'BORROW');
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG i Vullnetarizmit', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG i Vullnetarizmit', 'READ_AUTHORS');
--
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'READ_BOOKS');

SELECT relatePermissionToRole('ND i Vullnetarizmit', 'READ_BORROWS');
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'BORROW');
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND i Vullnetarizmit', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND i Vullnetarizmit', 'READ_AUTHORS');

-- ARKIVA
SELECT relatePermissionToRole('PG tek Arkiva', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Arkiva', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Arkiva', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Arkiva', 'BORROW');
SELECT relatePermissionToRole('PG tek Arkiva', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Arkiva', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Arkiva', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Arkiva', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Arkiva', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Arkiva', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Arkiva', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Arkiva', 'READ_AUTHORS');
--
SELECT relatePermissionToRole('ND tek Arkiva', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Arkiva', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Arkiva', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Arkiva', 'BORROW');
SELECT relatePermissionToRole('ND tek Arkiva', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Arkiva', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Arkiva', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Arkiva', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Arkiva', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Arkiva', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Arkiva', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Arkiva', 'READ_AUTHORS');

-- PROMOVIMI
SELECT relatePermissionToRole('PG tek Promovimi', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Promovimi', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Promovimi', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Promovimi', 'BORROW');
SELECT relatePermissionToRole('PG tek Promovimi', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Promovimi', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Promovimi', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Promovimi', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Promovimi', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Promovimi', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Promovimi', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Promovimi', 'READ_AUTHORS');
--
SELECT relatePermissionToRole('ND tek Promovimi', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Promovimi', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Promovimi', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Promovimi', 'BORROW');
SELECT relatePermissionToRole('ND tek Promovimi', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Promovimi', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Promovimi', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Promovimi', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Promovimi', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Promovimi', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Promovimi', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Promovimi', 'READ_AUTHORS');

-- BARI
SELECT relatePermissionToRole('PG tek Bari', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Bari', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Bari', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Bari', 'BORROW');
SELECT relatePermissionToRole('PG tek Bari', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Bari', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Bari', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Bari', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Bari', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Bari', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Bari', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Bari', 'READ_AUTHORS');
--
SELECT relatePermissionToRole('ND tek Bari', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Bari', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Bari', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Bari', 'BORROW');
SELECT relatePermissionToRole('ND tek Bari', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Bari', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Bari', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Bari', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Bari', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Bari', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Bari', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Bari', 'READ_AUTHORS');

-- PRODHIMI
SELECT relatePermissionToRole('PG tek Prodhimi', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Prodhimi', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Prodhimi', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Prodhimi', 'BORROW');
SELECT relatePermissionToRole('PG tek Prodhimi', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Prodhimi', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Prodhimi', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Prodhimi', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Prodhimi', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Prodhimi', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Prodhimi', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Prodhimi', 'READ_AUTHORS');
--
SELECT relatePermissionToRole('ND tek Prodhimi', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Prodhimi', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Prodhimi', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Prodhimi', 'BORROW');
SELECT relatePermissionToRole('ND tek Prodhimi', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Prodhimi', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Prodhimi', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Prodhimi', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Prodhimi', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Prodhimi', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Prodhimi', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Prodhimi', 'READ_AUTHORS');

-- MARKETINGU
SELECT relatePermissionToRole('PG tek Marketingu', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Marketingu', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Marketingu', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Marketingu', 'BORROW');
SELECT relatePermissionToRole('PG tek Marketingu', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Marketingu', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Marketingu', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Marketingu', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Marketingu', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Marketingu', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Marketingu', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Marketingu', 'READ_AUTHORS');
--
SELECT relatePermissionToRole('ND tek Marketingu', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Marketingu', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Marketingu', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Marketingu', 'BORROW');
SELECT relatePermissionToRole('ND tek Marketingu', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Marketingu', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Marketingu', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Marketingu', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Marketingu', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Marketingu', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Marketingu', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Marketingu', 'READ_AUTHORS');

-- Integrimi
SELECT relatePermissionToRole('PG tek Integrimi', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Integrimi', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Integrimi', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Integrimi', 'BORROW');
SELECT relatePermissionToRole('PG tek Integrimi', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Integrimi', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Integrimi', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Integrimi', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Integrimi', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Integrimi', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Integrimi', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Integrimi', 'READ_AUTHORS');
--
SELECT relatePermissionToRole('ND tek Integrimi', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Integrimi', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Integrimi', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Integrimi', 'BORROW');
SELECT relatePermissionToRole('ND tek Integrimi', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Integrimi', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Integrimi', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Integrimi', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Integrimi', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Integrimi', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Integrimi', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Integrimi', 'READ_AUTHORS');

-- Skolastika
SELECT relatePermissionToRole('PG tek Skolastika', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Skolastika', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Skolastika', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Skolastika', 'BORROW');
SELECT relatePermissionToRole('PG tek Skolastika', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Skolastika', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Skolastika', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Skolastika', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Skolastika', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Skolastika', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Skolastika', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Skolastika', 'READ_AUTHORS');
--
SELECT relatePermissionToRole('ND tek Skolastika', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Skolastika', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Skolastika', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Skolastika', 'BORROW');
SELECT relatePermissionToRole('ND tek Skolastika', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Skolastika', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Skolastika', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Skolastika', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Skolastika', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Skolastika', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Skolastika', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Skolastika', 'READ_AUTHORS');

-- Propi
SELECT relatePermissionToRole('PG tek Propi', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Propi', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Propi', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Propi', 'BORROW');
SELECT relatePermissionToRole('PG tek Propi', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Propi', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Propi', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Propi', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Propi', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Propi', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Propi', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Propi', 'READ_AUTHORS');

--
SELECT relatePermissionToRole('ND tek Propi', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Propi', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Propi', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Propi', 'BORROW');
SELECT relatePermissionToRole('ND tek Propi', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Propi', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Propi', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Propi', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Propi', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Propi', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Propi', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Propi', 'READ_AUTHORS');

-- GM
SELECT relatePermissionToRole('PG tek GM', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek GM', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek GM', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek GM', 'BORROW');
SELECT relatePermissionToRole('PG tek GM', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek GM', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek GM', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek GM', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek GM', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek GM', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek GM', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek GM', 'READ_AUTHORS');

--
SELECT relatePermissionToRole('ND tek GM', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek GM', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek GM', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek GM', 'BORROW');
SELECT relatePermissionToRole('ND tek GM', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek GM', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek GM', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek GM', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek GM', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek GM', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek GM', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek GM', 'READ_AUTHORS');

-- Rrjetet Sociale
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'READ_BOOKS');

SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'READ_BORROWS');
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'BORROW');
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'BOOK_BORROWED');
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'RETURN_BORROWED');
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'CREATE_COMMENT');
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'READ_COMMENTS');
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'DELETE_COMMENT');

SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'READ_AUTHORS');

--
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'READ_BOOKS');

SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'READ_BORROWS');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'BORROW');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'BOOK_BORROWED');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'RETURN_BORROWED');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'CREATE_COMMENT');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'READ_COMMENTS');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'DELETE_COMMENT');

SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'READ_AUTHORS');

-- Anetar
SELECT relatePermissionToRole('Anetar', 'CHANGE_PASSWORD');
SELECT relatePermissionToRole('Anetar', 'CHANGE_PASSWORD');

SELECT relatePermissionToRole('Anetar', 'READ_BORROWS');
SELECT relatePermissionToRole('Anetar', 'BORROW');
SELECT relatePermissionToRole('Anetar', 'BOOK_BORROWED');
SELECT relatePermissionToRole('Anetar', 'RETURN_BORROWED');
SELECT relatePermissionToRole('Anetar', 'EXTEND_DEADLINE');

SELECT relatePermissionToRole('Anetar', 'CREATE_COMMENT');
SELECT relatePermissionToRole('Anetar', 'READ_COMMENTS');
SELECT relatePermissionToRole('Anetar', 'UPDATE_COMMENT');
SELECT relatePermissionToRole('Anetar', 'DELETE_COMMENT');

SELECT relatePermissionToRole('Anetar', 'READ_AUTHORS');
