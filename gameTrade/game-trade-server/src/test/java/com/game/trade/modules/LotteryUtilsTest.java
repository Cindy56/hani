package com.game.trade.modules;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.game.modules.lottery.entity.LotteryTimeNum;
import com.game.modules.order.entity.LotteryOrder;
import com.game.trade.modules.lottery.manager.LotteryUtils;

public class LotteryUtilsTest {

    private LotteryOrder lotteryOrder;

    private LotteryTimeNum openlotteryTimeNum;

    @Before
    public void init() {
        // 注单数据
        lotteryOrder = new LotteryOrder();
        lotteryOrder.setBetRate(1);
        lotteryOrder.setPlayModeMoney("1700");
        lotteryOrder.setPlayModeMoneyType("0");
        // 开奖数据
        openlotteryTimeNum = new LotteryTimeNum();
    }

    /**
     * 一星定位胆
     * @author Terry
     */
    @Test
    public void ssc1XingDingWei() {
        long start = System.currentTimeMillis();

        // 投注奖金组、投注倍数、投注模式、中奖注数
        BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
        BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
        BigDecimal playModeMoneyType = new BigDecimal("1");
        BigDecimal winCount = new BigDecimal(LotteryUtils.winCountSsc1XinDingWei("2,5,2,4,1", "2,5,62,8,1"));

        // 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
        BigDecimal amount = playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);

        long end = System.currentTimeMillis();

        System.out.println("-------------------------------1星定位---------------------------");
        System.out.println("单注奖金：" + playModeMoney);
        System.out.println("中奖注数：" + winCount);
        System.out.println("注单奖金：" + amount);
        System.out.println("计算时间：" + (end - start) + " ms");
    }

    /**
     * 二星直选单式
     * @author Terry
     */
    @Test
    public void ssc2XingZhiXuanDan() {
        BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
        BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
        BigDecimal playModeMoneyType = new BigDecimal("1");
        BigDecimal winCount = null;

        long start = System.currentTimeMillis();
        // 判断是否中奖
        if (LotteryUtils.checkWinSscZhiXuanDan("2,5", "26 28 56 99 20 25")) {
            winCount = new BigDecimal(1);
        }
        else {
            winCount = new BigDecimal(0);
        }
        // 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
        BigDecimal amount = playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);

        long end = System.currentTimeMillis();

        System.out.println("-------------------------------2星直选单式---------------------------");
        System.out.println("单注奖金：" + playModeMoney);
        System.out.println("中奖注数：" + winCount);
        System.out.println("注单奖金：" + amount);
        System.out.println("计算时间：" + (end - start) + " ms");
    }

    /**
     * 二星直选复式
     * @author Terry
     */
    @Test
    public void ssc2XingZhiXuanFu() {
        BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
        BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
        BigDecimal playModeMoneyType = new BigDecimal("1");
        BigDecimal winCount = null;

        long start = System.currentTimeMillis();
        // 判断是否中奖
        if (LotteryUtils.checkWinSscZhiXuanFu("2,5", "2685,69205")) {
            winCount = new BigDecimal(1);
        }
        else {
            winCount = new BigDecimal(0);
        }
        // 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
        BigDecimal amount = playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);

        long end = System.currentTimeMillis();

        System.out.println("-------------------------------2星直选复式---------------------------");
        System.out.println("单注奖金：" + playModeMoney);
        System.out.println("中奖注数：" + winCount);
        System.out.println("注单奖金：" + amount);
        System.out.println("计算时间：" + (end - start) + " ms");
    }

    /**
     * 3星直选单式
     * @author Terry
     */
    @Test
    public void ssc3XingZhiXuanDan() {
        BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
        BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
        BigDecimal playModeMoneyType = new BigDecimal("1");
        BigDecimal winCount = null;

        long start = System.currentTimeMillis();
        // 判断是否中奖
        if (LotteryUtils.checkWinSscZhiXuanFu("2,5,8", "2685,69205,85624")) {
            winCount = new BigDecimal(1);
        }
        else {
            winCount = new BigDecimal(0);
        }
        // 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
        BigDecimal amount = playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);

        long end = System.currentTimeMillis();

        System.out.println("-------------------------------3星直选复式---------------------------");
        System.out.println("单注奖金：" + playModeMoney);
        System.out.println("中奖注数：" + winCount);
        System.out.println("注单奖金：" + amount);
        System.out.println("计算时间：" + (end - start) + " ms");
    }

    /**
     * 3星直选复式
     * @author Terry
     */
    @Test
    public void sscQian3ZhiXuanFu() {
        BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
        BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
        BigDecimal playModeMoneyType = new BigDecimal("1");
        BigDecimal winCount = null;

        long start = System.currentTimeMillis();
        // 判断是否中奖
        if (LotteryUtils.checkWinSscZhiXuanFu("2,5,6", "2685,69205,5496")) {
            winCount = new BigDecimal(1);
        }
        else {
            winCount = new BigDecimal(0);
        }
        // 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
        BigDecimal amount = playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);

        long end = System.currentTimeMillis();

        System.out.println("-------------------------------3星直选复式---------------------------");
        System.out.println("单注奖金：" + playModeMoney);
        System.out.println("中奖注数：" + winCount);
        System.out.println("注单奖金：" + amount);
        System.out.println("计算时间：" + (end - start) + " ms");
    }

    /**
     * 三星组选3
     * @author Terry
     */
    @Test
    public void ssc3XingZuXuan3() {
        // 投注内容和金额
        lotteryOrder.setBetDetail("2,5,6,4,1");
        lotteryOrder.setBetAmount(new BigDecimal(20));
        // 开奖号码
        openlotteryTimeNum.setOpenNum("6,2,6");

        long start = System.currentTimeMillis();

        // 投注奖金组、投注倍数、投注模式、中奖注数
        BigDecimal playModeMoney = new BigDecimal(lotteryOrder.getPlayModeMoney());
        BigDecimal betRate = new BigDecimal(lotteryOrder.getBetRate());
        BigDecimal playModeMoneyType = new BigDecimal("1");
        String openNum = openlotteryTimeNum.getOpenNum();
        BigDecimal winCount = new BigDecimal(LotteryUtils.winCountSsc3XingZu3(openNum.substring(0, 5), lotteryOrder.getBetDetail()));

        // 中奖金额 = 奖金组 * 投注倍数 * 投注模式对应面值 * 中奖注数
        BigDecimal amount = playModeMoney.multiply(betRate).multiply(playModeMoneyType).multiply(winCount);

        long end = System.currentTimeMillis();

        System.out.println("-------------------------------3星组选---------------------------");
        System.out.println("单注奖金：" + playModeMoney);
        System.out.println("中奖注数：" + winCount);
        System.out.println("注单奖金：" + amount);
        System.out.println("计算时间：" + (end - start) + " ms");
    }
}