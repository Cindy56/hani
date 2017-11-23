package com.game.manager.modules.draw;

public class LotteryUtilsTest {

    /**
     * 一星定位胆
     * @author Terry
     */
    public static void ssc1XingDingWei() {
        long start = System.currentTimeMillis();
        boolean isWin = false;
        for (int i = 0; i < 10000000; i++) {
            isWin = LotteryUtils.checkWinSsc1XingDingWei("2,5,2,4,1", "2,-,62,8,1");
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
            isWin = LotteryUtils.checkWinSscZhiXuanDan("2,5", "26 28 56 99 20 25");
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
            isWin = LotteryUtils.checkWinSscZhiXuanFu("2,9", "2685,69205");
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
            isWin = LotteryUtils.checkWinSscZhiXuanDan("0,5,9", "258 569 541 254 059");
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
            isWin = LotteryUtils.checkWinSscZhiXuanFu("0,5,9", "4501278369,5012784369,7834501269");
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
            isWin = LotteryUtils.checkWinSsc3XingZuXuan3("0,0,0", "4,5,0,1,2,7,8,3,6,9");
        }
        long end = System.currentTimeMillis();
        System.out.println("计算结果：" + (isWin ? "已中奖，恭喜你，再接再厉。" : "未中奖，别灰心，再来一次。"));
        System.out.println("计算耗时：" + (end - start) + "ms");
    }

    public static void main(String[] args) {
        // 一星定位胆
        // ssc1XingDingWei();
        // 二星直选单式
        // ssc2XingZhiXuanDan();
        // 二星直选复式
        // ssc2XingZhiXuanFu();
        // 三星直选单式
        // ssc3XingZhiXuanDan();
        // 三星直选复式
        // sscQian3ZhiXuanFu();
        // 三星组选3
        // ssc3XingZuXuan3();
    }
}