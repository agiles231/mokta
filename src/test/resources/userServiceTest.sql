
INSERT INTO mokta_user u (user_id, status, status_changed, activated, last_login
, last_updated, password_changed, cred_provider_type, cred_provider_name)
VALUES ('123', 'ACTIVE', SYSDATE, SYSDATE, SYSDATE, NULL, SYSDATE, 'Active Directory'
, 'domain.com');

INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'login', 'agiles@domain.com');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'email', 'agiles231@domain.com');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'firstName', 'Andrew');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'lastName', 'Giles');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'displayName', 'Andrew Giles');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'employeeCompanyIdentityRole', 'IT');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'office', 'KY-OFFICE');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'legacyEmployeeNumber', '01010101');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'title', 'IT Engr 2');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'managerEmployeeNumber', '222222');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'employeeNumber', '111111');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'companyTerritoryCode', 'USA');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'managerInitials', 'boss');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'AD_SAM_Account_Name', 'agiles');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'countryCode', 'US');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'state', 'KY');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'department', 'IT - DEV');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'initials', 'agiles');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'costCenter', '1234567890');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'secondEmail', NULL);
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'managerEmail', '');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_bool)
VALUES ('123', 'DirectReportsEmpty', 1);
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'EmployeeStatus', 'R');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'primaryPhone', '800/555-5555');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'mobilePhone', NULL);
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'streetAddress', '1000 Big Boi Lane, Big Boi, USA, Earth');
INSERT INTO mokta_user_profile p (user_id, profile_key, profile_value_string)
VALUES ('123', 'startDate', '1999-12-31');