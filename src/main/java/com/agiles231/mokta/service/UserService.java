package com.agiles231.mokta.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.agiles231.mokta.controller.user.UserForm;
import com.agiles231.mokta.domain.user.User;
import com.agiles231.mokta.domain.user.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getUserByLoginOrId(String loginOrId) {
		User user = null;
		if (loginOrId.contains("@")) {
			user = userDao.getByLogin(loginOrId);
		} else {
			user = userDao.get(loginOrId);
		}
		return user;
	}
	public Collection<User> getUsers() {
		return userDao.getAll();
	}
//	public User updateUserById(String id, UserForm form) {
//		User user = getUserById(id);
//		// TODO: do update
//		throw new UnsupportedOperationException("Not implemented yet");
//	}
//	public User updateUserByLogin(String login, UserForm form) {
//		User user = getUserByLogin(login);
//		String id = user.getId();
//		return updateUserById(id, form);
//	}
	public User partialUpdateUserById(User user) {
		userDao.update(user);
		return getUserByLoginOrId(user.getId());
	}
	public User partialUpdateUserByLogin(String login, UserForm form) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void activateUser(String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void reactivateUser(String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void deactivateUser(String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void suspendUser(String userId) {
		User user = getUserByLoginOrId(userId);
		user.suspend();
		userDao.update(user);
	}
	public void unsuspendUser(String userId) {
		User user = getUserByLoginOrId(userId);
		user.unsuspend();
		userDao.update(user);
	}
	public void deleteUserByLoginOrId(String loginOrId) {
		User user = getUserByLoginOrId(loginOrId);
		if (user.isDeprovisioned()) {
			userDao.delete(user.getId());
		} else {
			throw new IllegalStateException("User must be DEPROVISIONED to delete");
		}
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void unlockUser(String userId) {
		User user = getUserByLoginOrId(userId);
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void resetPassword(String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void expirePassword(String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void resetFactors(String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void clearCurrentUserSessions(String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
