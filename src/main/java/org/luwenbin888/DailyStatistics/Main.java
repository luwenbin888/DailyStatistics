package org.luwenbin888.DailyStatistics;

import java.sql.SQLException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		Statistics stat = new Statistics();
		
		List<Integer> scanStatistics = StatisticsUtil.getScanStatistics();
		
		stat.setTotalScannedTagsCnt(scanStatistics.get(0));
		stat.setUniqueScannedTagsCnt(scanStatistics.get(1));
		
		/*
		System.out.println("Scan count: "+scanStatistics.get(0));
		System.out.println("Unique tag scan cnt: "+scanStatistics.get(1));
		*/
		
		
		int effectiveScanCount = StatisticsUtil.getEffectiveScanStatistics();
		stat.setEffectiveScannedTagsCnt(effectiveScanCount);
		//System.out.println("Effective scan count:"+effectiveScanCount);
		
		int activeUserCount = StatisticsUtil.getActiveUserStatistics();
		stat.setActiveUserCnt(activeUserCount);
		
		System.out.println("Scan count: "+stat.getTotalScannedTagsCnt());
		System.out.println("Unique tag scan cnt: "+stat.getUniqueScannedTagsCnt());
		System.out.println("Effective scan count:" +stat.getEffectiveScannedTagsCnt());
		System.out.println("Active user count:"+stat.getActiveUserCnt());
	}
}
