package com.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration
@ContextConfiguration("classpath*:spring*.xml")
public class LotteryBetService2Test {

	@Autowired
	com.game.hall.modules.bet.service.LotteryAddBetService myBetService;

	public static LotteryOrder getOrder() {
		LotteryOrder bet1 = new LotteryOrder();
		bet1.preInsert();
		User user = new User();
		user.setId("00user");
		user.setName("00username");

		bet1.setAccountId("accountId");

		Office company = new Office();
		company.setCode("code");
		user.setCompany(company);
		bet1.setUser(user);
		String lotteryCode = "CQSSC";
		bet1.setLotteryCode(lotteryCode);
		BigDecimal betAmount = new BigDecimal(100);
		bet1.setBetAmount(betAmount);
		String betIssueNo = "20171117";
		bet1.setBetIssueNo(betIssueNo);
		String betType = "";
		bet1.setBetType(betType);
		User currentUser = null;
		bet1.setCurrentUser(currentUser);

		String orderSource = "0";
		bet1.setOrderSource(orderSource);
		String orderType = "2";
		bet1.setOrderType(orderType);
		BigDecimal playModeCommissionRate = new BigDecimal(0);
		bet1.setPlayModeCommissionRate(playModeCommissionRate);
		Integer playModeMoney = 1960;
		bet1.setPlayModeMoney(playModeMoney);
		String playModeMoneyType = "0";
		bet1.setPlayModeMoneyType(playModeMoneyType);

		//bet1.setOrderNum("10000001");

		bet1.setBetDetail("detail");
		bet1.setBetRate(1);
		bet1.setStatus("0");

		Date createDate = new Date();
		bet1.setCreateDate(createDate);
		bet1.setCreateBy(user);
		bet1.setUpdateBy(user);

		return bet1;
	}

	@Test
	public void test() {
		// fail("Not yet implemented");

		List<LotteryOrder> lsBetData = new ArrayList<LotteryOrder>();

		LotteryOrder order = getOrder();
		lsBetData.add(order);

		myBetService.addBet(lsBetData);

	}

}
