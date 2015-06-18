package com.toucha.analytics.DailyStatistics;

import java.math.BigDecimal;

public class RewardStatistics {
	public int claimTimes;
	public BigDecimal claimAmount;
	
	public int getClaimTimes() {
		return claimTimes;
	}
	public void setClaimTimes(int claimTimes) {
		this.claimTimes = claimTimes;
	}
	public BigDecimal getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}
	
	
}
