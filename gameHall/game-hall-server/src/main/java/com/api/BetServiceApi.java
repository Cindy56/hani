/**
 * 
 */
package com.api;

import java.util.List;

import com.entity.ResultData;
import com.game.modules.order.entity.LotteryOrder;

/**
 * 投注服务
 * 
 * @author antonio
 *
 */
public interface BetServiceApi {

	public String test1();

	/**
	 * 添加投注
	 * 
	 * @param betData
	 * @return
	 */
	public int addBet(LotteryOrder betData);

	/**
	 * 历史开奖
	 * 
	 * @param lotteryName
	 * @param num
	 * @return
	 */
	public ResultData openToday(String lotteryName, Integer num);

	/**
	 * 最新开奖
	 * 
	 * @param lotteryName
	 * @param num
	 * @return
	 */
	public ResultData curOpen(String lotteryName);

	/**
	 * 彩种玩法
	 * 
	 * @param lotteryName
	 * @return
	 */
	public ResultData getPlayConfig(String lotteryName);

	
	/**
	 * 我的方案
	 * 
	 * @param lotteryName
	 * @param num 
	 * @param userId
	 * @return
	 */
	public ResultData getOrders(String userId, String lotteryName, int num);

	
	
	
}
