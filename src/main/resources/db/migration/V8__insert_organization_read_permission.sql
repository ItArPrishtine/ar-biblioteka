INSERT INTO account_permissions(description, endpoint, permission_code) VALUES ('Lexoje Filialet',  '/api/organization/read', 'READ_ORGANIZATIONS') ON CONFLICT DO NOTHING;


SELECT relatePermissionToRole('ADMIN', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('KF', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PGS PISHTARI', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PGS AKSI', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PGS PENDA', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Biblioteka', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Biblioteka', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek IT', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek IT', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Ekonomia', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Ekonomia', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Teatri', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Teatri', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Teknika', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Teknika', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG i Vullnetarizmit', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND i Vullnetarizmit', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Arkiva', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Arkiva', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Promovimi', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Promovimi', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Bari', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Bari', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Prodhimi', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Prodhimi', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Marketingu', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Marketingu', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Integrimi', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Integrimi', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Skolastika', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Skolastika', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Skolastika', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Propi', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Propi', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek GM', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek GM', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('PG tek Rrjetet Sociale', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('ND tek Rrjetet Sociale', 'READ_ORGANIZATIONS');
SELECT relatePermissionToRole('Anetar', 'READ_ORGANIZATIONS');

