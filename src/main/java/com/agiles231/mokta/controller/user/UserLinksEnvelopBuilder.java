package com.agiles231.mokta.controller.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.agiles231.mokta.domain.user.User;
import com.google.gson.internal.LinkedTreeMap;

public class UserLinksEnvelopBuilder {

	private final URI baseUri;

	public UserLinksEnvelopBuilder(URI baseUri) throws URISyntaxException {
		this.baseUri = baseUri;
	}
	
	public Map<String, Map<String, String>> buildSelfLink(User user) throws URISyntaxException {
		Map<String, Map<String, String>> links = new LinkedTreeMap<>();
		String userId = user.getId();
		String selfUrl = baseUri.toString() + "/api/v1/users/" + userId;
		Map<String, String> link = new HashMap<>();
		link.put("href", new URI(selfUrl).toString());
		links.put("self", link);
		return links;
	}
	public Map<String, Map<String, String>> buildAllUserLinks(User user) throws URISyntaxException {
		Map<String, Map<String, String>> links = new HashMap<>();
		String userId = user.getId();
		String selfUrl = baseUri.toString() + "/api/v1/users/" + userId;
		String lifecycleUrl = selfUrl + "/lifecycle";
		String credentialsUrl = selfUrl + "/credentials";
		Map<String, String> link = null; 
		if (user.isStaged()) {
			link = new LinkedTreeMap<>();
			link.put("href", new URI(lifecycleUrl + "/activate").toString());
			link.put("method", "POST");
			links.put("activate", link);
		} else if (user.isProvisioned()) {
			link = new LinkedTreeMap<>();
			link.put("href", new URI(lifecycleUrl + "/reactivate").toString());
			link.put("method", "POST");
			links.put("reactivate", link);
		} else if (user.isActive()) {
			link = new LinkedTreeMap<>();
			link.put("href", new URI(lifecycleUrl + "/suspend").toString());
			link.put("method", "POST");
			links.put("suspend", link);
		} else if (user.isSuspended()) {
			link = new LinkedTreeMap<>();
			link.put("href", new URI(lifecycleUrl + "/unsuspend").toString());
			link.put("method", "POST");
			links.put("unsuspend", link);
		} else if (user.isLockedOut()) {
			link = new LinkedTreeMap<>();
			link.put("href", new URI(lifecycleUrl + "/unlock").toString());
			link.put("method", "POST");
			links.put("unlock", link);
		}
		if (!user.isDeprovisioned()) {
			link = new LinkedTreeMap<>();
			link.put("href", new URI(lifecycleUrl + "/reactivate").toString());
			link.put("method", "POST");
			links.put("deactivate", link);
		}
		link = new LinkedTreeMap<>();
		link.put("href", new URI(lifecycleUrl + "/reset_password").toString());
		link.put("method", "POST");
		links.put("resetPassword", link);
		link = new LinkedTreeMap<>();
		link.put("href", new URI(credentialsUrl + "/forgot_password").toString());
		link.put("method", "POST");
		links.put("forgotPassword", link);
		link = new LinkedTreeMap<>();
		link.put("href", new URI(lifecycleUrl + "/expire_password").toString());
		link.put("method", "POST");
		links.put("expirePassword", link);
		link = new LinkedTreeMap<>();
		link.put("href", new URI(credentialsUrl + "/change_recovery_question").toString());
		link.put("method", "POST");
		links.put("changeRecoveryQuestion", link);
		link = new LinkedTreeMap<>();
		link.put("href", new URI(selfUrl).toString());
		links.put("self", link);
		link = new LinkedTreeMap<>();
		link.put("href", new URI(lifecycleUrl + "/reset_factors").toString());
		link.put("method", "POST");
		links.put("resetFactors", link);
		link = new LinkedTreeMap<>();
		link.put("href", new URI(credentialsUrl + "/change_password").toString());
		link.put("method", "POST");
		links.put("changePassword", link);
		return links;
	}

}
