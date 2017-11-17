/**
 * 
 */
package com.api;

import com.entity.BetData;
import com.entity.ResultData;

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
	public ResultData addBet(BetData betData);

	public ResultData openToday();

}
