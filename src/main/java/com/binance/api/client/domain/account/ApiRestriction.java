package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * History of account withdraw.
 *
 * @see Withdraw
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiRestriction {

	private boolean ipRestrict;
	private long createTime;
	private boolean enableWithdrawals;
	private boolean enableInternalTransfer;
	private boolean permitsUniversalTransfer;
	private boolean enableVanillaOptions;
	private boolean enableReading;
	private boolean enableFutures;
	private boolean enableMargin;
	private boolean enableSpotAndMarginTrading;
	private long tradingAuthorityExpirationTime;
	public boolean isIpRestrict() {
		return ipRestrict;
	}
	public void setIpRestrict(boolean ipRestrict) {
		this.ipRestrict = ipRestrict;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public boolean isEnableWithdrawals() {
		return enableWithdrawals;
	}
	public void setEnableWithdrawals(boolean enableWithdrawals) {
		this.enableWithdrawals = enableWithdrawals;
	}
	public boolean isEnableInternalTransfer() {
		return enableInternalTransfer;
	}
	public void setEnableInternalTransfer(boolean enableInternalTransfer) {
		this.enableInternalTransfer = enableInternalTransfer;
	}
	public boolean isPermitsUniversalTransfer() {
		return permitsUniversalTransfer;
	}
	public void setPermitsUniversalTransfer(boolean permitsUniversalTransfer) {
		this.permitsUniversalTransfer = permitsUniversalTransfer;
	}
	public boolean isEnableVanillaOptions() {
		return enableVanillaOptions;
	}
	public void setEnableVanillaOptions(boolean enableVanillaOptions) {
		this.enableVanillaOptions = enableVanillaOptions;
	}
	public boolean isEnableReading() {
		return enableReading;
	}
	public void setEnableReading(boolean enableReading) {
		this.enableReading = enableReading;
	}
	public boolean isEnableFutures() {
		return enableFutures;
	}
	public void setEnableFutures(boolean enableFutures) {
		this.enableFutures = enableFutures;
	}
	public boolean isEnableMargin() {
		return enableMargin;
	}
	public void setEnableMargin(boolean enableMargin) {
		this.enableMargin = enableMargin;
	}
	public boolean isEnableSpotAndMarginTrading() {
		return enableSpotAndMarginTrading;
	}
	public void setEnableSpotAndMarginTrading(boolean enableSpotAndMarginTrading) {
		this.enableSpotAndMarginTrading = enableSpotAndMarginTrading;
	}
	public long getTradingAuthorityExpirationTime() {
		return tradingAuthorityExpirationTime;
	}
	public void setTradingAuthorityExpirationTime(long tradingAuthorityExpirationTime) {
		this.tradingAuthorityExpirationTime = tradingAuthorityExpirationTime;
	}
	@Override
	public String toString() {
		return "ApiRestriction [ipRestrict=" + ipRestrict + ", createTime=" + createTime + ", enableWithdrawals="
				+ enableWithdrawals + ", enableInternalTransfer=" + enableInternalTransfer
				+ ", permitsUniversalTransfer=" + permitsUniversalTransfer + ", enableVanillaOptions="
				+ enableVanillaOptions + ", enableReading=" + enableReading + ", enableFutures=" + enableFutures
				+ ", enableMargin=" + enableMargin + ", enableSpotAndMarginTrading=" + enableSpotAndMarginTrading
				+ ", tradingAuthorityExpirationTime=" + tradingAuthorityExpirationTime + "]";
	}
	
}
