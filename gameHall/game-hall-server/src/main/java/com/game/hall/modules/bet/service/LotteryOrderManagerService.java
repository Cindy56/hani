package com.game.hall.modules.bet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.hall.modules.bet.dao.LotteryOrderManagerDao;
import com.game.modules.finance.entity.FinanceTradeDetail;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.order.entity.LotteryOrder;

@Service
@Transactional(readOnly = true)
public class LotteryOrderManagerService {
	@Autowired
	private LotteryOrderManagerDao lotteryOrderManagerDao;
	
	@Transactional(readOnly = false)
	public boolean testInOrder(LotteryOrder lotteryOrder) throws Exception {
		FinanceTradeDetail  trade = new FinanceTradeDetail();
		trade.setUser(lotteryOrder.getUser());
		trade.setUserName(lotteryOrder.getUser().getLoginName());
		trade.setAccountId(lotteryOrder.getAccountId());
		trade.setCompanyId(lotteryOrder.getCompanyId());
		trade.setOfficeId(lotteryOrder.getCompanyId());
		trade.setBusiNo(lotteryOrder.getOrderNo());
//		trade.getUser().setId("sys");
		trade.setTradeType("0");
		trade.setAmount(lotteryOrder.getBetAmount());
		
		MemberAccount account = new MemberAccount();
		account.setId(lotteryOrder.getAccountId());
		account.setBlance(lotteryOrder.getBetAmount());
		
		lotteryOrder.preInsert();
		trade.preInsert();
//		int rows = this.lotteryOrderManagerDao.testInOrder(lotteryOrder, account, trade);
//		System.out.println(rows);
		
		//========================================测试本地事务：扣款，保存注单，生成帐变
		int minusAmountNum = this.lotteryOrderManagerDao.minusAmount(account);
		if(minusAmountNum != 1) {
			throw new Exception("余额不足，下单失败");
		}
		this.lotteryOrderManagerDao.createLotteryOrder(lotteryOrder);
		this.lotteryOrderManagerDao.createFinanceTradeDetail(trade);
		
		return true;
	}

}
