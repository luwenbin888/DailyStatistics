package com.toucha.analytics.DailyStatistics;

public class Query {
	private Query() {}
	
	public static final String GetScannedTagsCountQuery = "SELECT COUNT(*) AS scan1, COUNT(DISTINCT T.log.hid) AS scan2 FROM hbase.activities AS T WHERE T.row_key >= '%s' AND T.row_key < '%s' AND CONVERT_FROM(T.log.d,'UTF8') <> 'ScanHandler' AND CAST(T.log.c AS INT) = %s AND CAST(T.log.`at` AS INT) = 1";
	
	public static final String GetEffectiveScannedTagsCountQuery = "SELECT COUNT(DISTINCT T.log.hid) AS cnt FROM hbase.activities AS T WHERE T.row_key >= '%s' AND T.row_key < '%s' AND CONVERT_FROM(T.log.d,'UTF8') <> 'ScanHandler' AND CAST(T.log.c AS INT) = %s AND CAST(T.log.`at` AS INT) = 2";
	
	public static final String GetActiveUsersCountQuery = "WITH ActiveUsers AS (SELECT CONVERT_FROM(T.log.pn,'UTF8') AS pn FROM hbase.activities AS T WHERE T.row_key >= '%s' AND T.row_key < '%s' AND CONVERT_FROM(T.log.d,'UTF8') <> 'ScanHandler' AND CAST(T.log.c AS INT) = %s AND CAST(T.log.`at` AS INT) = 2 GROUP BY T.log.pn HAVING COUNT(*) >= 2) SELECT COUNT(pn) AS cnt FROM ActiveUsers";
	
	public static final String GetUsersQuery = "SELECT DISTINCT CONVERT_FROM(T.log.pn,'UTF8') AS pn FROM hbase.activities AS T WHERE T.row_key >= '%s' AND T.row_key < '%s' AND CONVERT_FROM(T.log.d,'UTF8') <> 'ScanHandler' AND CAST(T.log.c AS INT) = %s AND CAST(T.log.`at` AS INT) = 2";
	
}
