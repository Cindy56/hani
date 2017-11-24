package com.game.trade.modules.lottery.manager;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.game.modules.order.entity.LotteryOrder;

/**
 * 算奖工具类
 * @author Terry
 */
public class LotteryUtils {

    // -------------------------------是否中奖判断-----------------------------------------

    /**
     * 时时彩一星复试投注 判断是否中奖
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSsc1XingDingWei(String openNum, String betNum) {
        return winCountSsc1XinDingWei(openNum, betNum) > 0;
    }

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
        String[] betNumList = betNum.trim().split(",");
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
     * @param betNum 投注号码，格式为逗号分割（234,567,678）
     * @return
     */
    public static boolean checkWinSscZhiXuanFu(String openNum, String betNum) {
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] openArr = openNum.split(",");
        String[] betArr = betNum.contains(",") ? betNum.split(",") : new String[] {};
        for (int i = 0; i < openArr.length; i++) {
            if (!StringUtils.contains(betArr[i], openArr[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时时彩2星组选复式 判断是否中奖
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSsc2XingZuXuanFu(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        // 判断开奖号码是否是对子，是则直接返回false
        String[] openArr = openNum.split(",");
        String numFirst = openArr[0];
        String numSecend = openArr[1];
        // 确保开奖号码没有问题
        if (StringUtils.isBlank(numFirst) || StringUtils.isBlank(numSecend)) {
            return false;
        }
        // 排除对子
        if (numFirst.equals(numSecend)) {
            return false;
        }
        // 同时包含开奖号码，即为中奖
        if (StringUtils.contains(betNum, numFirst) && StringUtils.contains(betNum, numSecend)) {
            return true;
        }
        return false;
    }

    /**
     * 时时彩3星组选3 判断是否中奖
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSsc3XingZuXuan3(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        // 判断开奖号码是否有组三，没有直接返回false
        String[] openArr = openNum.split(",");
        int countFirst = openNum.length() - openNum.replace(openArr[0], "").length();
        if (2 != countFirst) {
            int countSecond = openNum.length() - openNum.replace(openArr[1], "").length();
            if (2 != countSecond) {
                return false;
            }
        }
        String[] betArr = betNum.contains(",") ? betNum.split(",") : new String[] {};
        for (String open : openArr) {
            if (ArrayUtils.contains(betArr, open)) {
                return true;
            }
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
        String[] betNumList = betDetail.contains(",") ? betDetail.trim().split(",") : new String[] {};
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
        String[] betNumList = betDetail.trim().split(",");
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
        String[] betNumList = betDetail.contains(",") ? betDetail.trim().split(",") : new String[] {};
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

    /**
     * 时时彩2星组选复式 投注注数
     * @return 通过调用排列组合计算函数计算的投注注数
     * @author Terry
     */
    public static int orderCountSsc2XingZuXuanFu(String betDetail) {
        if (StringUtils.isBlank(betDetail)) {
            return 0;
        }
        String[] betNumList = betDetail.contains(",") ? betDetail.trim().split(",") : new String[] {};
        if (2 <= betNumList.length) {
            return 0;
        }
        int count = new Long(combination(betNumList.length, 2)).intValue();
        return count;
    }

    // -------------------------------中奖注数计算------------------------------------------------

    /**
     * 时时彩一星定位胆 计算中奖注数
     * @param openNum 开奖号码,五个位（万,千,百,十,个）,位与位之间以英文逗号隔开
     * @param betNum 投注号码，五个位（万,千,百,十,个），位与位之间以英文逗号隔开,空位以减号表示
     * @return 中奖返回true
     * @author Terry
     */
    public static int winCountSsc1XinDingWei(String openNum, String betNum) {
        // 参数不合法，返回0
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return 0;
        }
        String[] betNumList = betNum.contains(",") ? betNum.trim().split(",") : new String[] {};
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

    // -------------------------------其他校验方法------------------------------------------------

    /**
     * 校验同一投注位不出现重复号码，例如：一行定位胆投注不允许出现 122,2,-,3,4
     * @param betNumber 投注号码
     * @return 返回校验结果，返回true校验通过
     * @author Terry
     */
    public static boolean checkRepeatNumber(String betNumber) {
        String[] betArr = betNumber.contains(",") ? betNumber.trim().split(",") : new String[] {};
        if (ArrayUtils.isEmpty(betArr)) {
            return false;
        }
        for (String bet : betArr) {
            if ("-".equals(bet) || 1 == bet.length()) {
                continue;
            }
            String[] tmpArr = bet.split("");
            for (String string : tmpArr) {
                int count = bet.length() - bet.replace(string, "").length();
                if (1 != count) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 校验单式投注不允许出现相同投注号码，如二星直选单式不允许出现 22,23,22
     * @param betNumber 投注内容
     * @return 校验通过返回true
     * @author Terry
     */
    public static boolean checkRepeatBet(String betNumber) {
        String[] betArr = betNumber.contains(",") ? betNumber.trim().split(",") : new String[] {};
        if (ArrayUtils.isEmpty(betArr)) {
            return false;
        }
        Set<String> set = new HashSet<>(Arrays.asList(betArr));
        if (set.size() == betArr.length) {
            return true;
        }
        return false;
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
     * 
	 * 时时彩前三混合组选：
	 */
	public static boolean ssc3XinHunHeZuXuan(String openNum,String betNum) {
		 // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] betNumArr = betNum.trim().split(",");
        List<String> betNumList = Arrays.asList(betNumArr);
        //检查组3是否中奖
       if(ssc3XinHunHeZuXuanDuiZi(openNum,betNum)) {
    	   return true;
       }
        for(String bet:betNumList) {
        	//判断是否有重复
        	long count = Arrays.asList(bet.split("")).stream().distinct().count();
        	if(count < 3) {
        		continue;
        	}
        	if(ssc3XinZuxuan6(openNum,StringUtils.join(bet.split(""),","))) {
        		return true;
        	}
        }
		return false;
	}	
	/**
	 * 开奖号码有对子
	 */
	public  static boolean ssc3XinHunHeZuXuanDuiZi(String openNum,String betNum) {
		  String[] betNumArr = betNum.trim().split(",");
	      String[] openNumArr = openNum.trim().split(",");
	      List<String> betNumList = Arrays.asList(betNumArr);
	      List<String> openNumList = Arrays.asList(openNumArr);
	        //检查组3是否中奖
	      boolean flag = false;
	      for(String bet:betNumList) {
	        	String[] betArr = bet.split("");
	        	//判断是否有重复
	        	long count = Arrays.asList(betArr).stream().distinct().count();
	        	if(count > 3) {
	        		continue;
	        	}
	        	// 开奖号码的前三位相同，顺序不限
	    		if(openNumList.contains(betArr[0]) &&
	    				openNumList.contains(betArr[1]) &&
	    				openNumList.contains(betArr[2])) {
	    			return true;
	    		}
		   }
		
		return flag;
	}
	
    /**
     * 
	 * 时时彩组选6：前3组选6 所选号码与开奖号码的前三位相同，顺序不限(前三、中三、后三通用 ,号码截取不一样)
	 */
	public static boolean ssc3XinZuxuan6(String openNum,String betNum) {
		 // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] betNumArr = betNum.contains(",") ? betNum.trim().split(",") : betNum.trim().split(" ");
        List<String> betNumList = Arrays.asList(betNumArr);
        // 开奖号码截取前三位 进行比较
        String[] openNums = openNum.split(",");
        List<String> openNumsList = Arrays.asList(openNums);
        //开奖号码有对子,不中奖
        long count = openNumsList.stream().distinct().count();
        if(count < 3) {
        	return false;
        }
		// 开奖号码的前三位相同，顺序不限
		if(betNumList.contains(openNumsList.get(0)) &&
				betNumList.contains(openNumsList.get(1)) &&
				betNumList.contains(openNumsList.get(2))) {
			return true;
		}
		return false;
	}	
	
	
	
	
    /**
     * 
	 * 时时彩组选和值：前3组选和值 所选数值等于开奖号码百位、十位、个位三个数字相加之和（不含豹子）即中奖
	 */
	public static boolean ssc3XinZuxuanHeZhi(String openNum,String betNum) {
		 // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] openNums = openNum.split(",");
        List<Integer> openNumsList = Arrays.asList(openNums).stream().map(Integer::valueOf).collect(Collectors.toList());
        String[] betNumArr = betNum.trim().split(",");
        List<String> betNumsList = Arrays.asList(betNumArr);
        //出现豹子，不中奖
        if(openNumsList.get(0) == openNumsList.get(1) && openNumsList.get(0) == openNumsList.get(2)) {
        	return false;
        }
        String openSum = openNumsList.stream().reduce(0, (sum,sum1)->sum+sum1).toString();
        if(betNumsList.contains(openSum)) {
        	return true;
        }
		return false;
	}	
	
	/**
     * 
	 * 时时彩前3  和值尾数：从0-9中任意选择一个号码，所选数值等于开奖号码的百位、十位、个位三个数字相加之和的尾数，即中奖
	 */
	public static boolean ssc3XinHeZhiWeiShu(String openNum,String betNum) {
		 // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] betNumArr = betNum.contains(",") ? betNum.trim().split(",") : betNum.trim().split(" ");
        List<String> betNumList = Arrays.asList(betNumArr);
        // 开奖号码截取前三位 进行比较
        String[] openNums = openNum.split(",");
        List<Integer> openNumsList = Arrays.asList(openNums).stream().map(Integer::valueOf).collect(Collectors.toList());
        String openSum = openNumsList.stream().reduce(0, (sum,sum1)->sum+sum1).toString();
        //截取最后一位比较
        String subOpenSum = openSum.substring(openSum.length()-1);
        if(betNumList.contains(subOpenSum)) {
        	return true;
        }
		return false;
	}
	
	/**
     * 
	 * 时时彩前3 - 豹子：三个位开出的结果相同
	 */
	public static boolean ssc3XinBaoZi(String openNum,String betNum) {
		 // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] betNumArr = betNum.contains(",") ? betNum.trim().split(",") : betNum.trim().split(" ");
        List<String> betNumList = Arrays.asList(betNumArr);
        // 开奖号码截取前三位 进行比较
        String[] openNums = openNum.split(",");
        List<String> openNumsList = Arrays.asList(openNums);
        long count =  openNumsList.stream().distinct().count();
        if(count == 1) {
        	if(betNumList.contains(openNumsList.get(0))) {
        		return true;
        	}
        }
		return false;
	}
	

    /**
     * 
	 * 时时彩前3直选和值：前3直选和值 所选数值等于开奖号码百位、十位、个位三个数字相加之和即中奖
	 */
	public static boolean ssc3XinZhiXuanHeZhi(String openNum,String betNum) {
		 // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] openNums = openNum.split(",");
        List<Integer> openNumsList = Arrays.asList(openNums).stream().map(Integer::valueOf).collect(Collectors.toList());
        String[] betNumArr = betNum.trim().split(",");
        List<String> betNumsList = Arrays.asList(betNumArr);
        String openSum = openNumsList.stream().reduce(0, (sum,sum1)->sum+sum1).toString();
        if(betNumsList.contains(openSum)) {
        	return true;
        }
		return false;
	}	
	
	
    /**
     * 
	 * 时时彩组选6：前3组选6 计算注数
	 */
	public static int ssc3XinBetCount(LotteryOrder lotteryOrder) {
		 // 参数不合法，返回false
        if (null  == lotteryOrder) {
            return 0;
        }
        // 获取投注倍数、单注金额
        BigDecimal betRate =new BigDecimal(lotteryOrder.getBetRate());
        BigDecimal price = new BigDecimal(2);
        BigDecimal resule =BigDecimal.ZERO;
       if("0".equals(lotteryOrder.getPlayModeMoneyType())) {//元
     	   resule = lotteryOrder.getBetAmount().divide(betRate).divide(price).multiply(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_DOWN);
        }else if("1".equals(lotteryOrder.getPlayModeMoneyType())) {//角
     	   resule = lotteryOrder.getBetAmount().divide(betRate).divide(price).multiply(new BigDecimal(10)).setScale(4,BigDecimal.ROUND_HALF_DOWN);
        }else if("2".equals(lotteryOrder.getPlayModeMoneyType())) {//分
      	   resule = lotteryOrder.getBetAmount().divide(betRate).divide(price).multiply(new BigDecimal(100)).setScale(4,BigDecimal.ROUND_HALF_DOWN);
        }
		return resule.intValue();
	}	
	
	/**
     * 
	 * 时时彩4星组4：
	 */
	public static boolean ssc4XinZuXuan4(String openNum,String betNum) {
		 // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] openNums = openNum.split(",");
        List<String> openNumsList = Arrays.asList(openNums);
        String[] betNumArr = betNum.trim().split(",");
        //取三重号 1 2 2 2
        long count =  openNumsList.stream().distinct().count();
        if(count != 2) {//没有三重号直接返回
        	return false;
        }
        //统计出现的次数
        boolean flag = false;
        Map<String, Long> map = openNumsList.stream().collect(Collectors.groupingBy(p -> p,Collectors.counting()));
        List<String> betNumsList = Arrays.asList(betNumArr[0].split(""));
        for (String bet : betNumsList) {
			if(map.containsKey(bet)) {
				long countNum = map.get(bet);
				if(countNum == 3) {
					flag = true;
					break;
				}
			}
		}
        //取单号
        if(flag) {
        	 List<String> delayNumsList = Arrays.asList(betNumArr[1].split(""));
        	 for (String delay : delayNumsList) {
     			if(map.containsKey(delay)) {
     				long countNum = map.get(delay);
     				if(countNum == 1) {
     					return true;
     				}
     			}
     		}
        }
		return false;
	}
	
	
	public static void main(String[] args) {
		String s = "1,2,3,3,3";
		System.out.println(s.substring(s.length()-7));
		System.out.println(ssc4XinZuXuan4(s.substring(s.length()-6),"03,120"));
		
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
