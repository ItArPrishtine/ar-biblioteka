CREATE OR REPLACE FUNCTION relatePermissionToRole(roleName varchar, permissionCode varchar) RETURNS void AS $$
DECLARE
roleId int;
    permissionId int;
BEGIN
    roleId := (SELECT id from account_roles where lower(name) = lower(roleName));
    permissionId := (SELECT id from account_permissions where lower(permission_code) = lower(permissionCode));
INSERT INTO account_role_permissions (role_id, permission_id) VALUES (roleId, permissionId) ON CONFLICT DO NOTHING;
END
$$
LANGUAGE plpgsql;
