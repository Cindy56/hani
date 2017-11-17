/**
 * 
 */
package com.api;

import com.entity.ResultData;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * 账号管理
 * 
 * @author antonio
 *
 */
public interface AccountMgrApi {

	public String test1();

	/**
	 * 添加投注
	 * 
	 * @param betData
	 * @return
	 */
	public ResultData addBet(LotteryOrder betData);

	public ResultData openToday();
	
	public ResultData personalData(String id);

}
