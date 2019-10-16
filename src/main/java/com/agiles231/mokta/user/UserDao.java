package com.agiles231.mokta.user;

import com.agiles231.mokta.persistance.Dao;

public interface UserDao extends Dao<String, User> {
	
	public User getByLogin(String login);

}
