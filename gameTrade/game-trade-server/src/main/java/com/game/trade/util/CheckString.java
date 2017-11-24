package com.game.trade.util;

import java.util.List;

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

}
