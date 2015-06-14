package org.luwenbin888.DailyStatistics;

import java.util.Calendar;
import java.util.Date;

public class AppConfig {
	public static int WaterCompanyId = 35;
	public static int EnergyDrinkCompanyId = 5210;
	
	public static String DrillJDBCDriverClass = "org.apache.drill.jdbc.Driver";
	
	public static String DrillConnectionStr = "jdbc:drill:zk=prod2:2181,prod1:2181,prod3:2181/drill/drillprod;schema=hbase";
	
	public static Date WaterStartDate = null;
	public static Date EnergyDrinkStartDate = null;
	
	static {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 16);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		WaterStartDate = cal.getTime();
		
		cal.set(Calendar.MONTH, 5);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		EnergyDrinkStartDate = cal.getTime();
		
	}
}
