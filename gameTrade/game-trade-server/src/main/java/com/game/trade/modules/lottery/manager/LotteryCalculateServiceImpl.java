package com.game.trade.modules.lottery.manager;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.lottery.entity.LotteryTimeNum;
import com.game.modules.lottery.entity.ResponseMsgData;
import com.game.modules.lottery.service.LotteryCalculateService;
import com.game.modules.order.entity.LotteryOrder;
import com.game.trade.modules.lottery.service.LotteryTimeNumServiceImpl;

/**
 * 彩种计算服务：
 * 提供订单检查、奖金计算
 * 
 * 基于数据库：拿到开奖号码，查询该期的有效订单，计算订单金额，更改余额，生成流水 
 * 返点服务：拿到开奖号码，查询该期的有效订单，计算层级返水，更改余额，生成流水
 * 
 * 基于redis：
 * 开奖时刻队列：所有的彩种时刻，方便取当前期号，当前封单时间，开奖号码
 * 注单队列（待开奖队列、系统下注单队列）：从客户端、追号生成的订单都放这里，等待开奖
 * 拉取到开奖号码， 根据彩种和期号把订单移到待派奖队列，同时复制到返点队列
 * 派奖队列：派奖服务拉取订单，计算中奖金额，更改余额，将账变流水添加到账变流水队列
 * 返点队列：返点服务拉取订单，计算层级返点，更改账户余额，将账变流水添加到账变流水队列
 * 账变流水：后台调度器，调用账变服务，入库mysql、mongdb
 * 
 * @author Administrator
 *
 */

@Service("lotteryCalculateService")
public class LotteryCalculateServiceImpl implements LotteryCalculateService {
	@Autowired
	private LotteryTimeNumServiceImpl lotteryTimeNumService;
	
	

	/**
	 * @return the lotteryTimeNumService
	 */
	public LotteryTimeNumServiceImpl getLotteryTimeNumService() {
		return lotteryTimeNumService;
	}

	/**
	 * @param lotteryTimeNumService the lotteryTimeNumService to set
	 */
	public void setLotteryTimeNumService(LotteryTimeNumServiceImpl lotteryTimeNumService) {
		this.lotteryTimeNumService = lotteryTimeNumService;
	}

	public void trend(LotteryTimeNum lotteryTimeNum) {
		// TODO Auto-generated method stub

	}

	public ResponseMsgData checkOrder(LotteryOrder lotteryOrder) {
		
		//获取当前彩种的时刻，
		LotteryTimeNum betLotteryTimeNum = lotteryTimeNumService.findCurrentIssueNo(lotteryOrder.getLotteryCode());
		//校验
		return SscService.valueOf(lotteryOrder.getBetType()).checkOrder(lotteryOrder, betLotteryTimeNum);
	}

	public boolean checkWin(LotteryOrder lotteryOrder) {
		LotteryTimeNum openLotteryTimeNum = lotteryTimeNumService.findByLotteryCodeIssueNo(lotteryOrder.getLotteryCode(), lotteryOrder.getBetIssueNo());
		return SscService.valueOf(lotteryOrder.getBetType()).checkWin(lotteryOrder, openLotteryTimeNum);
	}

	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder) {
//		LotteryCalculateService lotteryService = SpringContextHolder.getBean(orderLotteryCode + "Service");//get bean
		// 获取开奖号码
		LotteryTimeNum openlotteryTimeNum = lotteryTimeNumService.findByLotteryCodeIssueNo(lotteryOrder.getLotteryCode(), lotteryOrder.getBetIssueNo());
		return SscService.valueOf(lotteryOrder.getBetType()).calculateOrderBonus(lotteryOrder, openlotteryTimeNum);
	}
	
	private LotteryService getBeanByLotteryOrder(String orderLotteryCode) {
//		LotteryCalculateService lotteryService = SpringContextHolder.getBean(orderLotteryCode + "Service");//get bean
//		switch (orderLotteryCode) {
//			case "SSC_CQ":
//				return SsccqService
//				break;
//	
//			default:
//				return null;
//		}
		return null;
	}

}
