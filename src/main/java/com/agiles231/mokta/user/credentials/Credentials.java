package com.agiles231.mokta.user.credentials;

public class Credentials {
	
	private Password password;
	private RecoveryQuestion recoveryQuestion;
	private Provider provider;

	public Credentials(Password password, RecoveryQuestion recoveryQuestion, Provider provider) {
		super();
		this.password = password;
		this.recoveryQuestion = recoveryQuestion;
		this.provider = provider;
	}
	
	public Password getPassword() {
		return password;
	}

	public RecoveryQuestion getRecoveryQuestion() {
		return recoveryQuestion;
	}

	public Provider getProvider() {
		return provider;
	}
	
}
