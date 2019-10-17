package com.agiles231.mokta.domain.user;

import java.sql.Types;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.agiles231.mokta.user.credentials.Credentials;
import com.agiles231.mokta.user.credentials.Password;
import com.agiles231.mokta.user.credentials.Provider;
import com.agiles231.mokta.user.credentials.RecoveryQuestion;

@Repository
public class UserSqlDao implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	String getUserSql = "SELECT * FROM mokta_user u \n"
			+ " WHERE u.user_id = ? ";
	String getUserByLoginSql = "SELECT u.* FROM mokta_user u, mokta_user_profile p \n"
			+ " WHERE u.user_id = p.user_id \n"
			+ " AND p.profile_key = 'login' \n"
			+ " AND p.profile_value_string = ? ";
	String getUserProfileSql = "SELECT p.user_id, p.profile_key, p.profile_value_string \n"
			+ " , p.profile_value_int, p.profile_value_double, p.profile_value_bool \n"
			+ " FROM mokta_user_profile p \n"
			+ " WHERE p.user_id = ? ";
	String getUserProfileByLoginSql = "SELECT p.user_id, p.profile_key, p.profile_value_string \n"
			+ " , p.profile_value_int, p.profile_value_double, p.profile_value_bool \n"
			+ " FROM mokta_user_profile p \n"
			+ " WHERE EXISTS (\n"
			+ "    SELECT * FROM mokta_user_profile p2 WHERE p2.profile_key = 'login' \n"
			+ "    AND p2.profile_value_string = ? \n"
			+ "    AND p2.user_id = p.user_id \n"
			+ " )";
	String getUsersSql = "SELECT * FROM mokta_user u WHERE status = ?";
	String getUserProfilesSql = "SELECT p.user_id, p.profile_key, p.profile_value_string \n"
			+ " , p.profile_value_int, p.profile_value_double, p.profile_value_bool \n"
			+ " FROM mokta_user_profile p ";
	
	String deleteUserSql = "DELETE FROM mokta_user u WHERE u.user_id = ?";
	String deleteUserProfileSql = "DELETE FROM mokta_user_profile p WHERE p.user_id = ?";
	
	String updateUserSql = "MERGE INTO mokta_user u USING ( \n"
			+ " SELECT ? user_id, ? status, ? status_changed \n"
			+ " , ? created, ? activated, ? last_login, ? last_updated \n"
			+ " , ? password_changed, ? cred_provider_type \n"
			+ " , ? cred_provider_name FROM dual \n"
			+ ") u2 ON (u.user_id = u2.user_id) \n"
			+ " WHEN MATCHED THEN UPDATE SET \n"
			+ " u.status = u2.status \n"
			+ " , u.status_changed = u2.status_changed \n"
			+ " , u.created = u2.created \n"
			+ " , u.activated = u2.activated \n"
			+ " , u.last_login = u2.last_login \n"
			+ " , u.last_updated = u2.last_updated \n"
			+ " , u.password_changed = u2.password_changed \n"
			+ " , u.cred_provider_type = u2.cred_provider_type \n"
			+ " , u.cred_provider_name = u2.cred_provider_name \n"
			+ " WHEN NOT MATCHED THEN INSERT (user_id, status, status_changed \n"
			+ " , created, activated, last_login, last_updated, password_changed \n"
			+ " , cred_provider_type, cred_provider_name) \n"
			+ " VALUES (u2.user_id, u2.status, u2.status_changed \n"
			+ " , u2.created, u2.activated, u2.last_login, u2.last_updated, u2.password_changed \n"
			+ " , u2.cred_provider_type, u2.cred_provider_name)";
	String updateUserProfileSql = "MERGE INTO mokta_user_profile p USING (\n"
			+ " SELECT ? user_id, ? profile_key, ? profile_value_string, ? profile_value_int \n"
			+ " , ? profile_value_double, ? profile_value_bool FROM dual \n"
			+ " ) p2 ON (p.user_id = p2.user_id AND p.profile_key = p2.profile_key) \n"
			+ " WHEN MATCHED THEN UPDATE SET \n"
			+ " p.profile_value_string = p2.profile_value_string \n"
			+ " , p.profile_value_int = p2.profile_value_int \n"
			+ " , p.profile_value_double = p2.profile_value_double \n"
			+ " , p.profile_value_bool = p2.profile_value_bool \n"
			+ " WHEN NOT MATCHED THEN INSERT (user_id, profile_key \n"
			+ " , profile_value_string, profile_value_int \n"
			+ " , profile_value_double, profile_value_bool) \n"
			+ " VALUES (p2.user_id, p2.profile_key \n"
			+ " , p2.profile_value_string, p2.profile_value_int \n"
			+ " , p2.profile_value_double, p2.profile_value_bool)";

	@Override
	public User get(String id) {
		Map<String, Object> profile = jdbcTemplate.query(getUserProfileSql, new Object[] {id}, (rs) -> {
			Map<String, Object> p = new HashMap<>();
			while (rs.next()) {
				String valueStr = rs.getString("profile_value_string");
				valueStr = rs.wasNull() ? null : valueStr;
				Integer valueInt = rs.getInt("profile_value_int");
				valueInt = rs.wasNull() ? null : valueInt;
				Double valueDouble = rs.getDouble("profile_value_double");
				valueDouble = rs.wasNull() ? null : valueDouble;
				Boolean valueBool = rs.getBoolean("profile_value_bool");
				valueBool = rs.wasNull() ? null : valueBool;
				Object obj = null;
				if(valueStr != null) {
					obj = valueStr;
				} else if(valueInt != null) {
					obj = valueInt;
				} else if(valueDouble != null) {
					obj = valueDouble;
				} else if(valueBool != null) {
					obj = valueBool;
				}
				p.put(rs.getString("profile_key"), obj);
			}
			return p;
		});
		User user = jdbcTemplate.query(getUserSql, new Object[] {id}, (rs) -> {
			rs.next();
			Credentials credentials = new Credentials(new Password(null), new RecoveryQuestion(rs.getString("recovery_question"))
					, new Provider(rs.getString("cred_provider_type"), rs.getString("cred_provider_name")));
			return new User(rs.getString("user_id"), rs.getString("status")
					, rs.getString("status_changed"), rs.getString("created")
					, rs.getString("activated"), rs.getString("last_login")
					, rs.getString("last_updated"), rs.getString("password_changed")
					, credentials, profile);
		});
		return user;
	}

	@Override
	public void delete(String id) {
		jdbcTemplate.update(deleteUserSql, new Object[] {id});
		jdbcTemplate.update(deleteUserProfileSql, new Object[] {id});
	}

	@Override
	public void update(User user) {
		String userId = user.getId();
		String status = user.getStatus();
		String statusChanged = user.getStatusChanged();
		String created = user.getCreated();
		String activated = user.getActivated();
		String lastLogin = user.getLastLogin();
		String lastUpdated = user.getLastUpdated();
		String passwordChanged = user.getPasswordChanged();
		Credentials credentials = user.getCredentials();
		String credentialsProviderType = credentials.getProvider().getType();
		String credentialsProviderName = credentials.getProvider().getName();
		jdbcTemplate.update(updateUserSql, new Object[] {userId, status, statusChanged, created
				, activated, lastLogin, lastUpdated, passwordChanged, credentialsProviderType
				, credentialsProviderName});

		Map<String, Object> profile = user.getProfile();
		List<Object[]> args = new LinkedList<>();
		for(Map.Entry<String, Object> entry : profile.entrySet()) {
			Object obj = entry.getValue();
			if (obj != null) {
				String valueStr = obj.getClass().getName().equals("java.lang.String") ? (String)obj : null;
				Integer valueInt = obj.getClass().getName().equals("java.lang.Integer") ? (Integer)obj : null;
				Double valueDouble = obj.getClass().getName().equals("java.lang.Double") ? (Double)obj : null;
				Boolean valueBool = obj.getClass().getName().equals("java.lang.Boolean") ? (Boolean)obj : null;
				args.add(new Object[] {userId, entry.getKey(), valueStr, valueInt, valueDouble, valueBool});
			} else {
				args.add(new Object[] {userId, entry.getKey(), null, null, null, null});
			}
		}
		jdbcTemplate.batchUpdate(updateUserProfileSql, args, new int[] {Types.VARCHAR, Types.VARCHAR
						, Types.VARCHAR, Types.INTEGER, Types.DOUBLE, Types.INTEGER});
	}

	@Override
	public Collection<User> getAll() {
		Map<String, Map<String, Object>> profiles = jdbcTemplate.query(getUserProfilesSql, (rs) -> {
			Map<String, Map<String, Object>> p = new HashMap<>();
			while (rs.next()) {
				String userId = rs.getString("user_id");
				Map<String, Object> map = new HashMap<>();
				String valueStr = rs.getString("profile_value_string");
				valueStr = rs.wasNull() ? null : valueStr;
				Integer valueInt = rs.getInt("profile_value_int");
				valueInt = rs.wasNull() ? null : valueInt;
				Double valueDouble = rs.getDouble("profile_value_double");
				valueDouble = rs.wasNull() ? null : valueDouble;
				Boolean valueBool = rs.getBoolean("profile_value_bool");
				valueBool = rs.wasNull() ? null : valueBool;
				Object obj = null;
				if(valueStr != null) {
					obj = valueStr;
				} else if(valueInt != null) {
					obj = valueInt;
				} else if(valueDouble != null) {
					obj = valueDouble;
				} else if(valueBool != null) {
					obj = valueBool;
				}
				map.put(rs.getString("profile_key"), obj);
				p.merge(userId, map, (v1, v2) -> { v1.putAll(v2); return v1; });
			}
			return p;
		});
		Collection<User> users = jdbcTemplate.query(getUsersSql, new Object[] {"ACTIVE"}, (rs) -> {
			Collection<User> us = new LinkedList<>();
			while (rs.next()) {
				String id = rs.getString("user_id");
				Credentials credentials = new Credentials(new Password(null), new RecoveryQuestion(rs.getString("recovery_question"))
						, new Provider(rs.getString("cred_provider_type"), rs.getString("cred_provider_name")));
				us.add(new User(id, rs.getString("status")
					, rs.getString("status_changed"), rs.getString("created")
					, rs.getString("activated"), rs.getString("last_login")
					, rs.getString("last_updated"), rs.getString("password_changed")
					, credentials, profiles.get(id)));
			}
			return us;
		});
		return users;
	}

	@Override
	public User getByLogin(String login) {
		Map<String, Object> profile = jdbcTemplate.query(getUserProfileByLoginSql, new Object[] {login}, (rs) -> {
			Map<String, Object> p = new HashMap<>();
			while (rs.next()) {
				String valueStr = rs.getString("profile_value_string");
				valueStr = rs.wasNull() ? null : valueStr;
				Integer valueInt = rs.getInt("profile_value_int");
				valueInt = rs.wasNull() ? null : valueInt;
				Double valueDouble = rs.getDouble("profile_value_double");
				valueDouble = rs.wasNull() ? null : valueDouble;
				Boolean valueBool = rs.getBoolean("profile_value_bool");
				valueBool = rs.wasNull() ? null : valueBool;
				Object obj = null;
				if(valueStr != null) {
					obj = valueStr;
				} else if(valueInt != null) {
					obj = valueInt;
				} else if(valueDouble != null) {
					obj = valueDouble;
				} else if(valueBool != null) {
					obj = valueBool;
				}
				p.put(rs.getString("profile_key"), obj);
			}
			return p;
		});
		User user = jdbcTemplate.query(getUserByLoginSql, new Object[] {login}, (rs) -> {
			rs.next();
			Credentials credentials = new Credentials(new Password(null), new RecoveryQuestion(rs.getString("recovery_question"))
					, new Provider(rs.getString("cred_provider_type"), rs.getString("cred_provider_name")));
			return new User(rs.getString("user_id"), rs.getString("status")
					, rs.getString("status_changed"), rs.getString("created")
					, rs.getString("activated"), rs.getString("last_login")
					, rs.getString("last_updated"), rs.getString("password_changed")
					, credentials, profile);
		});
		return user;
	}

}