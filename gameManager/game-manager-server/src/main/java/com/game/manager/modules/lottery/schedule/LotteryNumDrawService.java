package com.game.manager.modules.lottery.schedule;

import com.game.manager.modules.lottery.exception.LotteryNumDrawException;

/**
 * 拉奖服务接口，后续通道实现该接口
 * @author Administrator
 *
 */
public interface LotteryNumDrawService {
	/**
	 * 根据彩种 和 期号 获取开奖号码
	 * 
	 * @param lotteryCode 彩种
	 * @param issueNo 彩票期号
	 * @return
	 */
	String drawLotteryNum(String lotteryCode, String issueNo) throws LotteryNumDrawException;
}
