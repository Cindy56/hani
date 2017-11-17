package com.game.manager.modules.draw;

import java.math.BigDecimal;

import com.game.manager.common.utils.SpringContextHolder;
import com.game.manager.modules.lottery.entity.LotteryTimeNum;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * 彩种基本功能服务总入口：提供订单检查、奖金计算
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
public class LotteryCalculateServiceImpl implements LotteryCalculateService {

	@Override
	public void trend(LotteryTimeNum lotteryTimeNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkOrder(LotteryOrder lotteryOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkWin(LotteryOrder lotteryOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BigDecimal calculateOrderBonus(LotteryOrder lotteryOrder) {
		String orderLotteryCode = lotteryOrder.getLotteryCode();//彩种
		String orderPlayCode = lotteryOrder.getOrderType();//玩法
//		LotteryCalculateService lotteryService = SpringContextHolder.getBean(orderLotteryCode + "Service");//get bean
		
		return SsccqService.valueOf(orderPlayCode).calculateOrderBonus(lotteryOrder);
	}
	
	private LotteryCalculateService getBeanByLotteryOrder(String orderLotteryCode) {
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
