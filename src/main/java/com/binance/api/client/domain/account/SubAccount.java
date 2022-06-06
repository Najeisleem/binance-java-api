package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * History of account transfer.
 *
 * @see Transfer
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubAccount {
	String email;

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "SubAccount [email=" + email + "]";
	}

	
}
