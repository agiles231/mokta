package com.agiles231.mokta.persistance;

import java.util.Collection;

public interface Dao<T, U> {

	public U get(T id);
	public Collection<U> getAll();
	public void delete(T id);
	public void update(U obj);
}
