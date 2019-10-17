package com.agiles231.mokta.controller.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import com.agiles231.mokta.controller.SimpleLinkEnvelop;
import com.agiles231.mokta.domain.user.User;
import com.agiles231.mokta.user.credentials.Credentials;
import com.google.gson.annotations.SerializedName;

public class UserEnvelop {

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

	@SerializedName("_links")
	private Map<String, Map<String, String>> links;
	
	public UserEnvelop(User user, URI baseURI) throws URISyntaxException {
		this(user, baseURI, false);
	}
	public UserEnvelop(User user, URI baseURI, boolean selfLinkOnly) throws URISyntaxException {
		super();
		this.id = user.getId();
		this.status = user.getStatus();
		this.statusChanged = user.getStatusChanged();
		this.created = user.getCreated();
		this.activated = user.getActivated();
		this.lastLogin = user.getLastLogin();
		this.lastUpdated = user.getLastUpdated();
		this.passwordChanged = user.getPasswordChanged();
		this.credentials = user.getCredentials();
		this.profile = user.getProfile();
		
		if (!selfLinkOnly) {
			this.links = new UserLinksEnvelopBuilder(baseURI).buildAllUserLinks(user);
		} else {
			this.links = new UserLinksEnvelopBuilder(baseURI).buildSelfLink(user);
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

	public Map<String, Map<String, String>> getLinks() {
		return links;
	}
	
	
}
