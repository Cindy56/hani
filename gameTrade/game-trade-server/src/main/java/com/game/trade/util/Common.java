package com.game.trade.util;

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

	/**
	 * 数是否大或者小
	 * 
	 * @param num
	 * @return 1big,0small
	 */
	static public int IsBigOrSmall(int num) {
		if (num > 4)
			return 1;
		return 0;
	}

	/**
	 * 数是否单或者双
	 * 
	 * @param num
	 * @return 1single,0small
	 */
	static public int IsSingelOrDouble(int num) {
		return (num % 2);
	}

	/**
	 * 求和值
	 * 
	 * @param array
	 * @return
	 */
	static public int SumOfArray(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}

		return sum;
	}
	
	/**
	 * 求和值
	 * 
	 * @param array
	 * @return
	 */
	static public int SumOfArray(int[] array, int n) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += array[i];
		}

		return sum;
	}

	/**
	 * 打印组成和值的所有可能排列
	 * 
	 * @param sum 和
	 * @param n   几个数求和
	 * @param ret   当前结果
	 * @param resCount 第几组结果 
	 * @return
	 */
	static public void PrintAllArrangeSum(int sum, int n,  int[] ret,  int[] resCount) {

		if (n <= 0)
		{
			System.out.println("why here?");
			return ;
		}

		if (n == 1) {
			// System.out.println(sum);
			
			if(sum > 9 )
				return;
			
			ret[3 - n] = sum;
			
			//System.out.print(ret[0]+","+ret[1]+","+ret[2]);
			
			//System.out.println(","+resCount[0]);
			
			resCount[0]++;
			//ret[0] = 0;
			ret[1] = 0;
			ret[2] = 0;
			
			
			return ;
		}

		for (int i = 0; i <= 9; i++) {
			// System.out.print(i+",");
			
			ret[3 - n] = i;
			
			if( i > sum )
				return;
			
			PrintAllArrangeSum(sum - i, n - 1, ret, resCount);
		}
		
		return ;

	}

	/**
	 * 打印组成和值的所有可能组合
	 * 
	 * @param sum
	 * @param n
	 * @return
	 */
	static public int PrintAllCombinationSum(int sum, int n) {

		// for( )
		return 0;

	}

}
