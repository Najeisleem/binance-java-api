package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * History of account deposits.
 *
 * @see Deposit
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterDepositHistory {

	private String amount;
	private String coin;
	private String network;
	private int status;
	private String addressTag;
	private String txId;
	private Long insertTime;
	private int transferType;
	private String unlockConfirm;
	private String confirmTimes;
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCoin() {
		return coin;
	}
	public void setCoin(String coin) {
		this.coin = coin;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddressTag() {
		return addressTag;
	}
	public void setAddressTag(String addressTag) {
		this.addressTag = addressTag;
	}
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}
	public Long getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Long insertTime) {
		this.insertTime = insertTime;
	}
	public int getTransferType() {
		return transferType;
	}
	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}
	public String getUnlockConfirm() {
		return unlockConfirm;
	}
	public void setUnlockConfirm(String unlockConfirm) {
		this.unlockConfirm = unlockConfirm;
	}
	public String getConfirmTimes() {
		return confirmTimes;
	}
	public void setConfirmTimes(String confirmTimes) {
		this.confirmTimes = confirmTimes;
	}
	
	
	@Override
	  public String toString() {
	    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
	        .append("amount", amount)
	        .append("coin", coin)
	        .append("network", network)
	        .append("status", status)
	        .append("addressTag", addressTag)
	        .append("txId", txId)
	        .append("insertTime", insertTime)
	        .append("transferType", transferType)
	        .append("unlockConfirm", unlockConfirm)
	        .append("confirmTimes", confirmTimes)
	        .toString();
	  }
}
