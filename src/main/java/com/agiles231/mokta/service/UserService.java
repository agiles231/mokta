package com.agiles231.mokta.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.agiles231.mokta.user.User;
import com.agiles231.mokta.user.UserDao;
import com.agiles231.mokta.user.UserForm;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getUserById(String id) {
		return userDao.get(id);
	}
	public User getUserByLogin(String login) {
		return userDao.getByLogin(login);
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
		return getUserById(user.getId());
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
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void unsuspendUser(String userId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void deleteUserById(String id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void deleteUserByLogin(String login) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	public void unlockUser(String userId) {
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
