/**
 * 
 */
package com.api;

import com.game.hall.modules.bet.entity.ResultData;
import com.game.modules.order.entity.LotteryOrder;

/**
 * @author antonio
 *
 */
public interface BetMgrApi {

	public String test1();

	/**
	 * 查询投注记录
	 * 
	 * @param betData
	 * @return
	 */
	public ResultData addBet(LotteryOrder betData);

	public ResultData openToday();

}
