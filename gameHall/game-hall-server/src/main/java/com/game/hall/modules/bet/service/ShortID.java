package com.game.hall.modules.bet.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.security.SecureRandom;

public class ShortID {

	public static volatile long time;
	private static volatile int nums = 0;
	private static List<String> poolStrings;
	// private static HashSet<String> poolStrings;
	static {
		// 初始你的数据池poolStrings,这里你可以从1-100000,直接数字初始化都可以
		// 或者按照上面的随机串初始化，但是要保证list里面的数据是非重复的,切记。

		poolStrings = new ArrayList<String>(100000);

		SecureRandom random = new SecureRandom();

		// byte bytes[] = new byte[20];
		// random.nextBytes(bytes);

		// HashSet<String> hashSet = new HashSet<>();

		for (int i = 0; i < 100000; i++) {

			int n = random.nextInt();
//			String s = Integer.toHexString(n);
			String s = Integer.toString(n);

			if (poolStrings.contains(s)) {
				System.out.println("errLine=35, now size =" + poolStrings.size());
				break;
			}

			poolStrings.add(s);

		}

	}
	/*
	 * 该方法主要保证一秒内获取的字符都是非重复的
	 */

	public static String generate() {
		// 如果nums为0的话,说明是首次取数字,记录下当前的时间,如果当前时间减去记录时间超过一秒了
		// 那么重新从数据池里面取数字,重置记录时间
		if (nums == 0 || System.currentTimeMillis() - time >= 1000) {
			time = System.currentTimeMillis();
		}
		int size = poolStrings.size();
		// 如果nums的数量等于数据池的大小了,而且当前时间距离记录时间还没过去一秒
		// 说明已经超出你的每秒的随机需求了,这时候你需要正视你的软件的每秒压力
		// 来调整数据池的大小。
		if (nums == size && System.currentTimeMillis() - time < 1000) {
			throw new RuntimeException("每秒需求的随机数超过了你的数据池");
		}
		// 正常取数字
		String randomString = (String) poolStrings.get(nums);
		// 对数字+1
		nums++;

		String stime = genCurTimeHex();

		return stime + randomString;

	}

	private static String genCurTimeHex() {
		time = System.currentTimeMillis();

		// String stime = Long.toString(time,16);

		String stime = Long.toString(time, 10);

		String shortTime = stime.substring(2, stime.length() - 3);

		return shortTime;

	}

}
