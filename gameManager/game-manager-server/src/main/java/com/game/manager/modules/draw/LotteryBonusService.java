package com.game.manager.modules.draw;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.manager.modules.order.entity.LotteryOrder;
import com.game.manager.modules.order.service.LotteryOrderService;
import com.game.manager.modules.trade.entity.FinanceTradeDetail;
import com.game.manager.modules.trade.service.FinanceTradeDetailService;

/**
 * 派奖服务：
 * 从 数据库 / redis待派奖队列  里获取等待派奖的订单->
 * 计算中奖金额->更改账户余额->将账变流水入库到账变表/添加到账变队列
 * @author Administrator
 *
 */
@Service
public class LotteryBonusService {
	@Autowired
	private LotteryCalculateService lotteryCalculateServiceImpl;
	@Autowired
	private LotteryOrderService lotteryOrderService;
	@Autowired
	private FinanceTradeDetailService financeTradeDetailService;
	
	
	/**
	 * 根据期号查询等待派奖的订单,查数据库，
	 * 批量调用、计算
	 * @param lotteryCode 彩种代码
	 * @param betIssueNo 期号
	 */
	public void calculateOrderBonusFromDB(String lotteryCode, String betIssueNo) {
		//根据期号查询等待派奖的订单
		LotteryOrder orderVO = new LotteryOrder();
		orderVO.setLotteryCode(lotteryCode);
		orderVO.setBetIssueNo(betIssueNo);
		List<LotteryOrder>  orderList = this.lotteryOrderService.findList(orderVO);
		//批量调用calculateOrderBonus计算
		for (LotteryOrder lotteryOrder : orderList) {
			this.calculateOrderBonus(lotteryOrder);
		}
		
		return;
	}
	
	
	/**
	 * 监控redis待派奖的订单,
	 * @param lotteryOrder
	 * @return
	 */
	public List<LotteryOrder> calculateOrderBonusFromRedis(LotteryOrder lotteryOrder) {
		//调用calculateOrderBonus计算
		
		return null;
	}
	
	/**
	 * 派奖服务
	 * @param lotteryOrder
	 * @return
	 */
	private boolean calculateOrderBonus(LotteryOrder lotteryOrder) {
//		//计算注单中奖金额
//		BigDecimal bonus = this.lotteryCalculateServiceImpl.calculateOrderBonus(lotteryOrder);
//		lotteryOrder.setWinAmount(bonus);
//		//更新注单中奖状态和中奖金额
//		this.lotteryOrderService.save(lotteryOrder);
//		
//		//获取账户，更新账户余额:调用账户服务直接更新余额
////		this.accountService.plusAmount(bonus);
//		
//		//生成中奖账变流水,入库
//		FinanceTradeDetail  trade = new FinanceTradeDetail();
//		trade.setUser(lotteryOrder.getUser());
//		trade.setUserName(userName);
//		trade.setAccountId(accountId);
//		trade.setBusiNo(lotteryOrder.getOrderNum());
//		trade.setTradeType(tradeType);
//		trade.setAmount(bonus);
//		trade.setAccountBlanceBefore(accountBlanceBefore);
//		trade.setAccountBlanceAfter(accountBlanceAfter);
//		this.financeTradeDetailService.save(financeTradeDetail);

		return true;
	}
}