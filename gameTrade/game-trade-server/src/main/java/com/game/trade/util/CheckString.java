package com.game.trade.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 检查字符串
 * 
 * @author antonio
 */
public class CheckString {

	/**
	 * 字符串中是否有重复字符
	 * 
	 * @param s
	 * @return
	 */
	public static boolean hasSameLetter(String s) {

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (s.indexOf(c, i + 1) > -1) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断集合中是否有重复
	 * 
	 * @param s
	 * @return
	 */
	public static boolean hasSameLetterStream(List<String> ls) {

		long distinctCount = ls.stream().distinct().count();

		if (distinctCount != ls.size())
			return true;

		return false;
	}

	/**
	 * 输入格式 "0123456,0123456";
	 * 
	 * @param ls
	 * @return
	 */
	public static boolean hasSameNum(List<String> ls) {

		List<Boolean> lss = ls.stream().map(s -> Arrays.asList(s.split("")))
				.map(s -> CheckString.hasSameLetterStream(s)).collect(Collectors.toList());

		Boolean b = lss.contains(true);

		return false;
	}

	/**
	 * 判断集合中是否有重复
	 * 
	 * @param s
	 * @return
	 */
	public static boolean hasSameLetterStream(List<String> ls, String letter) {

		long distinctCount = ls.stream().distinct().count();

		if (distinctCount != ls.size())
			return true;

		return false;
	}

}
