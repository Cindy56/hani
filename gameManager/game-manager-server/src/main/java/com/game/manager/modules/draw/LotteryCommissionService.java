package com.game.manager.modules.draw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.common.mapper.JsonMapper;
import com.game.manager.modules.member.service.MemberAccountService;
import com.game.manager.modules.member.service.MemberPlayConfigService;
import com.game.manager.modules.order.service.LotteryOrderService;
import com.game.manager.modules.trade.service.FinanceTradeDetailService;
import com.game.modules.lottery.entity.LotteryPlayConfig;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.member.entity.MemberPlayConfig;
import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.trade.entity.FinanceTradeDetail;

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
	
	@Autowired
	private MemberPlayConfigService memberPlayConfigService;
	
	
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
	public void calculateMemberCommission(LotteryOrder lotteryOrder) {
		if(null == lotteryOrder) {
			return;
		}
		//查询该订单所有上级代理账户
		List<MemberAccount> memberList = memberAccountService.findMemberId(lotteryOrder.getAccountId());
		//查询所有账户的奖金设置
		if(CollectionUtils.isEmpty(memberList)) {
			return;
		}
		List<String> ids = memberList.stream().map(MemberAccount::getId).collect(Collectors.toList());
		List<MemberPlayConfig> configList = memberPlayConfigService.queryByAccountIdList(ids);
		if(CollectionUtils.isEmpty(configList)) {
			return;
		}
		Map<String, MemberPlayConfig> memberPlayMap =new HashMap<String, MemberPlayConfig>();
		configList.stream().forEach(c->{memberPlayMap.put(c.getAccountId(), c);});
		Map<String, MemberAccount>  memberAccountMap = new HashMap<String, MemberAccount>();
		memberList.stream().forEach(c->{
			memberAccountMap.put(c.getId(), c);
		});
		//计算返点
		JsonMapper jsonMapper = JsonMapper.getInstance();
		memberList.stream().forEach(memberAccount->{
			if(lotteryOrder.getAccountId().equals(memberAccount.getId())) {
				//计算本人返点
				BigDecimal	currentAmount = lotteryOrder.getBetAmount().multiply(new BigDecimal(lotteryOrder.getPlayModeCommissionRate())).setScale(4, BigDecimal.ROUND_HALF_DOWN);
				//给上级加钱
				memberAccountService.plusAmount(memberAccount.getId(),currentAmount);
				//生成账变
				saveTradeDetail(memberAccount,lotteryOrder,currentAmount,"5");
			}else {
				if(memberAccountMap.containsKey(memberAccount.getParentAgentId())) {
					//计算上级返点
					MemberPlayConfig parentAgent = memberPlayMap.get(memberAccount.getParentAgentId());
					//当前
					MemberPlayConfig currentAgent = memberPlayMap.get(memberAccount.getId());
					if(null == parentAgent || null == currentAgent) {
						return;
					}
					//上级彩种配置
					List<LotteryPlayConfig> parentConfigList  =  jsonMapper.fromJson(parentAgent.getPlayConfig(), jsonMapper.createCollectionType(List.class, LotteryPlayConfig.class));
					//当前彩种配置
					List<LotteryPlayConfig> currentfigList  =  jsonMapper.fromJson(currentAgent.getPlayConfig(), jsonMapper.createCollectionType(List.class, LotteryPlayConfig.class));
						
					LotteryPlayConfig parentPlayConfig = parentConfigList.stream().filter(c->c.getLotteryCode().getCode().equals(lotteryOrder.getLotteryCode())).findFirst().get();
				
					LotteryPlayConfig currentPlayConfig = currentfigList.stream().filter(c->c.getLotteryCode().getCode().equals(lotteryOrder.getLotteryCode())).findFirst().get();

					BigDecimal  difference = new BigDecimal(parentPlayConfig.getCommissionRateMax()).subtract( new BigDecimal(currentPlayConfig.getCommissionRateMax()));
					if(BigDecimal.ZERO.compareTo(difference) == 0) {
						return;
					}
					BigDecimal	parentAmount = lotteryOrder.getBetAmount().multiply(difference).setScale(4, BigDecimal.ROUND_HALF_DOWN);
					//给上级加钱
					memberAccountService.plusAmount(memberAccount.getParentAgentId(),parentAmount);
					//生成账变
					saveTradeDetail(memberAccountMap.get(memberAccount.getParentAgentId()),lotteryOrder,parentAmount,"5");
				}
			}
		});
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
		//查询该订单所有上级代理账户
				List<MemberAccount> memberList = new ArrayList<MemberAccount>();
				MemberAccount memberAccount = new MemberAccount();
				memberAccount.setId("1");
				MemberAccount memberAccount2 = new MemberAccount();
				memberAccount2.setId("2");
				MemberAccount memberAccount3 = new MemberAccount();
				memberAccount3.setId("3");
				memberList.add(memberAccount);
				memberList.add(memberAccount2);
				memberList.add(memberAccount3);
				//查询所有账户的奖金设置
				List<String> ids = memberList.stream().map(MemberAccount::getId).collect(Collectors.toList());
				
				ids.stream().forEach(c->{
					System.out.println(c);
				});
		
	}
}
