package com.agiles231.mokta.controller;

import java.net.URI;

public class SimpleLinkEnvelop {

	private final String href;

	public SimpleLinkEnvelop(URI uri) {
		super();
		this.href = uri.toString();
	}

	public String getHref() {
		return href;
	}
	
}
