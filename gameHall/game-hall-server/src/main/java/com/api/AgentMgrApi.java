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
public interface AgentMgrApi {

	public String test1();

	/**
	 * 添加投注
	 * 
	 * @param betData
	 * @return
	 */
	public ResultData addBet(LotteryOrder betData);

	public ResultData openToday();

}
