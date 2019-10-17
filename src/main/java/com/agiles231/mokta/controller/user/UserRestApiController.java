package com.agiles231.mokta.controller.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agiles231.mokta.domain.user.User;
import com.agiles231.mokta.error.Error;
import com.agiles231.mokta.service.UserService;
import com.agiles231.mokta.user.credentials.Credentials;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/v1")
public class UserRestApiController {
	
	@Value("${baseUrl}")
	private String baseUrl;
	
	@Autowired
	private UserService userService;
	@Autowired
	private Gson gson;
	
	private URI baseUri;
	@PostConstruct
	public void init() throws URISyntaxException {
		baseUri = new URI(baseUrl);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}")
	public String updateUser(@PathVariable(value="loginOrId") String loginOrId, @RequestBody UserForm userForm, HttpServletResponse response) {
		User user = userService.getUserByLoginOrId(loginOrId);
		Credentials creds = userForm.getCredentials();
		Map<String, Object> profile = userForm.getProfile();
		userService.updateUserByLoginOrId(user.getId(), creds, profile);
		return getUser(user.getId(), response); // must use id in case login is what was updated
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}")
	public String partialUpdateUser(@PathVariable(value="loginOrId") String loginOrId, @RequestBody UserForm userForm, HttpServletResponse response) {
		User user = userService.getUserByLoginOrId(loginOrId);
		Credentials creds = userForm.getCredentials();
		Map<String, Object> profile = userForm.getProfile();
		userService.partialUpdateUserByLoginOrId(user.getId(), creds, profile);
		return getUser(user.getId(), response); // must use id in case login is what was updated
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}")
	public String getUser(@PathVariable(value="loginOrId") String loginOrId, HttpServletResponse response) {
		try {
			User user = userService.getUserByLoginOrId(loginOrId);
			UserEnvelop userEnvelop = new UserEnvelop(user, baseUri);
			return gson.toJson(userEnvelop);
		} catch(Exception e) {
			e.printStackTrace();
			String errorCode = "E0000007";
			response.setStatus(404);
			return gson.toJson(new Error(errorCode, "Not found: Resource not found: " + loginOrId + " (User)"
					, errorCode, "oaewgzWY_IaSs6G8Cf2TzzIsA", new String[] {}));
		}

	}
	
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}")
	public String deleteUser(@PathVariable(value="loginOrId") String loginOrId) {
		userService.deleteUserByLoginOrId(loginOrId);
		return "";
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}/lifecycle/deactivate")
	public String deactivateUser(@PathVariable(value="loginOrId") String loginOrId) {
		userService.deactivateUser(loginOrId);
		return "";
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users/{loginOrId}/lifecycle/suspend")
	public String suspendUser(@PathVariable(value="loginOrId") String loginOrId) {
		userService.suspendUser(loginOrId);
		return "";
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/users")
	public String getUsers(@RequestParam(value="filter", required=false) String filter
			, @RequestParam(value="search", required=false) String search) throws URISyntaxException {
		Collection<User> users = userService.getUsers();
		Collection<UserEnvelop> userEnvelops = new LinkedList<>();
		for (User user : users) {
			userEnvelops.add(new UserEnvelop(user, baseUri, true));
		}
		return gson.toJson(userEnvelops);
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