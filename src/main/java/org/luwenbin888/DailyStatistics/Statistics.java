package org.luwenbin888.DailyStatistics;

public class Statistics {
	public int totalScannedTagsCnt;
	public int uniqueScannedTagsCnt;
	public int effectiveScannedTagsCnt;
	
	public int historyEffectiveScannedTagsCnt;
	
	public int activeUserCnt;
	
	public int getTotalScannedTagsCnt() {
		return totalScannedTagsCnt;
	}
	public void setTotalScannedTagsCnt(int totalScannedTagsCnt) {
		this.totalScannedTagsCnt = totalScannedTagsCnt;
	}
	
	public int getUniqueScannedTagsCnt() {
		return uniqueScannedTagsCnt;
	}
	public void setUniqueScannedTagsCnt(int uniqueScannedTagsCnt) {
		this.uniqueScannedTagsCnt = uniqueScannedTagsCnt;
	}
	
	public int getEffectiveScannedTagsCnt() {
		return effectiveScannedTagsCnt;
	}
	public void setEffectiveScannedTagsCnt(int effectiveScannedTagsCnt) {
		this.effectiveScannedTagsCnt = effectiveScannedTagsCnt;
	}
	
	public int getActiveUserCnt() {
		return activeUserCnt;
	}
	public void setActiveUserCnt(int activeUserCnt) {
		this.activeUserCnt = activeUserCnt;
	}
	
	public int getHistoryEffectiveScannedTagsCnt() {
		return historyEffectiveScannedTagsCnt;
	}
	public void setHistoryEffectiveScannedTagsCnt(int historyEffectiveScannedTagsCnt) {
		this.historyEffectiveScannedTagsCnt = historyEffectiveScannedTagsCnt;
	}
	
}
