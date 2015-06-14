package org.luwenbin888.DailyStatistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatisticsUtil {
	public static List<Integer> getScanStatistics() throws SQLException {
		String query = String.format(Query.GetScannedTagsQuery,getYesterdayRowKey(),getTodayRowKey(),AppConfig.WaterCompanyId);
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
