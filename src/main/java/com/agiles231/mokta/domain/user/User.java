package com.agiles231.mokta.domain.user;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.agiles231.mokta.user.credentials.Credentials;

public class User {
	
	private static final Map<String, Object> DEFAULT_PROFILE;
	static {
		Map<String, Object> tempDefaultProfile = new HashMap<>();
		tempDefaultProfile.put("login", "");
		tempDefaultProfile.put("email", "");
		tempDefaultProfile.put("secondEmail", null);
		tempDefaultProfile.put("firstName", "");
		tempDefaultProfile.put("lastName", "");
		tempDefaultProfile.put("middleName", null);
		tempDefaultProfile.put("title", null);
		tempDefaultProfile.put("displayName", null);
		tempDefaultProfile.put("nickName", null);
		tempDefaultProfile.put("profileUrl", null);
		tempDefaultProfile.put("primaryPhone", null);
		tempDefaultProfile.put("mobilePhone", null);
		tempDefaultProfile.put("streetAddress", null);
		tempDefaultProfile.put("city", null);
		tempDefaultProfile.put("state", null);
		tempDefaultProfile.put("zipCode", null);
		tempDefaultProfile.put("countryCode", null);
		tempDefaultProfile.put("postalAddress", null);
		tempDefaultProfile.put("preferredLanguage", null);
		tempDefaultProfile.put("locale", null);
		tempDefaultProfile.put("timezone", null);
		tempDefaultProfile.put("userType", null);
		tempDefaultProfile.put("employeeNumber", null);
		tempDefaultProfile.put("costCenter", null);
		tempDefaultProfile.put("organization", null);
		tempDefaultProfile.put("division", null);
		tempDefaultProfile.put("department", null);
		tempDefaultProfile.put("managerId", null);
		tempDefaultProfile.put("manager", null);
		DEFAULT_PROFILE = Collections.unmodifiableMap(tempDefaultProfile);
	}

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
		this.profile.putAll(DEFAULT_PROFILE);
		this.profile.putAll(profile);
	}
	
	public boolean isStaged() {
		return status.equals("STAGED");
	}
	public boolean isProvisioned() {
		return status.equals("PROVISIONED");
	}
	public boolean isActive() {
		return status.equals("ACTIVE");
	}
	public boolean isSuspended() {
		return status.equals("SUSPENDED");
	}
	public boolean isDeprovisioned() {
		return status.equals("DEPROVISIONED");
	}
	public boolean isLockedOut() {
		return status.equals("LOCKED_OUT");
	}
	
	public void activate() {
		if (isStaged()) {
			this.status = "ACTIVE";
			this.statusChanged = Instant.now().toString();
		} else {
			throw new IllegalStateException("Status must be STAGED in order to activate");
		}
	}
	public void suspend() {
		if (isActive()) {
			this.status = "SUSPENDED";
			this.statusChanged = Instant.now().toString();
		} else {
			throw new IllegalStateException("Status must be ACTIVE in order to suspend");
		}
	}
	public void deactivate() {
		if (!isDeprovisioned()) {
			this.status = "DEPROVISIONED";
			this.statusChanged = Instant.now().toString();
		} else {
			throw new IllegalStateException("Status must not be DEPROVISIONED in order to deactivate");
		}
	}
	public void reactivate() {
		if (isProvisioned()) {
			this.status = "ACTIVE";
			this.statusChanged = Instant.now().toString();
		} else {
			throw new IllegalStateException("Status must be PROVISIONING in order to reactivate");
		}
	}
	public void unsuspend() {
		if (isSuspended()) {
			this.status = "ACTIVE";
			this.statusChanged = Instant.now().toString();
		} else {
			throw new IllegalStateException("Status must be SUSPENDED in order to unsuspend");
		}
	}
	
	public void unlock() {
		if (isLockedOut()) {
			this.status = "ACTIVE";
			this.statusChanged = Instant.now().toString();
		} else {
			throw new IllegalStateException("Status must be LOCKED_OUT in order to unlock");
		}

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
	
	public void replaceProfile(Map<String, Object> profile) {
		this.profile.clear();
		this.profile.putAll(DEFAULT_PROFILE);
		this.profile.putAll(profile);
	}
	
	public void updateCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", status=" + status + ", statusChanged=" + statusChanged + ", activated=" + activated
				+ ", lastLogin=" + lastLogin + ", lastUpdated=" + lastUpdated + ", passwordChanged=" + passwordChanged
				+ ", credentials=" + credentials + ", profile=" + profile + "]";
	}
	
	
	
}
