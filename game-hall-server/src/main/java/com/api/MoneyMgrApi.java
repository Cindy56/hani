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
public interface MoneyMgrApi {

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
