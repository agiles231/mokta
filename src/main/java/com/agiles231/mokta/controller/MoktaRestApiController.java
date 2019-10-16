package com.agiles231.mokta.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agiles231.mokta.error.Error;
import com.agiles231.mokta.service.UserService;
import com.agiles231.mokta.user.User;
import com.agiles231.mokta.user.UserForm;
import com.agiles231.mokta.user.credentials.Credentials;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/v1")
public class MoktaRestApiController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private Gson gson;
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}")
	public String updateUser(@PathVariable(value="loginOrId") String loginOrId, @RequestBody UserForm userForm) {
		User user = get(loginOrId);
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}")
	public String partialUpdateUser(@PathVariable(value="loginOrId") String loginOrId, @RequestBody UserForm userForm, HttpServletResponse response) {
		User user = get(loginOrId);
		Credentials creds = userForm.getCredentials();
		Map<String, Object> profile = userForm.getProfile();
		for(Map.Entry<String, Object> entry : profile.entrySet()) {
			user.updateProfile(entry.getKey(), entry.getValue());
		}
		userService.partialUpdateUserById(user);
		return getUser(user.getId(), response); // must use id in case login is what was updated
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}")
	public String getUser(@PathVariable(value="loginOrId") String loginOrId, HttpServletResponse response) {
		try {
			User user = get(loginOrId);
			return gson.toJson(user);
		} catch(Exception e) {
			e.printStackTrace();
			String errorCode = "E0000007";
			response.setStatus(404);
			return gson.toJson(new Error(errorCode, "Not found: Resource not found: " + loginOrId + " (User)"
					, errorCode, "oaewgzWY_IaSs6G8Cf2TzzIsA", new String[] {}));
		}

	}
	
	private User get(String loginOrId) {
		User user = null;
		if (loginOrId.contains("@")) {
			user = userService.getUserByLogin(loginOrId);
		} else {
			user = userService.getUserById(loginOrId);
		}
		return user;
	}
	
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}")
	public String deleteUser(@PathVariable(value="loginOrId") String loginOrId) {
//		if (loginOrId.contains("@")) {
//			userService.deleteUserByLogin(loginOrId);
//		} else {
//			userService.deleteUserById(loginOrId);
//		}
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}/lifecycle/deactivate")
	public String deactivateUser(@PathVariable(value="loginOrId") String loginOrId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}/lifecycle/suspend")
	public String suspendUser(@PathVariable(value="loginOrId") String loginOrId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users")
	public String getUsers() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/apps")
	public String getApps() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/apps/{appId}")
	public String getApp(@PathVariable(value="appId") String appId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/apps/{appId}/users")
	public String getAppUsers(@PathVariable(value="appId") String appId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/apps/{appId}/users/{userId}")
	public String getAppUser(@PathVariable(value="appId") String appId, @PathVariable(value="userId") String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/apps/{appId}/users/{userId}")
	public String updateAppUser(@PathVariable(value="appId") String appId, @PathVariable(value="userId") String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/app/{appId}/users/{userId}")
	public String removeAppMembership(@PathVariable(value="appId") String appId, @PathVariable(value="userId") String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/groups")
	public String getGroups() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/groups/{groupId}")
	public String getGroup(@PathVariable("groupId") String groupId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/groups/{groupId}/users")
	public String getGroupUsers(@PathVariable(value="userId") String groupId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/groups/{groupId}/users/{userId}")
	public String addGroupMembership(@PathVariable(value="groupId") String groupId, @PathVariable(value="userId") String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/groups/{groupId}/users/{userId}")
	public String removeGroupMembership(@PathVariable(value="groupId") String groupId, @PathVariable(value="userId") String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/apps/{appId}/users/{userId}")
	public String addAppAssignment(@PathVariable(value="appId") String appId, @PathVariable(value="userId") String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}