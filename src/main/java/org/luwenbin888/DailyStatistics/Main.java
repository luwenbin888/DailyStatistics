package org.luwenbin888.DailyStatistics;

import java.sql.SQLException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws SQLException {
		List<Integer> scanStatistics = StatisticsUtil.getScanStatistics();
		
		System.out.println("Scan count: "+scanStatistics.get(0));
		System.out.println("Unique tag scan cnt: "+scanStatistics.get(1));
	}
}
