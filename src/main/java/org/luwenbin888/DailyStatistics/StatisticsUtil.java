package org.luwenbin888.DailyStatistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatisticsUtil {
	public static List<Integer> getScanStatistics(int companyId) throws SQLException {
		String query = String.format(Query.GetScannedTagsQuery,getYesterdayRowKey(),getTodayRowKey(),companyId);
		System.out.println("Beginning to execute query: "+query);
		ResultSet result = DrillUtil.submitQuery(query);
		
		
		Set<String> scannedTags = new HashSet<String>();
		
		int totalScannedTagsCnt = 0;
		
		while(result.next()) {
			
			//System.out.println(result.getString("hid"));
			scannedTags.add(result.getString("hid"));
			
			totalScannedTagsCnt++;
		}
		
		List<Integer> scanStatistics = new ArrayList<Integer>();
		scanStatistics.add(totalScannedTagsCnt);
		scanStatistics.add(scannedTags.size());
		
		result.close();
		
		return scanStatistics;
	}
	
	public static int getEffectiveScanStatistics(int companyId) throws SQLException {
		String query = String.format(Query.GetEffectiveScannedTagsQuery, getYesterdayRowKey(),getTodayRowKey(),companyId);
		System.out.println("Beginning to execute query: " + query);
		
		ResultSet result = DrillUtil.submitQuery(query);
		
		Set<String> enterLotteryTags = new HashSet<String>();
		
		int enterLotteryTagCnt = 0;
		
		while(result.next()) {
			
			//System.out.println(result.getString("hid"));
			enterLotteryTags.add(result.getString("hid"));
			
			enterLotteryTagCnt++;
		}
		
		result.close();
		
		return enterLotteryTagCnt;
	}
	
	public static int getActiveUserStatistics(int companyId) throws SQLException {
		String query = String.format(Query.GetActiveUsersQuery, getYesterdayRowKey(),getTodayRowKey(),companyId);
		System.out.println("Beginning to execute query: " + query);
		
		ResultSet result = DrillUtil.submitQuery(query);
		
		int activeUserCnt = 0;
		Set<String> activeUsers = new HashSet<String>();
		while(result.next()) {
			activeUsers.add(result.getString("pn"));
			activeUserCnt++;
		}
		
		result.close();
		
		return activeUserCnt;
	}
	
	public static int getHistoryEffectiveScanStatistics(int companyId) throws SQLException {
		
		String startRowKey = getCompanyStartRowKey(companyId);
		String query = String.format(Query.GetEffectiveScannedTagsQuery, startRowKey,getTodayRowKey(),companyId);
		System.out.println("Beginning to execute query: " + query);
		
		ResultSet result = DrillUtil.submitQuery(query);
		
		Set<String> enterLotteryTags = new HashSet<String>();
		
		int enterLotteryTagCnt = 0;
		
		while(result.next()) {
			
			//System.out.println(result.getString("hid"));
			enterLotteryTags.add(result.getString("hid"));
			
			enterLotteryTagCnt++;
		}
		
		result.close();
		
		return enterLotteryTagCnt;
	}
	
	public static List<Integer> getUserStatistics(int companyId) throws SQLException {
		String startRowKey = getCompanyStartRowKey(companyId);
		String query = String.format(Query.GetUsersQuery, startRowKey,getYesterdayRowKey(),AppConfig.WaterCompanyId);
		System.out.println("Beginning to execute query: " + query);
		
		ResultSet result = DrillUtil.submitQuery(query);
		Set<String> historyUsers = new HashSet<String>();
		
		while(result.next()) {
			historyUsers.add(result.getString("pn"));
		}
		result.close();
		
		query = String.format(Query.GetUsersQuery, getYesterdayRowKey(),getTodayRowKey(),AppConfig.WaterCompanyId);
		System.out.println("Beginning to execute query: " + query);
		result = DrillUtil.submitQuery(query);
		Set<String> yesterdayUsers = new HashSet<String>();
		
		while(result.next()) {
			yesterdayUsers.add(result.getString("pn"));
		}
		result.close();
		
		int totalYesterdayUsersCnt = yesterdayUsers.size();
		yesterdayUsers.removeAll(historyUsers);
		
		int newUserCnt = yesterdayUsers.size();
		int oldUserScanCnt = totalYesterdayUsersCnt - newUserCnt;
		int historyUserCnt = historyUsers.size() + newUserCnt;
		
		List<Integer> userStat = new ArrayList<Integer>();
		userStat.add(newUserCnt);
		userStat.add(oldUserScanCnt);
		userStat.add(historyUserCnt);
		
		return userStat;
	}
	
	private static String getCompanyStartRowKey(int companyId) {
		Date startDate = null;
		switch (companyId) {
		case AppConfig.WaterCompanyId:
			startDate = AppConfig.WaterStartDate;
			break;
		case AppConfig.EnergyDrinkCompanyId:
			startDate = AppConfig.EnergyDrinkStartDate;
			break;
			default:
				startDate = new Date();
		}
		String startRowKey = padding(startDate.getTime());
		
		return startRowKey;
	}
	
	private static String getTodayRowKey() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		String todayRowKey = padding(cal.getTimeInMillis());
		
		return todayRowKey;
	}
	
	private static String getYesterdayRowKey() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		String yesterdayRowKey = padding(cal.getTimeInMillis());
		
		return yesterdayRowKey;
	}
	
	private static String padding(Number n) {
        String s = null;
        int length = 8;
        if (n instanceof Long) {
            s = Long.toHexString(n.longValue());
            length = 16;
        } else if (n instanceof Integer) {
            s = Integer.toHexString(n.intValue());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < length; i++) {
            sb.append(0);
        }
        return sb.append(s).toString();
    }
}
