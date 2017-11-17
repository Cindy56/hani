/**
 * 
 */
package com.api;

import com.entity.ResultData;
import com.game.manager.modules.order.entity.LotteryOrder;

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
