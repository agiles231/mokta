package com.agiles231.mokta.controller.user;

import java.util.Map;

import com.agiles231.mokta.user.credentials.Credentials;

public class UserForm {

	Credentials credentials;
	Map<String, Object> profile;

	public UserForm() {
	}

	public UserForm(Credentials credentials, Map<String, Object> profile) {
		super();
		this.credentials = credentials;
		this.profile = profile;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Map<String, Object> getProfile() {
		return profile;
	}

	public void setProfile(Map<String, Object> profile) {
		this.profile = profile;
	}
	
}
