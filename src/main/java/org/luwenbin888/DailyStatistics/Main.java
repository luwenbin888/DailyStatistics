package org.luwenbin888.DailyStatistics;

import java.sql.SQLException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		if (args == null || args.length != 2) {
			for (String arg:args) {
				System.out.println(arg);
				System.exit(1);
			}
			System.out.println("Main -companyId");
		}
		
		int companyId = Integer.parseInt(args[1]);
		
		Statistics stat = new Statistics();
		
		List<Integer> scanStatistics = StatisticsUtil.getScanStatistics(companyId);
		
		stat.setTotalScannedTagsCnt(scanStatistics.get(0));
		stat.setUniqueScannedTagsCnt(scanStatistics.get(1));
		
		
		
		System.out.println("Scan count: "+scanStatistics.get(0));
		System.out.println("Unique tag scan cnt: "+scanStatistics.get(1));
		
		
		
		int effectiveScanCount = StatisticsUtil.getEffectiveScanStatistics(companyId);
		stat.setEffectiveScannedTagsCnt(effectiveScanCount);
		//System.out.println("Effective scan count:"+effectiveScanCount);
		
		int activeUserCount = StatisticsUtil.getActiveUserStatistics(companyId);
		stat.setActiveUserCnt(activeUserCount);
		
		int historyEffectiveScanCount = StatisticsUtil.getHistoryEffectiveScanStatistics(companyId);
		stat.setHistoryEffectiveScannedTagsCnt(historyEffectiveScanCount);
		
		
		List<Integer> userStat = StatisticsUtil.getUserStatistics(companyId);
		stat.setNewUserCnt(userStat.get(0));
		stat.setOldUserScanCnt(userStat.get(1));
		stat.setTotalUserCnt(userStat.get(2));
		
		DrillUtil.closeConnection();
		
		System.out.println("Scan count: "+stat.getTotalScannedTagsCnt());
		System.out.println("Unique tag scan cnt: "+stat.getUniqueScannedTagsCnt());
		System.out.println("Effective scan count:" +stat.getEffectiveScannedTagsCnt());
		System.out.println("Active user count:"+stat.getActiveUserCnt());
		System.out.println("History effective scan count:"+stat.getHistoryEffectiveScannedTagsCnt());
		
		
		System.out.println("New user count:" + stat.getNewUserCnt());
		System.out.println("Old user scan count:"+stat.getOldUserScanCnt());
		System.out.println("Total user count:"+stat.getTotalUserCnt());
		
		System.out.println("Sending email..");
		EMailUtil.sendEmail(stat, 35);
	}
}
