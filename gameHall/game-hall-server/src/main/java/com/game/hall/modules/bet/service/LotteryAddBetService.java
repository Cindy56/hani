/**
 * 
 */
package com.game.hall.modules.bet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.hall.modules.bet.entity.BetData;
import com.game.hall.modules.bet.entity.ResultData;
import com.hessianapi.MoneyMgrApi;

/**
 * @author antonio
 *
 */
@Service
public class LotteryAddBetService {
	@Autowired // (name = "myServiceClient")
	private MoneyMgrApi heApi;

	public ResultData bet(BetData betData) {

		return heApi.addBet(betData);

	}

}
