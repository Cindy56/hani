/**
 * 
 */
package com.api;

import com.entity.BetData;
import com.entity.ResultData;

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
	public ResultData addBet(BetData betData);

	public ResultData openToday();

}
