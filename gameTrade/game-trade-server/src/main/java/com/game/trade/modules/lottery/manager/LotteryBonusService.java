package com.game.trade.modules.lottery.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.finance.entity.FinanceTradeDetail;
import com.game.modules.finance.service.FinanceTradeDetailService.FinanceTradeDetailType;
import com.game.modules.order.entity.LotteryOrder;
import com.game.trade.modules.finance.service.FinanceTradeDetailServiceImpl;
import com.game.trade.modules.member.service.MemberAccountServiceImpl;
import com.game.trade.modules.order.service.LotteryOrderServiceImpl;

/**
 * 派奖服务：
 * 从 数据库 / redis待派奖队列  里获取等待派奖的订单->
 * 计算中奖金额->更改账户余额->将账变流水入库到账变表/添加到账变队列
 * @author Administrator
 *
 */
@Service
public class LotteryBonusService {
//	@Autowired
//	private SystemService systemService;
	@Autowired
	private MemberAccountServiceImpl memberAccountService;
	@Autowired
	private LotteryCalculateServiceImpl lotteryCalculateService;
	@Autowired
	private LotteryOrderServiceImpl lotteryOrderService;
	@Autowired
	private FinanceTradeDetailServiceImpl financeTradeDetailService;
	
	@Autowired
	private LotteryCommissionService lotteryCommissionService;
	
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
		
		this.calculateOrderBonus(orderList);
		
        new Thread() {
        	@Override
        	public void run() {
        		try {
					Thread.sleep(3000);
					 //返水服务
					lotteryCommissionService.calculateOrderCommissionFromDB(lotteryCode,betIssueNo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
        }.run();
		
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
	 * 派奖服务：
	 * 计算注单中奖金额->更新注单中奖状态和中奖金额->更新账户余->生成中奖账变流水,入库
	 * @param lotteryOrder
	 * @return
	 */
	private void calculateOrderBonus(List<LotteryOrder> orderList) {
		
		List<LotteryOrder> winLotteryOrderList = new ArrayList<LotteryOrder>();
		for (LotteryOrder lotteryOrder : orderList) {
//			//计算注单中奖金额
			BigDecimal bonus = this.lotteryCalculateService.calculateOrderBonus(lotteryOrder);
			if(null == bonus) {
				bonus = BigDecimal.ZERO;
			}
			lotteryOrder.setWinAmount(bonus);
			lotteryOrder.setStatus(bonus.intValue() > 0 ? "1" : "2");//注单状态：		0等待开奖		1已中奖		2未中奖		3已撤单
			//更新注单中奖状态和中奖金额
			this.lotteryOrderService.save(lotteryOrder);
			
			if(bonus.intValue() > 0) {
				//获取账户，更新账户余额:调用账户服务直接更新余额
				this.memberAccountService.plusAmount(lotteryOrder.getAccountId(), bonus);
				
				winLotteryOrderList.add(lotteryOrder);
//				//生成中奖账变流水,入库
//				FinanceTradeDetail  trade = new FinanceTradeDetail();
//				trade.setUser(lotteryOrder.getUser());
//				trade.setUserName(lotteryOrder.getUser().getName());
//				trade.setAccountId(lotteryOrder.getAccountId());
//				trade.setOrgId(lotteryOrder.getOrgId());
//				trade.setBusiNo(lotteryOrder.getOrderNo());
//				trade.setTradeType("4");
//				trade.setAmount(bonus);
////				trade.setAccountBlanceBefore(accountBlanceBefore);
////				trade.setAccountBlanceAfter(accountBlanceAfter);
//				trade.getUser().setId("robot");
//				this.financeTradeDetailService.save(trade);
			}
		}
		
		
		this.financeTradeDetailService.batchGenFinanceTradeDetail(winLotteryOrderList, FinanceTradeDetailType.xxxxxx);
		return;
	}
}
