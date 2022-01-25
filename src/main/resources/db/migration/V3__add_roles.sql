INSERT INTO account_roles(name, description) VALUES ('ADMIN', 'Admin') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('KF', 'Kryetari/ja i/e Filialit') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PGS PISHTARI', 'Pergjegjes/e ne grup seketarine Pishtari') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('PGS AKSI', 'Pergjegjes/e ne grup seketarine Aksi') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('PGS PENDA', 'Pergjegjes/e ne grup seketarine Penda') ON CONFLICT DO NOTHING;

-- Pishtari
INSERT INTO account_roles(name, description) VALUES ('PG tek Biblioteka', 'Pergjegjes/e ne sekretarine e bibliotekes') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Bilbioteka', 'Ndihmes/e ne sekretarine e bibliotekes') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek IT', 'Pergjegjes/e ne sekretarine e IT') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek IT', 'Ndihmes/e ne sekretarine e IT') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Ekonomia', 'Pergjegjes/e ne sekretarine e Ekonomise') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Ekonomia', 'Ndihmes/e ne sekretarine e Ekonomise') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Teatri', 'Pergjegjes/e ne sekretarine e Teatrit') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Teatri', 'Ndihmes/e ne sekretarine e teatrit') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Teknika', 'Pergjegjes/e ne sekretarine e Teknikes') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Teknika', 'Ndihmes/e ne sekretarine e Teknikes') ON CONFLICT DO NOTHING;

-- Aksi
INSERT INTO account_roles(name, description) VALUES ('PG i Vullnetarizmit', 'Pergjegjes/e ne sekretarine e Vullnetarizmit') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND i Vullnetarizmit', 'Ndihmes/e ne sekretarine e Vullnetarizmit') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Arkiva', 'Pergjegjes/e ne sekretarine e Arkivave') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Arkiva', 'Ndihmes/e ne sekretarine e Arkivave') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Promovimi', 'Pergjegjes/e ne sekretarine e Promovimit') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Promovimi', 'Ndihmes/e ne sekretarine e Promovimit') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Bari', 'Pergjegjes/e ne sekretarine e Barit') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Bari', 'Ndihmes/e ne sekretarine e Barit') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Prodhimi', 'Pergjegjes/e ne sekretarine e Prodhimit') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Prodhimi', 'Ndihmes/e ne sekretarine e Prodhimit') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Marketingu', 'Pergjegjes/e ne sekretarine e Marketingut') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Marketingu', 'Ndihmes/e ne sekretarine e Marketingut') ON CONFLICT DO NOTHING;

-- Penda

INSERT INTO account_roles(name, description) VALUES ('PG tek Integrimi', 'Pergjegjes/e ne sekretarine e Integrimit') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Integrimi', 'Ndihmes/e ne sekretarine e Integrimit') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Skolastika', 'Pergjegjes/e ne sekretarine e Skolastikes') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Skolastika', 'Ndihmes/e ne sekretarine e Skolastikes') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Propi', 'Pergjegjes/e ne sekretarine e Propit') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Propi', 'Ndihmes/e ne sekretarine e Propit') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek GM', 'Pergjegjes/e ne sekretarine e Grupit muzikor') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek GM', 'Ndihmes/e ne sekretarine e Grupit muzikor') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('PG tek Rrjetet Sociale', 'Pergjegjes/e ne sekretarine e Rrjeteve Sociale') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('ND tek Rrjetet Sociale', 'Ndihmes/e ne sekretarine e Rrjeteve Sociale') ON CONFLICT DO NOTHING;

INSERT INTO account_roles(name, description) VALUES ('Anetar', 'Anetar ne AR') ON CONFLICT DO NOTHING;
INSERT INTO account_roles(name, description) VALUES ('Provues', 'Provues ne AR') ON CONFLICT DO NOTHING;

