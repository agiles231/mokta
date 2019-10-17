package com.agiles231.mokta.controller;

import java.net.URI;

public class LinkEnvelop extends SimpleLinkEnvelop {

	private final String method;

	public LinkEnvelop(URI uri, String method) {
		super(uri);
		this.method = method;
	}

	public String getMethod() {
		return method;
	}
	
}
