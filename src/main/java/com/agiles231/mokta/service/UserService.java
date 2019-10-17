package com.agiles231.mokta.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiles231.mokta.domain.user.User;
import com.agiles231.mokta.domain.user.UserDao;
import com.agiles231.mokta.user.credentials.Credentials;
import com.agiles231.mokta.user.credentials.CredentialsPassword;
import com.agiles231.mokta.user.credentials.CredentialsProvider;
import com.agiles231.mokta.user.credentials.CredentialsRecoveryQuestion;

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
	public void updateUserByLoginOrId(String id, Credentials credentials, Map<String, Object> profile) {
		User user = getUserByLoginOrId(id);
		user.replaceProfile(profile);
		user.updateCredentials(credentials);
		userDao.update(user);
	}
	public void partialUpdateUserByLoginOrId(String loginOrId, Credentials credentials, Map<String, Object> profile) {
		User user = getUserByLoginOrId(loginOrId);
		if(profile != null) {
			for(Map.Entry<String, Object> entry : profile.entrySet()) {
				user.updateProfile(entry.getKey(), entry.getValue());
			}
		}
		if(credentials != null) {
			Credentials existingCreds = user.getCredentials();
			CredentialsPassword existingPass = existingCreds.getPassword();
			CredentialsRecoveryQuestion existingRecoveryQuestion = existingCreds.getRecoveryQuestion();
			CredentialsProvider existingProvider = existingCreds.getProvider();
			CredentialsPassword password = credentials.getPassword() != null ? credentials.getPassword() : existingPass;
			CredentialsRecoveryQuestion recoveryQuestion = credentials.getRecoveryQuestion() != null ? credentials.getRecoveryQuestion() : existingRecoveryQuestion;
			CredentialsProvider provider = credentials.getProvider() != null ? credentials.getProvider() : existingProvider;
			Credentials newCreds = new Credentials(password, recoveryQuestion, provider);
			user.updateCredentials(newCreds);
		}
		userDao.update(user);
	}
	public void activateUser(String userId) {
		User user = getUserByLoginOrId(userId);
		user.activate();
		userDao.update(user);
	}
	public void reactivateUser(String userId) {
		User user = getUserByLoginOrId(userId);
		user.reactivate();
		userDao.update(user);
	}
	public void deactivateUser(String userId) {
		User user = getUserByLoginOrId(userId);
		user.deactivate();
		userDao.update(user);
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
		user.unlock();
		userDao.update(user);
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
