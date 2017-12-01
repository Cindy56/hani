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
import com.game.hall.modules.order.dao.LotteryOrderDao;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.lottery.entity.LotteryTimeNum;
import com.game.modules.lottery.entity.LotteryType;
import com.game.modules.lottery.service.LotteryPlayConfigService;
import com.game.modules.lottery.service.LotteryTimeNumService;
import com.game.modules.order.entity.LotteryOrder;

/**
 * 今日开奖
 * 
 * @author antonio
 *
 */
@Service
public class LotteryOpenTodayService {
	// @Autowired
	private BetServiceApi myServiceClient;

	@Autowired
	private LotteryTimeNumService myLotteryTimeNumService;

	@Autowired
	private LotteryOrderDao myOrder;

	@Autowired
	private LotteryPlayConfigService lotteryPlayConfigService;



	public ResultData openToday(String lotteryCode, Integer num) {
		List<LotteryTimeNum> lsLots = myLotteryTimeNumService.findLotteryTimeNum(lotteryCode, num);
		return ResultData.ok(lsLots);

	}

	public ResultData openCur(String lotteryCode) {
		LotteryTimeNum lot = myLotteryTimeNumService.findCurrentIssueNo(lotteryCode);
		return ResultData.ok(lot);
	}

	public ResultData getPlayConfig(String lotteryCode) {
		if (myServiceClient != null)
			return myServiceClient.curOpen(lotteryCode);
		
		LotteryPlayConfig lotteryPlayConfig = new LotteryPlayConfig();
		LotteryType lotteryType = new LotteryType();
		lotteryType.setCode(lotteryCode);
		lotteryPlayConfig.setLotteryCode(lotteryType);
		
		List<LotteryPlayConfig> lsLot = lotteryPlayConfigService.findList(lotteryPlayConfig);
		
		return ResultData.ok(lsLot);
	}

	public ResultData getOrders(String userID, String lotteryName, int num) {
		if (myServiceClient != null)
			return myServiceClient.curOpen(lotteryName);
		
		List<LotteryOrder> lsLot = myOrder.findListById(userID, lotteryName, num);
		
		return ResultData.ok(lsLot);
	}

}
