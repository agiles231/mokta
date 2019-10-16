package com.agiles231.mokta.user.credentials;

public class Provider {
	private final String type;
	private final String name;

	public Provider(String type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
}