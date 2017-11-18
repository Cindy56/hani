/**
 * 
 */
package com.game.hall.modules.bet.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.hall.modules.bet.dao.LotteryOpenTodayDao;
import com.game.hall.modules.bet.entity.LotteryTimeNum;
import com.game.hall.modules.bet.entity.ResultData;
import com.hessianapi.MoneyMgrApi;

/**
 * @author antonio
 *
 */
@Service
public class LotteryOpenTodayService {
	@Autowired // (name = "myServiceClient")
	private MoneyMgrApi heApi;

	@Autowired
	private LotteryOpenTodayDao lotOpenToday;

	public void bet() {
		heApi.test1();
	}

	public ResultData OpenToday() {
		return heApi.openToday();
	}

	public List<LotteryTimeNum> Cur(Date dt) {
		return lotOpenToday.currentIssue(dt);
	}

}
