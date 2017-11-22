package com.game.manager.modules.draw;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 算奖工具类
 * @author Terry
 */
public class LotteryUtils {
    // -------------------------------是否中奖判断-----------------------------------------
    /**
     * 时时彩直选单式 检查是否中奖
     * @param openNum 开奖号码，格式为逗号分割“4,5,6”。
     * @param betNum 投注号码，没有分隔符“456 789 258”，多注以空格分开
     * @return
     */
    public static boolean checkWinSscZhiXuanDan(String openNum, String betNum) {
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] betNumList = betNum.trim().split(" ");
        if (0 == betNumList.length) {
            return false;
        }
        openNum = openNum.replace(",", "");
        if (ArrayUtils.contains(betNumList, openNum)) {
            return true;
        }
        return false;
    }

    /**
     * 时时彩直选复式 检查是否中奖
     * @param openNum 开奖号码，格式为逗号分割“4,5,6”。
     * @param betNum 投注号码，格式为逗号分割（234,567,678）或空格分割（234 567 678）
     * @return
     */
    public static boolean checkWinSscZhiXuanFu(String openNum, String betNum) {
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] open = openNum.split(",");
        String[] bet = betNum.contains(",") ? betNum.split(",") : betNum.split(" ");
        for (int i = 0; i < open.length; i++) {
            if (!StringUtils.contains(bet[i], open[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时时彩3星组选 判断是否中奖
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSsc3XinZuXuan(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        // 格式化开奖号码
        openNum = formatNumber(openNum.split(","));
        // 生成用户投注号码
        Set<String> betSet = ssc3XinZuXuan3(betNum);
        // 迭代匹配，只要有一个用户投注号码是获奖投注，返回true
        if (betSet.contains(openNum)) {
            return true;
        }
        return false;
    }

    // -------------------------------投注注数计算-----------------------------------------

    /**
     * 时时彩1星直选 计算投注注数
     * @param betDetail 投注内容
     * @return 返回注单实际投注注数
     * @author Terry
     */
    public static int orderCount1XingZhiXuan(String betDetail) {
        // 参数非法直接返回0注
        if (StringUtils.isBlank(betDetail)) {
            return 0;
        }
        String[] betNumList = betDetail.contains(",") ? betDetail.trim().split(",") : betDetail.trim().split(" ");
        if (0 == betNumList.length) {
            return 0;
        }
        // 不为空的位置长度相加
        int count = 0;
        for (String string : betNumList) {
            // 空位跳过
            if ("-".equals(string)) {
                continue;
            }
            count += string.length();
        }
        return count;
    }

    /**
     * 时时彩直选单式 计算投注注数
     * @param betDetail 投注内容
     * @return 返回注单实际投注注数
     * @author Terry
     */
    public static int orderCountSscZhiXuanDan(String betDetail) {
        if (StringUtils.isBlank(betDetail)) {
            return 0;
        }
        String[] betNumList = betDetail.trim().split(" ");
        return betNumList.length;
    }

    /**
     * 时时彩直选复式 计算投注注数
     * @param betDetail 投注内容
     * @return 返回注单实际投注注数
     * @author Terry
     */
    public static int orderCountSscZhiXuanFu(String betDetail) {
        if (StringUtils.isBlank(betDetail)) {
            return 0;
        }
        String[] betNumList = betDetail.contains(",") ? betDetail.trim().split(",") : betDetail.trim().split(" ");
        if (0 == betNumList.length) {
            return 0;
        }
        // 不为空的位置长度相加
        int count = 1;
        for (String string : betNumList) {
            // 空位跳过
            if ("-".equals(string)) {
                continue;
            }
            count *= string.length();
        }
        return count;
    }

    // -------------------------------中奖注数计算------------------------------------------------

    /**
     * 时时彩一星定位胆 计算中奖注数
     * @param openNum 开奖号码,五个位（万,千,百,十,个）,位与位之间以英文逗号隔开
     * @param betNum 投注号码，五个位（万,千,百,十,个），位与位之间以英文逗号或空格字符隔开
     * @return 中奖返回true
     * @author Terry
     */
    public static int winCountSsc1XinDingWei(String openNum, String betNum) {
        // 参数不合法，返回0
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return 0;
        }
        String[] betNumList = betNum.contains(",") ? betNum.trim().split(",") : betNum.trim().split(" ");
        if (5 != betNumList.length) {
            return 0;
        }
        // 每个位的号码分别判断是否包含对应的开奖号码
        int count = 0;
        String[] openNums = openNum.trim().split(",");
        for (int i = 0; i < openNums.length; i++) {
            // 只要有一个包含就判断为中奖
            if (StringUtils.contains(betNumList[i], openNums[i])) {
                count++;
            }
        }
        // 没有一个位包含对应的开奖号码，返回false
        return count;
    }

    /**
     * 时时彩三星组三 计算中奖注数
     * @param betDetail 投注内容
     * @return 返回注单实际投注注数
     * @author Terry
     */
    public static int winCountSsc3XingZu3(String openNum, String betNum) {
        int count = 0;
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return count;
        }
        openNum = formatNumber(openNum.split(","));
        Set<String> betSet = ssc3XinZuXuan3(betNum);
        if (betSet.contains(openNum)) {
            count++;
        }
        return count;
    }

    // -------------------------------|------------------------------------------------

    /**
     * 检查时时彩组选6是否中奖，支持多星
     * @param openNum 开奖号码，格式为逗号分割“4,5,6”。
     * @param betNum 投注号码，格式为逗号分割“4,5,6”。
     * @return
     */
    public static boolean checkWinSscZu6(String openNum, String betNum) {
        String[] open = openNum.split(",");
        for (int i = 0; i < open.length; i++) {
            if (!StringUtils.contains(betNum, open[i])) {
                return false;
            }
        }
        return true;
    }

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
     * @return 三星组三大底
     */
    public static Set<String> ssc3XinZuXuan3() {
        return ssc3XinZuXuan3("0,1,2,3,4,5,6,7,8,9");
    }

    /**
     * 时时彩3星组选3:3个号码里有相同的2个号码（指定基数数组）
     * @return 三星组三大底
     */
    public static Set<String> ssc3XinZuXuan3(String baseStr) {
        Set<String> result = new TreeSet<>();
        if (StringUtils.isBlank(baseStr)) {
            return result;
        }
        String[] baseArr = baseStr.trim().split(",");
        for (int i = 0; i < baseArr.length; i++) {
            for (int j = 0; j < baseArr.length; j++) {
                // 避免出现豹子
                if (i == j) {
                    continue;
                }
                // 如果不同位置出现相同号码，说明注单有误
                if (baseArr[i].equals(baseArr[j])) {
                    result = new TreeSet<>();
                    return result;
                }
                // 头中相同
                StringBuilder temp = new StringBuilder();
                temp.append(baseArr[i]).append(baseArr[i]).append(baseArr[j]);
                result.add(temp.toString());
                temp = new StringBuilder();
                // 头尾相同
                temp.append(baseArr[i]).append(baseArr[j]).append(baseArr[i]);
                result.add(temp.toString());
                // 中尾相同;
                temp = new StringBuilder();
                temp.append(baseArr[j]).append(baseArr[i]).append(baseArr[i]);
                result.add(temp.toString());
            }
        }
        return result;
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
