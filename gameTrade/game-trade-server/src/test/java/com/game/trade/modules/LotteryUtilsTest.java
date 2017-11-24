package com.game.trade.modules;

import com.game.trade.modules.lottery.manager.LotteryUtils;

public class LotteryUtilsTest {

    /**
     * 一星定位胆
     * @author Terry
     */
    public static void ssc1XingDingWei() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSsc1XingDingWei("2,5,2,4,1", "2103597486,2974103586,7410329586,5867034912,5128670349");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    /**
     * 二星直选单式
     * @author Terry
     */
    public static void ssc2XingZhiXuanDan() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSscZhiXuanDan("2,7", "26,28,56,99,20,01,57,45,00,25");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    /**
     * 二星直选复式
     * @author Terry
     */
    public static void ssc2XingZhiXuanFu() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSscZhiXuanFu("0,9", "26248091375,1693240857");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    /**
     * 二星组选复式
     * @author Terry
     */
    public static void ssc2XingZuXuanFu() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSsc2XingZuXuanFu("9,8", "2,6,8,5,1,9,3,0,4");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    /**
     * 3星直选单式
     * @author Terry
     */
    public static void ssc3XingZhiXuanDan() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSscZhiXuanDan("0,5,9", "258,569,541,254,789,465,132,159,759,059");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    /**
     * 3星直选复式
     * @author Terry
     */
    public static void sscQian3ZhiXuanFu() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSscZhiXuanFu("0,5,9", "4501278369,5012784369,783450126");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    /**
     * 三星组选3
     * @author Terry
     */
    public static void ssc3XingZuXuan3() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSsc3XingZuXuan3("0,7,0", "4,5,0,1,2,7,8,3,6,9");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    /**
     * 五星组选120
     * @author Terry
     */
    public static void checkWinSsc5XingZuXuan120() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSsc5XingZuXuan120("1,5,4,6,6", "1,5,4,9,3,2,6,7,8,0");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    /**
     * 五星组选60
     * @author Terry
     */
    public static void checkWinSsc5XingZuXuan60() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSsc5XingZuXuan60("1,5,3,9,6", "1549326780,6150493782");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    public static void main(String[] args) {
        // 一星定位胆
        ssc1XingDingWei();
        // 二星直选单式
        // ssc2XingZhiXuanDan();
        // 二星直选复式
        // ssc2XingZhiXuanFu();
        // 二星组选复式
        // ssc2XingZuXuanFu();
        // 三星直选单式
        // ssc3XingZhiXuanDan();
        // 三星直选复式
        // sscQian3ZhiXuanFu();
        // 三星组选3
        // ssc3XingZuXuan3();
        // 五星组选120
        // checkWinSsc5XingZuXuan120();
        // 五星组选60
        // checkWinSsc5XingZuXuan60();
    }
}