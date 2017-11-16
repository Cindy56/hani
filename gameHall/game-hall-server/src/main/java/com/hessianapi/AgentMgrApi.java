/**
 * 
 */
package com.hessianapi;

import com.game.hall.modules.bet.entity.BetData;
import com.game.hall.modules.bet.entity.ResultData;

/**
 * @author Administrator
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
	public ResultData addBet(BetData betData);

	public ResultData openToday();

}
