package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * History of account transfer.
 *
 * @see Transfer
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferHistory {
	String from;
	String to;
	String asset;
	String qty;
	String status;
	long tranId;
	long time;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getTranId() {
		return tranId;
	}
	public void setTranId(long tranId) {
		this.tranId = tranId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "TransferHistory [from=" + from + ", to=" + to + ", asset=" + asset + ", qty=" + qty + ", status="
				+ status + ", tranId=" + tranId + ", time=" + time + "]";
	}
	
	
}
