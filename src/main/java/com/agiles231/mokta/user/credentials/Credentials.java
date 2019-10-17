package com.agiles231.mokta.user.credentials;

public class Credentials {
	
	private CredentialsPassword password;
	private CredentialsRecoveryQuestion recoveryQuestion;
	private CredentialsProvider provider;

	public Credentials(CredentialsPassword password, CredentialsRecoveryQuestion recoveryQuestion, CredentialsProvider provider) {
		super();
		this.password = password;
		this.recoveryQuestion = recoveryQuestion;
		this.provider = provider;
	}
	
	public CredentialsPassword getPassword() {
		return password;
	}

	public CredentialsRecoveryQuestion getRecoveryQuestion() {
		return recoveryQuestion;
	}

	public CredentialsProvider getProvider() {
		return provider;
	}
	
}
