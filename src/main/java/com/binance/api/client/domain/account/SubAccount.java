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

	@Override
	public String toString() {
		return "SubAccount [email=" + email + "]";
	}
	

	
}
