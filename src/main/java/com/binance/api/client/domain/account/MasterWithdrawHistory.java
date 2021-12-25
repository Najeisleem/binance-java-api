package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * History of account withdraw.
 *
 * @see Withdraw
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterWithdrawHistory {

	private String address;
	private String amount;
	private String applyTime;
	private String coin;
	private String id;
	private String withdrawOrderId;
	private String network;
	private int transferType;
	private int status;
	private String transactionFee;
	private int confirmNo;
	private String info;
	private String txId;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getCoin() {
		return coin;
	}
	public void setCoin(String coin) {
		this.coin = coin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWithdrawOrderId() {
		return withdrawOrderId;
	}
	public void setWithdrawOrderId(String withdrawOrderId) {
		this.withdrawOrderId = withdrawOrderId;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public int getTransferType() {
		return transferType;
	}
	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTransactionFee() {
		return transactionFee;
	}
	public void setTransactionFee(String transactionFee) {
		this.transactionFee = transactionFee;
	}
	public int getConfirmNo() {
		return confirmNo;
	}
	public void setConfirmNo(int confirmNo) {
		this.confirmNo = confirmNo;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}

	@Override
	  public String toString() {
	    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
	        .append("address", address)
	        .append("amount", amount)
	        .append("applyTime", applyTime)
	        .append("coin", coin)
	        .append("id", id)
	        .append("withdrawOrderId", withdrawOrderId)
	        .append("network", network)
	        .append("transferType", transferType)
	        .append("status", status)
	        .append("transactionFee", transactionFee)
	        .append("confirmNo", confirmNo)
	        .append("info", info)
	        .append("txId", txId)
	        .toString();
	  }
}
