/**
 * 
 */
package com.hessianapi;

import com.game.hall.modules.bet.entity.BetData;
import com.game.hall.modules.bet.entity.ResultData;

/**
 * 投注服务
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

}
