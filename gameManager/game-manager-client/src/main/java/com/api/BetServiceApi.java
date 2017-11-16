/**
 * 
 */
package com.api;

import com.entity.BetData;
import com.entity.ResultData;

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
	public ResultData addBet(BetData betData);

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
	public ResultData lotteryPlayConfig(String lotteryName);

	/**
	 * 我的方案
	 * 
	 * @param lotteryName
	 * @param userId
	 * @return
	 */
	public ResultData lotteryPlayConfig(String lotteryName, String userId);

}
