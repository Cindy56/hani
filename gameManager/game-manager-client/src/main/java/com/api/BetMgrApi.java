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
public interface BetMgrApi {

	public String test1();

	/**
	 * 查询投注记录
	 * 
	 * @param betData
	 * @return
	 */
	public ResultData addBet(BetData betData);

	public ResultData openToday();

}
