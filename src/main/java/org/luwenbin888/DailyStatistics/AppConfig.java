package org.luwenbin888.DailyStatistics;

import java.util.Date;

public class AppConfig {
	public static int WaterCompanyId = 35;
	public static int EnergyDrinkCompanyId = 5210;
	
	public static String DrillJDBCDriverClass = "org.apache.drill.jdbc.Driver";
	
	public static String DrillConnectionStr = "jdbc:drill:zk=prod2:2181,prod1:2181,prod3:2181/drill/drillprod;schema=hbase";
	
	public static Date WaterStartDate = null;
	public static Date EnergyDrinkStartDate = null;
}
