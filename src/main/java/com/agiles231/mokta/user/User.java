package com.agiles231.mokta.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.agiles231.mokta.user.credentials.Credentials;

public class User {

	private final String id;
	private String status;
	private String statusChanged;
	private String created;
	private String activated;
	private String lastLogin;
	private String lastUpdated;
	private String passwordChanged;
	private Credentials credentials;
	private final Map<String, Object> profile;
	
	public User(String id) {
		this.id = id;
		this.profile = new HashMap<>();
	}
	public User(String id, String status, String statusChanged, String created, String activated
			, String lastLogin, String lastUpdated, String passwordChanged, Credentials credentials
			, Map<String, Object> profile) {
		super();
		this.id = id;
		this.status = status;
		this.statusChanged = statusChanged;
		this.created = created;
		this.activated = activated;
		this.lastLogin = lastLogin;
		this.lastUpdated = lastUpdated;
		this.passwordChanged = passwordChanged;
		this.credentials = credentials;
		this.profile = new HashMap<>();
		this.profile.put("login", "");
		this.profile.put("email", "");
		this.profile.put("secondEmail", null);
		this.profile.put("firstName", "");
		this.profile.put("lastName", "");
		this.profile.put("middleName", null);
		this.profile.put("title", null);
		this.profile.put("displayName", null);
		this.profile.put("nickName", null);
		this.profile.put("profileUrl", null);
		this.profile.put("primaryPhone", null);
		this.profile.put("mobilePhone", null);
		this.profile.put("streetAddress", null);
		this.profile.put("city", null);
		this.profile.put("state", null);
		this.profile.put("zipCode", null);
		this.profile.put("countryCode", null);
		this.profile.put("postalAddress", null);
		this.profile.put("preferredLanguage", null);
		this.profile.put("locale", null);
		this.profile.put("timezone", null);
		this.profile.put("userType", null);
		this.profile.put("employeeNumber", null);
		this.profile.put("costCenter", null);
		this.profile.put("organization", null);
		this.profile.put("division", null);
		this.profile.put("department", null);
		this.profile.put("managerId", null);
		this.profile.put("manager", null);
		this.profile.putAll(profile);
	}

	public String getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusChanged() {
		return statusChanged;
	}
	
	public String getCreated() {
		return created;
	}

	public String getActivated() {
		return activated;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public String getPasswordChanged() {
		return passwordChanged;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public Map<String, Object> getProfile() {
		return profile;
	}
	
	public void updateProfile(String key, Object newValue) {
		this.profile.put(key, newValue);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", status=" + status + ", statusChanged=" + statusChanged + ", activated=" + activated
				+ ", lastLogin=" + lastLogin + ", lastUpdated=" + lastUpdated + ", passwordChanged=" + passwordChanged
				+ ", credentials=" + credentials + ", profile=" + profile + "]";
	}
	
	
	
}
