package com.game.manager.modules.draw;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.manager.modules.memberadd.entity.MemberAccount;
import com.game.manager.modules.memberadd.service.MemberAccountService;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * 返水服务，佣金服务
 * @author Administrator
 *
 */

@Service
public class LotteryCommissionService {
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	
	
	public void calculateCommission(LotteryOrder lotteryOrder) {
		MemberAccount memberAccount = memberAccountService.get(lotteryOrder.getAccountId());
		if(null == memberAccount) {
			return;
		}
		//查询该订单所有上级代理账户
		List<String> ids = Arrays.asList(memberAccount.getParentAgentIds().split(","));
		List<MemberAccount> memberList = memberAccountService.findMemberIds(ids);
		//计算返点
		memberList.stream().forEach(c->{
			
		});
		
		
	}
}
