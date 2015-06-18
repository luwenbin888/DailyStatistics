package com.toucha.analytics.DailyStatistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RewardStatisticsUtil {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static Map<Integer, RewardStatistics> getRewardStatistics(int companyId) throws ClassNotFoundException, SQLException {
		String userName = "zhabeiadmin@ltzk5b7p7v";
        String password = "Zhabei@123!!";

        String url = "jdbc:sqlserver://ltzk5b7p7v.database.chinacloudapi.cn;database=zhabei-platform;";
        Map<Integer,RewardStatistics> rewardStat = new HashMap<Integer,RewardStatistics>();
        
        try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        Connection conn = DriverManager.getConnection(url, userName, password);
	
	        Statement stmt = conn.createStatement();
	        
	        String query = "SELECT chosenRewardTypeId, COUNT(*) AS times, SUM(RewardAmount) AS amt FROM PromotionRewardWinner WHERE RewardClaimedTime >= '%s' AND RewardClaimedTime < '%s' AND CompanyId = %s AND Status = 3 GROUP BY ChosenRewardTypeId";
	        String rewardQuery = String.format(query, getTodayForSqlServer(), getYesterdayForSqlServer(), companyId);
	        System.out.println("Begin to execute query: "+rewardQuery);
	        ResultSet result = stmt.executeQuery(String.format(query, getYesterdayForSqlServer(), getTodayForSqlServer(), companyId));
	        
	        while (result.next()) {
	        	int rewardTypeId = result.getInt("chosenRewardTypeId");
	        	//String rewardType = rewardTypeId == 1? "PhoneTopup":"UnionPay";
	        	RewardStatistics stat = new RewardStatistics();
	        	stat.setClaimTimes(result.getInt("times"));
	        	stat.setClaimAmount(result.getBigDecimal("amt"));
	        	rewardStat.put(rewardTypeId, stat);
            }
        
	        result.close();
	        stmt.close();
	        conn.close();
        }
        catch (ClassNotFoundException ex) {
        	ex.printStackTrace();
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
        
        return rewardStat;
	}
	
	private static String getTodayForSqlServer() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return dateFormat.format(cal.getTime());
	}
	
	private static String getYesterdayForSqlServer() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return dateFormat.format(cal.getTime());
	}
}
