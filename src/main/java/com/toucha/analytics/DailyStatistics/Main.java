package com.toucha.analytics.DailyStatistics;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {

		if (args == null || args.length != 2) {
			System.out.println("Wrong params..");
			System.exit(1);
		}

		int companyId = Integer.parseInt(args[1]);

		if (companyId != AppConfig.WaterCompanyId
				&& companyId != AppConfig.EnergyDrinkCompanyId) {
			System.out.println("CompanyId " + companyId + " not supported..");
			System.exit(1);
		}

		Statistics stat = new Statistics();

		List<Integer> userStat = StatisticsUtil.getUserStatistics(companyId);
		stat.setNewUserCnt(userStat.get(0));
		stat.setOldUserScanCnt(userStat.get(1));
		stat.setTotalUserCnt(userStat.get(2));

		int activeUserCount = StatisticsUtil.getActiveUserStatistics(companyId);
		stat.setActiveUserCnt(activeUserCount);

		int historyActiveUserCount = StatisticsUtil
				.getHistoryActiveUserStatistics(companyId);
		stat.setHistoryActiveUserCnt(historyActiveUserCount);

		List<Integer> scanStatistics = StatisticsUtil
				.getScanStatistics(companyId);
		stat.setTotalScannedTagsCnt(scanStatistics.get(0));
		stat.setUniqueScannedTagsCnt(scanStatistics.get(1));

		int effectiveScanCount = StatisticsUtil
				.getEffectiveScanStatistics(companyId);
		stat.setEffectiveScannedTagsCnt(effectiveScanCount);

		int historyEffectiveScanCount = StatisticsUtil
				.getHistoryEffectiveScanStatistics(companyId);
		stat.setHistoryEffectiveScannedTagsCnt(historyEffectiveScanCount);

		DrillUtil.closeConnection();
		
		Map<Integer, RewardStatistics> rewardStat = RewardStatisticsUtil
				.getRewardStatistics(companyId);
		for (Integer rewardType : rewardStat.keySet()) {
			System.out.println(rewardType);
			System.out.println(rewardStat.get(rewardType).getClaimTimes());
			System.out.println(rewardStat.get(rewardType).getClaimAmount());
		}

		System.out.println("New user count:" + stat.getNewUserCnt());
		System.out.println("Old user scan count:" + stat.getOldUserScanCnt());
		System.out.println("Total user count:" + stat.getTotalUserCnt());
		System.out.println("Scan count: " + stat.getTotalScannedTagsCnt());
		System.out.println("Unique tag scan cnt: "
				+ stat.getUniqueScannedTagsCnt());
		System.out.println("Effective scan count:"
				+ stat.getEffectiveScannedTagsCnt());
		System.out.println("History effective scan count:"
				+ stat.getHistoryEffectiveScannedTagsCnt());
		System.out.println("Active user count:" + stat.getActiveUserCnt());
		System.out.println("History active user count:"
				+ stat.getHistoryActiveUserCnt());

		System.out.println("Sending email..");
		EMailUtil.sendEmail(stat, rewardStat, companyId);
	}
}
