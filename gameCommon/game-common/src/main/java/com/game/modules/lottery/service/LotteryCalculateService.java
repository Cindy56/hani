package com.game.modules.lottery.service;

import java.math.BigDecimal;

import com.game.modules.lottery.entity.LotteryTimeNum;
import com.game.modules.lottery.entity.ResponseMsgData;
import com.game.modules.order.entity.LotteryOrder;

public interface LotteryCalculateService {
	void trend(LotteryTimeNum lotteryTimeNum);
	ResponseMsgData checkOrder(LotteryOrder lotteryOrder);
	boolean checkWin(LotteryOrder lotteryOrder);
	BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder);
}
