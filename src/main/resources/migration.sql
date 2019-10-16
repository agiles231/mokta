
CREATE TABLE mokta_user (
	user_id VARCHAR2(16)
	, status VARCHAR2(16)
	, status_changed VARCHAR2(32)
    , created VARCHAR2(32)
	, activated VARCHAR2(32)
	, last_login VARCHAR2(32)
	, last_updated VARCHAR2(32)
	, password_changed VARCHAR2(32)
	, cred_provider_type VARCHAR2(32)
	, cred_provider_name VARCHAR2(32)
	, CONSTRAINT mokta_user_pk PRIMARY KEY (user_id)
);

CREATE TABLE mokta_user_profile (
	user_id VARCHAR2(16)
	, profile_key VARCHAR2(32)
	, profile_value_string VARCHAR2(128)
	, profile_value_int INT
	, profile_value_double NUMBER
	, profile_value_bool NUMBER(1)
	, CONSTRAINT mokta_user_prof_pk PRIMARY KEY (user_id, profile_key)
	, CONSTRAINT mokta_user_prof_user_fk FOREIGN KEY (user_id) REFERENCES mokta_user(user_id)
);

CREATE TABLE mokta_app (
	app_id VARCHAR2(16)
	, name VARCHAR2(64)
	, label VARCHAR2(64)
	, status VARCHAR2(16)
	, last_updated VARCHAR(32)
	, created VARCHAR2(32)
	, CONSTRAINT mokta_app_pk PRIMARY KEY (app_id)
);

CREATE TABLE mokta_app_user (
	app_id VARCHAR2(16)
	, user_id VARCHAR2(16)
	, external_id VARCHAR2(512)
	, created VARCHAR2(32)
	, last_updated VARCHAR2(32)
	, scope VARCHAR2(8)
	, status VARCHAR2(16)
	, status_changed VARCHAR2(32)
	, password_changed VARCHAR2(32)
	, syncState VARCHAR2(16)
	, last_sync VARCHAR2(32)
	, cred_provider_type VARCHAR2(32)
	, cred_provider_name VARCHAR2(32)
	, CONSTRAINT mokta_app_user_pk PRIMARY KEY (app_id, user_id)
	, CONSTRAINT mokta_app_user_app_fk FOREIGN KEY (app_id) REFERENCES mokta_app(app_id)
	, CONSTRAINT mokta_app_user_user_fk FOREIGN KEY (user_id) REFERENCES mokta_user(user_id)
);

CREATE TABLE mokta_app_user_profile (
	app_id VARCHAR2(16)
	, user_id VARCHAR2(16)
	, profile_key VARCHAR2(32)
	, profile_value_string VARCHAR2(128)
	, profile_value_int INT
	, profile_value_double NUMBER
	, profile_value_bool NUMBER(1)
	, CONSTRAINT mokta_app_user_prof_pk PRIMARY KEY (app_id, user_id, profile_key)
	, CONSTRAINT mokta_app_user_prof_app_fk FOREIGN KEY (app_id) REFERENCES mokta_app(app_id)
	, CONSTRAINT mokta_app_user_prof_user_fk FOREIGN KEY (user_id) REFERENCES mokta_user(user_id)
);

CREATE TABLE mokta_group (
	group_id VARCHAR2(16)
	, created VARCHAR2(32)
	, last_updated VARCHAR2(32)
	, last_membership_update VARCHAR2(32)
	, group_type VARCHAR2(32)
	, CONSTRAINT mokta_group_pk PRIMARY KEY (group_id)
);

CREATE TABLE mokta_group_profile (
	group_id VARCHAR2(16)
	, profile_key VARCHAR2(32)
	, profile_value_string VARCHAR2(128)
	, profile_value_int INT
	, profile_value_double NUMBER
	, profile_value_bool NUMBER(1)
	, CONSTRAINT mokta_group_prof_pk PRIMARY KEY (group_id, profile_key)
	, CONSTRAINT mokta_group_prof_group_fk FOREIGN KEY (group_id) REFERENCES mokta_group(group_id)
);

CREATE TABLE mokta_group_membership (
	group_id VARCHAR2(16)
	, user_id VARCHAR2(16)
	, CONSTRAINT mokta_group_memship_pk PRIMARY KEY (group_id, user_id)
	, CONSTRAINT mokta_group_memship_group_fk FOREIGN KEY (group_id) REFERENCES mokta_group(group_id)
	, CONSTRAINT mokta_group_memship_user_fk FOREIGN KEY (user_id) REFERENCES mokta_user(user_id)
);