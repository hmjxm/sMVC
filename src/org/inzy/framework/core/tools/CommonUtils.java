package org.inzy.framework.core.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {
	/**
	 * getPK,获得数据库使用的一个long型唯一主键 16位，同一微秒内3000个不会重复
	 */
	private static long[] ls = new long[3000];
	private static String[] lsD = new String[3000];
	private static int li = 0;

	public synchronized static long getPK() {
		long lo = getpk();
		for (int i = 0; i < 3000; i++) {
			long lt = ls[i];
			if (lt == lo) {
				lo = getPK();
				break;
			}
		}
		ls[li] = lo;
		li++;
		if (li == 3000) {
			li = 0;
		}
		return lo;
	}

	private synchronized static long getpk() {
		String a = (String.valueOf(System.currentTimeMillis()))
				.substring(3, 13);
		String d = (String.valueOf(Math.random())).substring(2, 8);
		return Long.parseLong(a + d);
	}

	/**
	 * getWorkAutoNumber,获得数据库使用的一个String型唯一值如：20110727-173349-125-318
	 * 同一微秒内3000个不会重复
	 */
	public synchronized static String getWorkAutoNumber() {
		String lo = getWorkNumber();
		for (int i = 0; i < 3000; i++) {
			String lt = lsD[i];
			if (lo.equals(lt)) {
				lo = getWorkAutoNumber();
				break;
			}
		}
		lsD[li] = lo;
		li++;
		if (li == 3000) {
			li = 0;
		}
		return lo;
	}

	private synchronized static String getWorkNumber() {
		String a = (new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()));
		long currentTime = System.currentTimeMillis();
		String b = (String.valueOf(currentTime)).substring(10, 13); // (5,13)
		String c = (String.valueOf(Math.random())).substring(5, 8);
		return a + "-" + b + "-" + c;
	}

	public synchronized static long getLongKey() {
		String a = (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		long currentTime = System.currentTimeMillis();
		String b = (String.valueOf(currentTime)).substring(10, 13); // (5,13)
		String c = (String.valueOf(Math.random())).substring(5, 7);
		String s = a + b + c;
		long l = Long.parseLong(s);
		return l;
	}

	/*
	 * 生成36位通用唯一识别码，主要用于主键值的生成
	 */
	public static String getLongUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str;
	}

	/*
	 * 生成32位通用唯一识别码，主要用于主键值的生成
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String shortStr = str.replace("-", "");
		return shortStr;
	}

	public static void main(String args[]) {
		System.out.println(System.currentTimeMillis());
		System.out.println("getWorkAutoNumber==" + getWorkAutoNumber());
		System.out.println("getWorkAutoNumber==" + getWorkAutoNumber());
		System.out.println("getPK==" + getPK());

		long l = getLongKey();
		System.out.println("getLongKey=" + l);

		System.out.println("UUID:" + getUUID());

		// 结果
		// 1420508513529
		// getWorkAutoNumber==20150106-094153-572-328
		// getWorkAutoNumber==20150106-094153-573-032
		// getPK==508513576359395
		// UUID:4af28688c2d64084865b4af1bd534e73
	}
}
