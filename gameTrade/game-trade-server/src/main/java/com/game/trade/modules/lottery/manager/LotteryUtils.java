package com.game.trade.modules.lottery.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.game.modules.order.entity.LotteryOrder;
import com.game.trade.model.OpenLottery;
import com.game.trade.model.Star5;
import com.game.trade.util.Combination;

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
     * @param openNum 开奖号码，格式为逗号分割（4,5,6）
     * @param betNum 投注号码，没有分隔符（456,789,258），多注以逗号分开
     * @return 中奖返回true
     */
    public static boolean checkWinSscZhiXuanDan(String openNum, String betNum) {
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] betNumList = betNum.split(",");
        openNum = openNum.replace(",", "");
        if (ArrayUtils.contains(betNumList, openNum)) {
            return true;
        }
        return false;
    }

    /**
     * 时时彩直选复式 检查是否中奖
     * @param openNum 开奖号码，格式为逗号分割（4,5,6）
     * @param betNum 投注号码，格式为逗号分割（234,567,678）
     * @return 中奖返回true
     */
    public static boolean checkWinSscZhiXuanFu(String openNum, String betNum) {
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] openArr = openNum.split(",");
        String[] betArr = betNum.split(",");
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
        List<String> openNumList = Arrays.asList(openNum.split(","));
        Map<String, Long> map = openNumList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        if (2 != map.size()) {
            return false;
        }

        // 判断开奖号码是否存在于投注号码中，只要一个不存在就不中奖
        Set<String> set = map.keySet();
        for (String open : set) {
            if (!StringUtils.contains(betNum, open)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时时彩组选6：前3组选6 所选号码与开奖号码的前三位相同，顺序不限(前三、中三、后三通用 ,号码截取不一样)
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Jerry
     */
    public static boolean checkWinSsc3XingZuxuan6(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] betNumArr = betNum.split(",");
        List<String> betNumList = Arrays.asList(betNumArr);
        // 开奖号码截取前三位 进行比较
        String[] openNums = openNum.split(",");
        List<String> openNumsList = Arrays.asList(openNums);
        // 开奖号码有对子,不中奖
        long count = openNumsList.stream().distinct().count();
        if (count < 3) {
            return false;
        }
        // 开奖号码的前三位相同，顺序不限
        if (betNumList.contains(openNumsList.get(0)) && betNumList.contains(openNumsList.get(1)) && betNumList.contains(openNumsList.get(2))) {
            return true;
        }
        return false;
    }

    /**
     * 时时彩三星混合组选
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 开奖返回true
     * @author Jerry
     */
    public static boolean checkWinSsc3XingZuXuanHun(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }

        // 判断组3是否中奖
        if (checkWinSsc3XingHunHeZuXuan3(openNum, betNum)) {
            return true;
        }

        // 判断组6是否中奖
        String[] betNumArr = betNum.trim().split(",");
        List<String> betNumList = Arrays.asList(betNumArr);
        for (String bet : betNumList) {
            // 判断是否有重复
            long count = Arrays.asList(bet.split("")).stream().distinct().count();
            if (count == 3) {
                continue;
            }
            if (checkWinSsc3XingZuxuan6(openNum, StringUtils.join(bet.split(""), ","))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 时时彩前3 - 豹子：三个位开出的结果相同
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Jerry
     */
    public static boolean checkWinSsc3XingBaoZi(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] betNumArr = betNum.contains(",") ? betNum.trim().split(",") : betNum.trim().split(" ");
        List<String> betNumList = Arrays.asList(betNumArr);
        // 开奖号码截取前三位 进行比较
        String[] openNums = openNum.split(",");
        List<String> openNumsList = Arrays.asList(openNums);
        long count = openNumsList.stream().distinct().count();
        if (count == 1) {
            if (betNumList.contains(openNumsList.get(0))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 时时彩组选和值：前3组选和值 所选数值等于开奖号码百位、十位、个位三个数字相加之和（不含豹子）即中奖
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Jerry
     */
    public static boolean checkWinSsc3XingZuXuanHeZhi(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] openNums = openNum.split(",");
        List<Integer> openNumsList = Arrays.asList(openNums).stream().map(Integer::valueOf).collect(Collectors.toList());
        String[] betNumArr = betNum.trim().split(",");
        List<String> betNumsList = Arrays.asList(betNumArr);
        // 出现豹子，不中奖
        if (openNumsList.get(0) == openNumsList.get(1) && openNumsList.get(0) == openNumsList.get(2)) {
            return false;
        }
        String openSum = openNumsList.stream().reduce(0, (sum, sum1) -> sum + sum1).toString();
        if (betNumsList.contains(openSum)) {
            return true;
        }
        return false;
    }

    /**
     * 时时彩前3 和值尾数：从0-9中任意选择一个号码，所选数值等于开奖号码的百位、十位、个位三个数字相加之和的尾数，即中奖
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Jerry
     */
    public static boolean checkWinSsc3XingHeZhiWeiShu(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        // 计算开奖和值
        String[] openNums = openNum.split(",");
        List<Integer> openNumsList = Arrays.asList(openNums).stream().map(Integer::valueOf).collect(Collectors.toList());
        String openSum = openNumsList.stream().reduce(0, (sum, sum1) -> sum + sum1).toString();
        // 截取最后一位比较
        String[] betNumArr = betNum.split(",");
        List<String> betNumList = Arrays.asList(betNumArr);
        String subOpenSum = openSum.substring(openSum.length() - 1);
        if (betNumList.contains(subOpenSum)) {
            return true;
        }
        return false;
    }

    /**
     * 时时彩直选和值
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Jerry
     */
    public static boolean checkWinSscZhiXuanHe(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] openNums = openNum.split(",");
        List<Integer> openNumsList = Arrays.asList(openNums).stream().map(Integer::valueOf).collect(Collectors.toList());
        String[] betNumArr = betNum.trim().split(",");
        List<String> betNumsList = Arrays.asList(betNumArr);
        String openSum = openNumsList.stream().reduce(0, (sum, sum1) -> sum + sum1).toString();
        if (betNumsList.contains(openSum)) {
            return true;
        }
        return false;
    }

    /**
     * 供混合组选调用，判断号码中是否存在组三，组三是否中奖
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Jerry
     */
    public static boolean checkWinSsc3XingHunHeZuXuan3(String openNum, String betNum) {
        String[] betNumArr = betNum.trim().split(",");
        String[] openNumArr = openNum.trim().split(",");
        List<String> betNumList = Arrays.asList(betNumArr);
        List<String> openNumList = Arrays.asList(openNumArr);
        // 检查组3是否中奖
        boolean flag = false;
        for (String bet : betNumList) {
            String[] betArr = bet.split("");
            // 判断是否有重复
            long count = Arrays.asList(betArr).stream().distinct().count();
            if (count == 3) {
                continue;
            }
            // 开奖号码的前三位相同，顺序不限
            if (openNumList.contains(betArr[0]) && openNumList.contains(betArr[1]) && openNumList.contains(betArr[2])) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 时时彩 4星组4 5星组选20
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @param ident 3 ：组20 2 ：组4
     * @return 中奖返回true
     * @author Jerry
     */
    public static boolean checkWinSscZuXuan4_20(String openNum, String betNum, int ident) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum) && (ident != 3 || ident != 2)) {
            return false;
        }
        // 取三重号 1 2 2 2
        Map<String, Long> map = Arrays.asList(openNum.split(",")).stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        if (map.size() != ident) {// 没有三重号直接返回
            return false;
        }
        boolean flag = false;
        for (String key : map.keySet()) {
            if (map.get(key) == 3) {
                flag = true;
                break;
            }
        }
        if (flag) {
            String[] betNumArr = betNum.trim().split(",");
            // 统计出现的次数
            boolean betFlag = false;
            for (String key : map.keySet()) {
                if (map.get(key) == 3 && StringUtils.contains(betNumArr[0], key)) {
                    map.remove(key);
                    betFlag = true;
                    break;
                }
            }
            if (betFlag) {
                for (String key : map.keySet()) {
                    if (!StringUtils.contains(betNumArr[1], key)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 时时彩4星组6
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Jerry
     */
    public static boolean checkWinSsc4XingZuXuan6(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] openNums = openNum.split(",");
        List<String> openNumsList = Arrays.asList(openNums);
        // 2个二重号码 3 3 2 2 ,
        Map<String, Long> map = openNumsList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        if (map.size() != 2) {// 没有二重号直接返回
            return false;
        }
        for (String key : map.keySet()) {
            if (map.get(key) != 2) {
                return false;
            }
        }
        String[] betNumArr = betNum.trim().split("");
        List<String> betNumsList = Arrays.asList(betNumArr);
        for (String key : map.keySet()) {
            if (!betNumsList.contains(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时时彩4星组12
     * @param openNum 开奖号码
     * @param betNum 投注号码
     * @return 中奖返回true
     * @author Jerry
     */
    public static boolean checkWinSsc4XingZuXuan12(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        String[] openNums = openNum.split(",");
        List<String> openNumsList = Arrays.asList(openNums);
        // 1个二重号码和2个单号号码 :3 4 2 2
        Map<String, Long> map = openNumsList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        if (map.size() != 3) {// 没有二重号直接返回
            return false;
        }

        String[] betNumArr = betNum.split(",");
        boolean flag = true;
        for (String key : map.keySet()) {
            if (map.get(key) == 2 && StringUtils.contains(betNumArr[0], key)) {
                map.remove(key);
                flag = true;
                break;
            }
        }
        if (flag) {
            boolean betFale = false;
            for (String key : map.keySet()) {
                if (map.get(key) == 1 && StringUtils.contains(betNumArr[1], key)) {
                    betFale = true;
                } else {
                    betFale = false;
                    break;
                }
            }
            return betFale;
        }
        return false;
    }

    /**
     * 时时彩 5星组选120 4星组选24 判断是否中奖
     * @param openNum 开奖号码，五个位以逗号隔开
     * @param betNum 投注号码，至少五个号码，以逗号隔开
     * @param groupSize 投注位数标识，5星为5，4星为4
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSscZuXuan24_120(String openNum, String betNum, int groupSize) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }

        // 判断开奖号码中的号码是否出现重复，有则直接返回false
        List<String> openNumList = Arrays.asList(openNum.split(","));
        Map<String, Long> map = openNumList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        if (groupSize != map.size()) {
            return false;
        }

        // 如果判断的号码没有出现重复则继续判断该位号码是否存在于投注号码，不存在同样不中奖，返回false
        for (String open : openNumList) {
            if (!StringUtils.contains(betNum, open)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时时彩5星组选60 判断是否中奖
     * @param openNum 开奖号码，五个位以逗号隔开
     * @param betNum 投注号码，至少五个号码，以逗号隔开
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSsc5XingZuXuan60(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }

        // 开奖号码有且仅有1个号码重复，否则不是组60，直接返回false
        String[] openArr = openNum.split(",");
        List<String> openNumList = Arrays.asList(openArr);
        Map<String, Long> map = openNumList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        if (4 != map.size()) {
            return false;
        }

        // 取出开奖号码中的二重号，判断是否存在与投注号码中，如果不存在，肯定不中奖，返回false
        String[] butArr = betNum.split(",");
        Set<Entry<String, Long>> groupMap = map.entrySet();
        for (Entry<String, Long> entry : groupMap) {
            // 如果存在一个二重号（经过前面的过滤，这里肯定有且仅有一个二重号），但是投注号码中没有，直接返回false
            if (2 == entry.getValue()) {
                if (!StringUtils.contains(betNum, entry.getKey())) {
                    return false;
                }
                map.remove(entry.getKey());
                break;
            }
        }

        // 从开奖号码中取出剩余的单号，依次判断是否存在于投注号码的单号内，只要一个不存在就不中奖
        String aloneNum = butArr[1];
        Set<String> set = map.keySet();
        for (String alone : set) {
            if (!StringUtils.contains(aloneNum, alone)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时时彩5星组选30 判断是否中奖
     * @param openNum 开奖号码，五个位以逗号隔开
     * @param betNum 投注号码，至少五个号码，以逗号隔开
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSsc5XingZuXuan30(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }

        // 开奖号码有且仅有2个二重号，否则不是组30，直接返回false
        String[] openArr = openNum.split(",");
        List<String> openNumList = Arrays.asList(openArr);
        Map<String, Long> map = openNumList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        if (3 != map.size()) {
            return false;
        }
        Set<Entry<String, Long>> groupSet = map.entrySet();
        List<String> list = new ArrayList<>();
        for (Entry<String, Long> entry : groupSet) {
            if (2 == entry.getValue()) {
                list.add(entry.getKey());
            }
        }
        if (2 != list.size()) {
            return false;
        }

        // 判断出现的二重号是否存在与投注号码的二重号中，只要又一个没有，直接返回false
        String[] butArr = betNum.split(",");
        String doubleArr = butArr[0];
        for (String doubleNum : list) {
            if (!StringUtils.contains(doubleArr, doubleNum)) {
                return false;
            }
            map.remove(doubleNum);
        }

        // 从开奖号码中取出剩余的单号，依次判断是否存在于投注号码的单号内，只要一个不存在就不中奖
        String aloneNum = butArr[1];
        Set<String> set = map.keySet();
        for (String alone : set) {
            if (!StringUtils.contains(aloneNum, alone)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时时彩5星组选10 判断是否中奖
     * @param openNum 开奖号码，五个位以逗号隔开
     * @param betNum 投注号码，至少五个号码，以逗号隔开
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSsc5XingZuXuan10(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }

        // 开奖号码有且仅有2个重复号，一个三重号，一个二重号，否则不是组10，直接返回false
        String[] openArr = openNum.split(",");
        List<String> openNumList = Arrays.asList(openArr);
        Map<String, Long> map = openNumList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        if (2 != map.size()) {
            return false;
        }

        // 没有2重号或者2重号不存在于投注号码中
        Set<Entry<String, Long>> groupSet = map.entrySet();
        String[] butArr = betNum.split(",");
        for (Entry<String, Long> entry : groupSet) {
            // 如果是有二重号，也只可能有一个二重号，只要出现二重号且包含再投注的二重号内，删除分组map中的这个号码，结束循环，但如果出现的二重号没有
            if (2 == entry.getValue()) {
                if (StringUtils.contains(butArr[1], entry.getKey())) {
                    map.remove(entry.getKey());
                    break;
                }
                return false;
            }
        }
        // 如果这里map的size没有变为1，说明没有出现二重号，直接返回false
        if (1 != map.size()) {
            return false;
        }

        // 从开奖号码中取出剩余的号码（三重号），依次判断是否存在于投注号码的三重号内，只要一个不存在就不中奖
        String threeNum = butArr[0];
        Set<String> set = map.keySet();
        for (String num : set) {
            if (!StringUtils.contains(threeNum, num)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时时彩5星组选5 判断是否中奖
     * @param openNum 开奖号码，五个位以逗号隔开
     * @param betNum 投注号码，至少五个号码，以逗号隔开
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSsc5XingZuXuan5(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        // 开奖号码有且仅有1个四重号，一个单号，否则不是组5，直接返回false
        String[] openArr = openNum.split(",");
        List<String> openNumList = Arrays.asList(openArr);
        Map<String, Long> map = openNumList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        if (2 != map.size()) {
            return false;
        }

        // 判断4重号是否存在，是否包含在投注号码中
        Set<Entry<String, Long>> groupSet = map.entrySet();
        String[] butArr = betNum.split(",");
        for (Entry<String, Long> entry : groupSet) {
            // 这里如果有4重号的话，只可能出现一个，如果出现，判断是否存在于投注号码内，不存在直接返回false
            if (4 == entry.getValue()) {
                if (StringUtils.contains(butArr[0], entry.getKey())) {
                    map.remove(entry.getKey());
                    break;
                }
                return false;
            }
        }
        if (1 != map.size()) {
            return false;
        }

        // 从开奖号码中取出剩余的号码单号，依次判断是否存在于投注号码的单号内，只要一个不存在就不中奖
        String threeNum = butArr[1];
        Set<String> set = map.keySet();
        for (String num : set) {
            if (!StringUtils.contains(threeNum, num)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时时彩大小单双 判断是否中奖
     * @param openNum 开奖号码，五个位以逗号隔开
     * @param betNum 投注号码，至少五个号码，以逗号隔开
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinSscDaXiaoDanShuang(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }
        // 开奖号码转换为大小单数标识
        String[] openArr = openNum.split(",");
        String shiWei = changeNumToDaXiaoDanShuang(openArr[0]);
        String geWei = changeNumToDaXiaoDanShuang(openArr[1]);

        String[] betArr = betNum.split(",");
        // 首先判断十位，如果投注类型中的十位没有包含开奖号码中的十位类型，直接返回false
        String[] shiWeiArr = shiWei.split(" ");
        if (!StringUtils.contains(betArr[0], shiWeiArr[0]) && !StringUtils.contains(betArr[0], shiWeiArr[1])) {
            return false;
        }

        // 继续判断个位，如果投注类型中的个位没有包含开奖号码中的个位类型，直接返回false
        String[] geWeiArr = geWei.split(" ");
        if (!StringUtils.contains(betArr[1], geWeiArr[0]) && !StringUtils.contains(betArr[1], geWeiArr[1])) {
            return false;
        }
        return true;
    }

    /**
     * 时时彩大一帆风顺 判断是否中奖
     * @param openNum 开奖号码，五个位以逗号隔开
     * @param betNum 投注号码，多个号码以逗号隔开
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWin1FanFengShun(String openNum, String betNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }

        // 只要开奖号码中包含任意一个投注号码，返回true
        String[] betArr = betNum.split(",");
        for (String num : betArr) {
            if (StringUtils.contains(openNum, num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 时时彩好事成双,三星报喜,四季发财 判断是否中奖
     * @param openNum 开奖号码，五个位以逗号隔开
     * @param betNum 投注号码，至少五个号码，以逗号隔开
     * @param groupNum 最大分组数，好事成双传入4,三星报喜传入3,四季发财传入2
     * @return 中奖返回true
     * @author Terry
     */
    public static boolean checkWinQuWei(String openNum, String betNum, int groupNum) {
        // 参数不合法，返回false
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return false;
        }

        // 如果没有重复号码直接返回false
        String[] openArr = openNum.split(",");
        List<String> openNumList = Arrays.asList(openArr);
        Map<String, Long> map = openNumList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        // 这里的6并没有什么特殊意义，仅仅是三种玩法的区别，二星的时候至少2个重复，最多可分为4组，三星的时候至少3个重复，最多分3组，四星的时候至少4个重复，最多分2组
        if ((6 - groupNum) < map.size()) {
            return false;
        }

        // 开奖号码中的二重号只要有一个包含在投注号码中即为中奖，返回true
        for (Entry<String, Long> entry : map.entrySet()) {
            if (groupNum == entry.getValue() && StringUtils.contains(betNum, entry.getKey())) {
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

    /**
     * 时时彩组选6：前3组选6 计算注数
     * @param lotteryOrder 订单信息
     * @return 返回订单注数
     * @author Jerry
     */
    public static int orderCountSsc3XingZuXuan6(LotteryOrder lotteryOrder) {
        // 参数不合法，返回false
        if (null == lotteryOrder) {
            return 0;
        }
        // 获取投注倍数、单注金额
        BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
        BigDecimal price = new BigDecimal(2);
        BigDecimal resule = BigDecimal.ZERO;
        if ("0".equals(lotteryOrder.getPlayModeMoneyType())) {// 元
            resule = lotteryOrder.getBetAmount().divide(betRate).divide(price).multiply(new BigDecimal(1)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        } else if ("1".equals(lotteryOrder.getPlayModeMoneyType())) {// 角
            resule = lotteryOrder.getBetAmount().divide(betRate).divide(price).multiply(new BigDecimal(10)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        } else if ("2".equals(lotteryOrder.getPlayModeMoneyType())) {// 分
            resule = lotteryOrder.getBetAmount().divide(betRate).divide(price).multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        }
        return resule.intValue();
    }

    // -------------------------------中奖注数计算------------------------------------------------

    /**
     * 时时彩一星定位胆 计算中奖注数
     * @param openNum 开奖号码,五个位（万,千,百,十,个）,位与位之间以英文逗号隔开
     * @param betNum 投注号码，五个位（万,千,百,十,个），位与位之间以英文逗号隔开,空位以减号表示
     * @return 返回中奖注数，没有中奖返回0
     * @author Terry
     */
    public static int winCountSsc1XinDingWei(String openNum, String betNum) {
        // 参数不合法，返回0
        if (StringUtils.isBlank(openNum) || StringUtils.isBlank(betNum)) {
            return 0;
        }
        String[] betNumList = betNum.split(",");
        String[] openNums = openNum.split(",");
        // 每个位的号码分别判断是否包含对应的开奖号码,对应位上的投注号码包含开奖号码,中奖注释加1
        int count = 0;
        for (int i = 0; i < openNums.length; i++) {
            if (StringUtils.contains(betNumList[i], openNums[i])) {
                count++;
            }
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

    // -------------------------------其他工具方法------------------------------------------------

    /**
     * 转换开奖号码为大小单双的形式
     * @param num 需要转换的开奖号码（单个号码）
     * @return 转换为大小单双形式的开奖号码
     * @author Terry
     */
    private static String changeNumToDaXiaoDanShuang(String num) {
        StringBuilder result = new StringBuilder();
        if (StringUtils.contains("56789", num)) {
            result.append("DA");
        } else {
            result.append("XIAO");
        }
        if (StringUtils.contains("13579", num)) {
            result.append(" DAN");
        } else {
            result.append(" SHUANG");
        }
        return result.toString();
    }

    /**
     * 将数组String[]{1, 2, 3}格式化成 字符串123
     * @param a 要格式化的数组
     * @return 格式化后的字符串
     */
    public static String formatNumber(String[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0;; i++) {
            b.append(a[i]);
            if (i == iMax) {
                return b.toString();
            }
            // b.append(",");
        }
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
	
	
	public static int calBetNum5XingZhiXuanDanShi(String bet) {
		// TODO Auto-generated method stub

		String[] betNumList = bet.split(",");

		return betNumList.length;

	}
	
	public static int calBetNum5XingZhiXuanFuShi(String bet)
	{
		String[] betNumList = bet.split(",");
		int count = 0;
		for (String string : betNumList) {
			count *= string.length();
		}

		return count;
		
	}
	
	public static int calBetNum5XingZhiXuanHeZhi(String bet) {
		// TODO Auto-generated method stub

		String[] betNumList = bet.split(",");

		return betNumList.length;

	}
	
	
	public static int calBetNum5XingZuXuan120(String bet) {
		

		String[] betNumList = bet.split(",");

		List<String> lsBets = Arrays.asList(betNumList);

		List<Integer> lsIntBets = lsBets.stream().map(Integer::valueOf).collect(Collectors.toList());

		// get combination
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arrCombs = Combination.Combination(lsIntBets, lsIntBets.size(), 5, t);

		OpenLottery ol = new OpenLottery();
		return arrCombs.size();
		
	}

	public static int calBetNum5XingZuXuan60(String betDetail) {
		// TODO Auto-generated method stub
		

		String[] betNumList = betDetail.split(",");

		List<Star5> lsStar5 = new ArrayList<Star5>();

		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;

		// double No array
		List<Integer> doubleNo = Arrays.asList(betNumList[0].split("")).stream().map(Integer::valueOf)
				.collect(Collectors.toList());

		// single No array
		List<Integer> singleNo = Arrays.asList(betNumList[1].split("")).stream().map(Integer::valueOf)
				.collect(Collectors.toList());

		// single No array => combination N,3
		ArrayList<Integer> t = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arr = Combination.Combination(singleNo, singleNo.size(), 3, t);

		for (int idb = 0; idb < doubleNo.size(); idb++) {

			a0 = a1 = doubleNo.get(idb);

			for (int isingle = 0; isingle < arr.size(); isingle++) {

				a2 = arr.get(isingle).get(0);
				a3 = arr.get(isingle).get(1);
				a4 = arr.get(isingle).get(2);

				int arrAN[] = new int[5];
				arrAN[0] = a0;
				arrAN[1] = a1;
				arrAN[2] = a2;
				arrAN[3] = a3;
				arrAN[4] = a4;

				Arrays.sort(arrAN);

				Star5 s5 = new Star5(arrAN);

				lsStar5.add(s5);
			}

		}

		return lsStar5.size();
		
	}

	public static int calBetNum5XingZuXuan30(String betDetail) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	
}
