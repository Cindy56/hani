package com.game.manager.modules.draw;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.game.manager.modules.member.entity.MemberAccount;
import com.game.manager.modules.member.service.MemberAccountService;
import com.game.manager.modules.order.entity.LotteryOrder;

/**
 * 返水服务，佣金服务
 * @author Administrator
 *
 */
public class LotteryCommissionService {

	@Autowired
	private MemberAccountService memberAccountService;
	
	
	
	public void calculateCommission(LotteryOrder lotteryOrder) {
		if(null == lotteryOrder) {
			return;
		}
		//查询该订单所有上级代理账户
		List<MemberAccount> memberList = memberAccountService.findMemberId(lotteryOrder.getAccountId());
		//计算返点
		memberList.stream().forEach(c->{
			
		});
	}
}
