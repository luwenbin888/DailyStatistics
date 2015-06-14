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
	
	/*
	public static String EmailRecipient = "yi.dai.@sao.so";
	public static String EmailCC = "steve.liu@sao.so,wenbin.lu@sao.so,jay.jia@sao.so,pitar.leung@sao.so,da.zhang@sao.so,pudan@sao.so";
	*/
	public static String EmailRecipient = "wenbin.lu@sao.so";
	public static String EmailCC = "luwenbin1016@126.com";
	public static String EmailSubject = "%s %s Statistics";
	public static String EmailContent = "This is auto generated email.\n\nNew user scan count:%s\nOld user scan count:%s\nAccumulated user count:%s\nTotal scan count:%s\nUnique tag scan count:%s\nEffective scan count:%s\nAccumulated effective scan count:%s\nActive user (>=2) count:%s\n";
}
