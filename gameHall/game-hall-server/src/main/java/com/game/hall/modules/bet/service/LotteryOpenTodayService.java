/**
 * 
 */
package com.game.hall.modules.bet.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.BetServiceApi;
import com.entity.ResultData;
import com.game.hall.modules.bet.dao.AccountChargeDao;
import com.game.hall.modules.bet.dao.LotteryOpenTodayDao;
import com.game.hall.modules.bet.dao.LotteryOrderDao;
import com.game.hall.modules.bet.dao.LotteryPlayConfigDao;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.lottery.entity.LotteryTimeNum;
import com.game.modules.order.entity.LotteryOrder;

/**
 * 今日开奖
 * @author antonio
 *
 */
@Service
public class LotteryOpenTodayService {
	// @Autowired
	private BetServiceApi myServiceClient;

	@Autowired
	private LotteryOrderDao myOrder;

	@Autowired
	AccountChargeDao myAccountCharge;

	@Autowired
	LotteryPlayConfigDao myPlayConfig;

	@Autowired
	private LotteryOpenTodayDao lotOpenToday;
	// private BetServiceApi getBetServiceApi() {
	// return (BetServiceApi) SpringContextHolder.getBean("myServiceClient");
	// }
	//

	public ResultData openToday(String lotteryName, Integer num) {

		if (myServiceClient != null)
			return myServiceClient.openToday(lotteryName, num);

		List<LotteryTimeNum> lsLots = lotOpenToday.openToday(lotteryName, num);

		ResultData rd = ResultData.ResultDataOK();

		rd.setData(lsLots);
		return rd;

	}

	public ResultData openCur(String lotteryName) {
		if (myServiceClient != null)
			return myServiceClient.curOpen(lotteryName);

		Date dt = new Date();
		List<LotteryTimeNum> lsLot = lotOpenToday.currentIssue(lotteryName, dt);
		ResultData rd = ResultData.ResultDataOK();

		rd.setData(lsLot);
		return rd;
	}

	public ResultData getPlayConfig(String lotteryName) {
		if (myServiceClient != null)
			return myServiceClient.curOpen(lotteryName);

		List<LotteryPlayConfig> lsLot = myPlayConfig.findAllConfigByName(lotteryName);
		ResultData rd = ResultData.ResultDataOK();

		rd.setData(lsLot);
		return rd;
	}

	public ResultData getOrders( String userID, String lotteryName, int num) {
		if (myServiceClient != null)
			return myServiceClient.curOpen(lotteryName);

		Date dt = new Date();
		List<LotteryOrder> lsLot = myOrder.findListById(userID, lotteryName, num);
		ResultData rd = ResultData.ResultDataOK();

		rd.setData(lsLot);
		return rd;
	}

}
