/**
 * 
 */
package com.hessianapi;

import com.game.hall.modules.bet.entity.BetData;
import com.game.hall.modules.bet.entity.ResultData;

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
