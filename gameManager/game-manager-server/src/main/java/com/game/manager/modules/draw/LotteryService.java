package com.game.manager.modules.draw;

import java.math.BigDecimal;

import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.order.entity.LotteryOrder;

//拉奖
//算奖
//派奖

/**
 * 算奖接口：
 * 计算生成走势
 * 检查订单是否中奖
 * 计算中奖金额
 * 
 * @author Administrator
 *
 */
public interface LotteryService {
    /**
     * 计算走势图
     * @param openLotteryTimeNum 彩种开奖数据
     */
    void trend(LotteryTimeNum openLotteryTimeNum);

    /**
     * 检查注单合法性、有效性
     * @param lotteryOrder
     * @parm betLotteryTimeNum 可以投注的时刻数据
     * @return
     */
    boolean checkOrder(LotteryOrder lotteryOrder, LotteryTimeNum betLotteryTimeNum);

    /**
     * 检查是否中奖
     * true:中奖 | false:未中奖
     * @parm  lotteryOrder 注单
     * @param openLotteryTimeNum 彩种开奖数据
     * @return
     */
    boolean checkWin(LotteryOrder lotteryOrder, LotteryTimeNum openLotteryTimeNum);

    /**
     * 计算注单中奖金额
     * @param lotteryOrder
     * @param openLotteryTimeNum 彩种开奖数据
     * @return
     */
    BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder, LotteryTimeNum openlotteryTimeNum);

    // 计算本人和上级的返水

}
