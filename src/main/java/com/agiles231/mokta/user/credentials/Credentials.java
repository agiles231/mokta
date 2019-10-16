package com.agiles231.mokta.user.credentials;

public class Credentials {
	
	private RecoveryQuestion recoveryQuestion;
	private Provider provider;

	public Credentials(RecoveryQuestion recoveryQuestion, Provider provider) {
		super();
		this.recoveryQuestion = recoveryQuestion;
		this.provider = provider;
	}
	
	public RecoveryQuestion getRecoveryQuestion() {
		return recoveryQuestion;
	}

	public Provider getProvider() {
		return provider;
	}
	
}
