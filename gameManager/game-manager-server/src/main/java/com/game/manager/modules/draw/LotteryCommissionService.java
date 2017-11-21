package com.game.manager.modules.draw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.game.manager.common.utils.StringUtils;
import com.game.manager.modules.lottery.entity.LotteryPlayConfig;
import com.game.manager.modules.member.entity.MemberAccount;
import com.game.manager.modules.member.service.MemberAccountService;
import com.game.manager.modules.order.entity.LotteryOrder;
import com.game.manager.modules.order.service.LotteryOrderService;
import com.game.manager.modules.trade.entity.FinanceTradeDetail;
import com.game.manager.modules.trade.service.FinanceTradeDetailService;

/**
 * 返水服务，佣金服务
 * @author Administrator
 *
 */
@Service
public class LotteryCommissionService {

	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private FinanceTradeDetailService financeTradeDetailService;
	
	@Autowired
	private LotteryOrderService lotteryOrderService;
	
	/**
	 * 根据期号查询等待派奖的订单,查数据库，
	 * 批量调用、计算
	 * @param lotteryCode 彩种代码
	 * @param betIssueNo 期号
	 */
	public void calculateOrderCommissionFromDB(String lotteryCode, String betIssueNo) {
		//根据期号查询等待派奖的订单
		LotteryOrder orderVO = new LotteryOrder();
		orderVO.setLotteryCode(lotteryCode);
		orderVO.setBetIssueNo(betIssueNo);
		List<LotteryOrder>  orderList = this.lotteryOrderService.findList(orderVO);
		//批量调用calculateMemberCommission计算 会员返点
		for (LotteryOrder lotteryOrder : orderList) {
			this.calculateMemberCommission(lotteryOrder);
		}
		return;
	}
	
	/**
	 * 计算返点 服务
	 * @param lotteryOrder
	 */
	private void calculateMemberCommission(LotteryOrder lotteryOrder) {
		if(null == lotteryOrder) {
			return;
		}
		//查询该订单所有上级代理账户
		List<MemberAccount> memberList = memberAccountService.findMemberId(lotteryOrder.getAccountId());
		//查询所有账户的奖金设置
		
		//计算
		Map<String, MemberAccount>  xxxx = new HashMap<String, MemberAccount>();
		memberList.stream().forEach(memberAccount->{
			xxxx.put(memberAccount.getId(), memberAccount);
		});
		
		//计算返点
		List<String> ids = new ArrayList<String>();
		memberList.stream().forEach(memberAccount->{
			if(lotteryOrder.getAccountId().equals(memberAccount.getId())) {
				//计算本人返点
				BigDecimal	currentAmount = lotteryOrder.getBetAmount().multiply(new BigDecimal(lotteryOrder.getPlayModeCommissionRate())).setScale(4, BigDecimal.ROUND_HALF_DOWN);
				//给上级加钱
				memberAccountService.plusAmount(memberAccount.getId(),currentAmount);
				//生成账变
				saveTradeDetail(memberAccount,lotteryOrder,currentAmount,"5");
			}else {
				if(xxxx.containsKey(memberAccount.getParentAgentId())) {
					//计算上级返点
					MemberAccount parentAgent = xxxx.get(memberAccount.getParentAgentId());
					//当前
					MemberAccount parentAgent2 = xxxx.get(memberAccount.getParentAgentId());
				}
			}
		});
		//模拟扣款
		List<LotteryPlayConfig> playConfigList = JSON.parseObject("", List.class) ;
	}
	
	private void calculate(LotteryPlayConfig current,LotteryPlayConfig superior,MemberAccount memberAccount ,LotteryOrder lotteryOrder) {
		//betAmount 订单金额
		//上级玩法最高返水   superior.get(lotteryOrder.getBetType()).getcommissionRateMax
		//当前会员玩法最高返水 current.get(lotteryOrder.getBetType()).getcommissionRateMax
		//上级返水
	//	lotteryOrder.getBetAmount() * （superior.get(lotteryOrder.getBetType()).getcommissionRateMax - current.get(lotteryOrder.getBetType()).getcommissionRateMax）
		
		
//		//上级返点金额
//		BigDecimal superiorAmount = bonus.multiply(new BigDecimal(1).subtract(new BigDecimal(superior.getCommissionRateMax()))).setScale(4,BigDecimal.ROUND_HALF_UP);
//		//当前返点
//		BigDecimal currentBonus = bonus.
//				multiply(new BigDecimal(1).subtract(new BigDecimal(current.getCommissionRateMax()))).setScale(4,BigDecimal.ROUND_HALF_UP);
//		
//		BigDecimal superiorBonus = superiorAmount.subtract(currentBonus).divide(bonus, 4, BigDecimal.ROUND_HALF_UP).multiply(bet);
		
		//给上级加钱
	//	memberAccountService.plusAmount(memberAccount.getId(),superiorBonus);
		//生成账变
	//	saveTradeDetail(memberAccount,lotteryOrder,superiorBonus,"5");
	}
	
	private void saveTradeDetail (MemberAccount memberAccount ,LotteryOrder lotteryOrder,BigDecimal amount,String tradeType) {
		FinanceTradeDetail  trade = new FinanceTradeDetail();
		trade.setUser(memberAccount.getUser());
		trade.setUserName(memberAccount.getUser().getName());
		trade.setAccountId(memberAccount.getId());
		trade.setOrgId(memberAccount.getOrgId().getId());
		trade.setBusiNo(lotteryOrder.getOrderNo());
		trade.setTradeType(tradeType);
		trade.setAmount(amount);
		//trade.setAccountBlanceBefore(accountBlanceBefore);
		//trade.setAccountBlanceAfter(accountBlanceAfter);
		trade.getUser().setId("sys");
		this.financeTradeDetailService.save(trade);
	}
	public static void main(String[] args) {
		/*//计算 奖金组
		BigDecimal bet = new BigDecimal(1000);
		
		BigDecimal a = new BigDecimal(2).divide(new BigDecimal(1).
				divide(bet, 4, BigDecimal.ROUND_HALF_UP), 4, BigDecimal.ROUND_HALF_UP).setScale(4,BigDecimal.ROUND_HALF_UP);
		
		BigDecimal bonus = a.multiply(new BigDecimal(1).subtract(new BigDecimal(0.022))).setScale(4,BigDecimal.ROUND_HALF_UP);
		
		BigDecimal bonus1 = a.
				multiply(new BigDecimal(1).subtract(new BigDecimal(0.028))).setScale(4,BigDecimal.ROUND_HALF_UP);
		
		BigDecimal bonus3 = a.
				multiply(new BigDecimal(1).subtract(new BigDecimal(0.03))).setScale(4,BigDecimal.ROUND_HALF_UP);
		//返点计算
		BigDecimal bonus2 = bonus.subtract(bonus1).divide(a, 4, BigDecimal.ROUND_HALF_UP).multiply(bet);
		
		
		

		System.out.println(bonus.doubleValue());
		System.out.println(bonus1.doubleValue());
		System.out.println(bonus2.doubleValue());*/

		
	}
}
