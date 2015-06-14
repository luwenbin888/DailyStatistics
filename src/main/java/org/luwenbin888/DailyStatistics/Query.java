package org.luwenbin888.DailyStatistics;

public class Query {
	private Query() {}
	
	public static final String GetScannedTagsQuery = "SELECT CONVERT_FROM(T.log.hid,'UTF8') AS hid FROM hbase.activities AS T WHERE T.row_key >= '%s' AND T.row_key < '%s' AND CONVERT_FROM(T.log.d,'UTF8') <> 'ScanHandler' AND CAST(T.log.c AS INT) = %s AND CAST(T.log.`at` AS INT) = 1";
	
	public static final String GetEffectiveScannedTagsQuery = "SELECT DISTINCT CONVERT_FROM(T.log.hid,'UTF8') AS hid FROM hbase.activities AS T WHERE T.row_key >= '%s' AND T.row_key < '%s' AND CONVERT_FROM(T.log.d,'UTF8') <> 'ScanHandler' AND CAST(T.log.c AS INT) = %s AND CAST(T.log.`at` AS INT) = 2";
	
	public static final String GetActiveUsersQuery = "SELECT DISTINCT CONVERT_FROM(T.log.pn,'UTF8') AS pn FROM hbase.activities AS T WHERE T.row_key >= '%s' AND T.row_key < '%s' AND CONVERT_FROM(T.log.d,'UTF8') <> 'ScanHandler' AND CAST(T.log.c AS INT) = %s AND CAST(T.log.`at` AS INT) = 2 GROUP BY T.log.pn HAVING COUNT(*) >= 2";
	
}
