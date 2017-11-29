package com.game.trade.modules.lottery.manager;

import com.game.modules.lottery.dto.OpenCaiResult;
import com.game.modules.lottery.exception.LotteryNumDrawException;

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
	OpenCaiResult drawLotteryNum(String lotteryCode, String issueNo) throws LotteryNumDrawException;
	
	/**
	 * 根据彩种 获取开奖号码  最新一期
	 * @param lotteryCode 彩种
	 * @return
	 */
	OpenCaiResult drawLotteryNum(String lotteryCode) throws LotteryNumDrawException;
}
