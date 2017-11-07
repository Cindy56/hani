package com.game.draw.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Common {

	/**
	 * 数是否在数组里
	 * 
	 * @param num
	 * @param array0
	 * @return
	 */
	static public boolean IsInArray(int num, int[] array0) {
		for (int i = 0; i < array0.length; i++) {
			if (num == array0[i])
				return true;
		}

		return false;
	}

	/**
	 * 数在数组里出现的次数
	 * 
	 * @param num
	 * @param array0
	 * @return
	 */
	static public int CountInArray(int num, int[] array0) {
		int count = 0;
		for (int i = 0; i < array0.length; i++) {
			if (num == array0[i])
				count++;
		}

		return count;
	}

	/**
	 * 数组中重复的最大个数
	 * 
	 * @param array0
	 * @return
	 */
	static public int SameInArray(int[] array0) {

		Map<Integer, Integer> map = new HashMap<>();

		// Map<String, Integer> map = new HashMap<String, Integer>();
		for (Integer s : array0) {
			if (map.get(s) != null) {
				map.put(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
		}

		int[] keys = new int[map.size()];

		// for (int i = 0; i < keys.length; i++) {
		Collection<Integer> colI = map.values();
		// Iterator<Integer> itI = colI.iterator();

		int i = 0;
		for (Iterator iter = colI.iterator(); iter.hasNext();) {
			Integer thisI = (Integer) iter.next();
			keys[i] = thisI;
			i++;
		}

		// keys[i] = colI.iterator().next();

		Arrays.sort(keys);

		// System.out.println(map.get(count_2));

		// int[] array = {1,1,1,5,5,8,9}

		return keys[keys.length - 1];
	}

	/**
	 * 是否顺子
	 * 
	 * @param array0
	 * @return
	 */
	static public boolean IsSequence(int[] array0) {

		// boolean isSeq = false;
		int len = array0.length;
		for (int i = 0; i < len - 1; i++) {
			if (array0[i] + 1 != array0[i + 1]) {
				return false;
			}
		}

		return true;

	}

}
