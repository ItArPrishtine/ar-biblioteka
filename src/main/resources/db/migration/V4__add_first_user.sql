INSERT INTO account_users(id, date_of_birth, description, first_name, last_name, password, username, role_id, email, organization_id)
	VALUES (1, '06-12-1994', 'This is global admin', 'Admin', 'Admin', '$2a$10$KvIXJcMA014RH8Zd0xFpbuy5cE9.DQ1k1OPNp0t83tFEUmSoWr.2.', 'admin', 1, 'admin@admin.com', 1) ON CONFLICT DO NOTHING;
