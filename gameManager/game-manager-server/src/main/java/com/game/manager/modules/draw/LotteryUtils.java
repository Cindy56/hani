package com.game.manager.modules.draw;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import freemarker.core.CollectionAndSequence;

public class LotteryUtils {
	public static void main(String[] args) {
//		System.out.println(LotteryUtils.ssc3XinZuxuan());
		
	}
	
	/**
	 * 时时彩直选 检查是否中奖
	 * @param openNum 开奖号码，格式为逗号分割“4,5,6”。
	 * @param betNum 投注号码，复试为逗号分割“234,567,678”，单式没有分隔符“456 789 258”
	 * @return
	 */
	public static boolean checkWinSscZhi(String openNum, String betNum) {
		String[] open = openNum.split(",");
		String[] bet =  betNum.contains(",") ? betNum.split(",") : betNum.split("");
		for (int i = 0; i < open.length; i++) {
			if(!StringUtils.contains(bet[i], open[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 时时彩直选 检查是否中奖
	 * @param openNum 开奖号码，格式为逗号分割“4,5,6”。
	 * @param betNum 投注号码，支持两种格式，用逗号分割 或者 用空格分割：单式没有分隔符“456 789 258”
	 * @return
	 */
	public static boolean checkWinSscZhiDan(String openNum, String betNum) {
		String[] betNumList = betNum.contains(",") ? betNum.trim().split(",") : betNum.trim().split(" ");
		if (0 == betNumList.length) {
			return false;
		}
		
		openNum = openNum.replace(",", "");
		for (String haoma : betNumList) {
			if(openNum.equals(haoma)) {
				return true;
			}
			
		}
		return false;
	}
	/**
	 * 检查时时彩组选6是否中奖，支持多星
	 * @param openNum 开奖号码，格式为逗号分割“4,5,6”。
	 * @param betNum 投注号码，没有分隔符“2345678”，
	 * @return
	 */
	public static boolean checkWinSscZu6(String openNum, String betNum) {
		String[] open = openNum.split(",");
		for (int i = 0; i < open.length; i++) {
			if(!StringUtils.contains(betNum, open[i])) {
				return false;
			}
		}
		return true;
	}
	
	/** 检查时时彩组选3是否中奖， 支持多星 */
	
	
    /**
     * 将数组String[]{1, 2, 3}格式化成 字符串123 
     * @param a
     * @return
     */
    public static String formatNumber(String[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax) {
            	 return b.toString();
            }
//            b.append(",");
        }
    }
	
	/**
	 * 格式如下:
	 * String[] bai = new String[]{"0","1","2","3","4","5","6","7","8","9"};//百位
	 * String[] shi = new String[]{"0","1","2","3","4","5","6","7","8","9"};//十位
	 * String[] ge = new String[]{"0","1","2","3","4","5","6","7","8","9"};//个位 
	 */
	public static Set<String> ssc3XinPailie(String[] bai, String[] shi, String[] ge) {
		Set<String> result = new TreeSet<>();
        for (int i = 0; i < bai.length; i++) {
        	for (int j = 0; j < shi.length; j++) {
        		for (int k = 0; k < ge.length; k++) {
        			StringBuilder temp = new StringBuilder();
        			temp.append(bai[i]).append(shi[j]).append(ge[k]);
        			result.add(temp.toString());
        		}
    		}
		}
        return result;
	}
	
	/**
	 * 3星直选大底全排列
	 * @return
	 */
	public static Set<String> ssc3XinPailie() {
		String[] bai = new String[]{"0","1","2","3","4","5","6","7","8","9"};//百位
		String[] shi = new String[]{"0","1","2","3","4","5","6","7","8","9"};//十位
		String[] ge = new String[]{"0","1","2","3","4","5","6","7","8","9"};//个位      
        return ssc3XinPailie(bai, shi, ge);
	}
	
	
	/**
	 *  3星所有的组选大底
	 */
	public static Set<String> ssc3XinZuxuan() {
		String[] bai = new String[]{"0","1","2","3","4","5","6","7","8","9"};//百位
		String[] shi = new String[]{"0","1","2","3","4","5","6","7","8","9"};//十位
		String[] ge = new String[]{"0","1","2","3","4","5","6","7","8","9"};//个位      
		
		Set<String> result = new TreeSet<>();
		Set<String> dadi = ssc3XinPailie(bai, shi, ge);
        for (String haoma : dadi) {
        	String[] xxx = haoma.split("");
        	Arrays.sort(xxx);
        	result.add(formatNumber(xxx));
		}
        return result;
	}	
	/**
	 * 时时彩3星组选3:3个号码里有相同的2个号码
	 */
	public static void ssc3XinZuxuan3() {
		//TODO:XXXXX
	}	
	/**
	 * 时时彩组选6：3个号码里没有相同的2个号码
	 */
	public static void ssc3XinZuxuan6() {
		//TODO:XXXXX		
	}	
	
	/**
	 * 时时彩组选豹子
	 */
	
	/**
	 * 排列2
	 * @return
	 */
	public static Set<String> ssc2XinPailie() {
		//TODO:xxxxx
		return null;
	}
//===============================================================排列组合公式
	
	/** 
	 * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1 
	 * @param n 
	 * @return 
	 */  
	private static long factorial(int n) {
	    return (n > 1) ? n * factorial(n - 1) : 1;  
	}  
	  
	/** 
	 * 计算排列数，即A(n, m) = n!/(n-m)! 
	 * @param n 
	 * @param m 
	 * @return 
	 */  
	public static long arrangement(int n, int m) {
	    return (n >= m) ? factorial(n) / factorial(n - m) : 0;  
	}  
	  
	/** 
	 * 计算组合数，即C(n, m) = n!/((n-m)! * m!) 
	 * @param n 
	 * @param m 
	 * @return 
	 */  
	public static long combination(int n, int m) {  
	    return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;  
	}  

//===============================================================排列组合集合
	/** 
	 * 排列选择（从列表中选择n的排列） 
	 * @param dataList 待选列表 
	 * @param n 选择个数 
	 */  
	public static void arrangementSelect(String[] dataList, int n) {  
//	    System.out.println(String.format("A(%d, %d) = %d", dataList.length, n, arrangement(dataList.length, n)));  
	    arrangementSelect(dataList, new String[n], 0);  
	}  
	  
	/** 
	 * 排列选择 
	 * @param dataList 待选列表 
	 * @param resultList 前面（resultIndex-1）个的排列结果 
	 * @param resultIndex 选择索引，从0开始 
	 */  
	private static void arrangementSelect(String[] dataList, String[] resultList, int resultIndex) {  
	    int resultLen = resultList.length;  
	    if (resultIndex >= resultLen) { // 全部选择完时，输出排列结果  
	        System.out.println(Arrays.asList(resultList));  
	        return;  
	    }  
	  
	    // 递归选择下一个  
	    for (int i = 0; i < dataList.length; i++) {  
	        // 判断待选项是否存在于排列结果中  
	        boolean exists = false;  
	        for (int j = 0; j < resultIndex; j++) {  
	            if (dataList[i].equals(resultList[j])) {  
	                exists = true;  
	                break;  
	            }  
	        }  
	        if (!exists) { // 排列结果不存在该项，才可选择  
	            resultList[resultIndex] = dataList[i];  
	            arrangementSelect(dataList, resultList, resultIndex + 1);  
	        }  
	    }  
	} 
	
	/** 
	 * 组合选择（从列表中选择n的组合） 
	 * @param dataList 待选列表 
	 * @param n 选择个数 
	 */  
	public static void combinationSelect(String[] dataList, int n) {  
//	    System.out.println(String.format("C(%d, %d) = %d", dataList.length, n, combination(dataList.length, n)));  
	    combinationSelect(dataList, 0, new String[n], 0);  
	}  
	  
	/** 
	 * 组合选择 
	 * @param dataList 待选列表 
	 * @param dataIndex 待选开始索引 
	 * @param resultList 前面（resultIndex-1）个的组合结果 
	 * @param resultIndex 选择索引，从0开始 
	 */  
	private static void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {  
	    int resultLen = resultList.length;  
	    int resultCount = resultIndex + 1;  
	    if (resultCount > resultLen) { // 全部选择完时，输出组合结果  
	        System.out.println(Arrays.asList(resultList));  
	        return;  
	    }  
	  
	    // 递归选择下一个  
	    for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {  
	        resultList[resultIndex] = dataList[i];  
	        combinationSelect(dataList, i + 1, resultList, resultIndex + 1);  
	    }  
	}  
}
