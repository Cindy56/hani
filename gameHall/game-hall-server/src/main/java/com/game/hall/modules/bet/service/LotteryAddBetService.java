/**
 * 
 */
package com.game.hall.modules.bet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.BetServiceApi;
import com.entity.ResultData;
import com.game.hall.modules.bet.dao.AccountChargeDao;
import com.game.hall.modules.order.dao.LotteryOrderDao;
import com.game.hall.modules.sys.utils.UserUtils;
import com.game.modules.finance.entity.FinanceTradeDetail;
import com.game.modules.finance.service.FinanceTradeDetailService;
import com.game.modules.lottery.entity.GameError;
import com.game.modules.lottery.service.LotteryPlayConfigService;
import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.User;

/**
 * 投注服务
 * 
 * @author antonio
 *
 */
@Transactional(readOnly = true)
@Service
public class LotteryAddBetService implements BetServiceApi {

	private static final Logger LOG = LoggerFactory.getLogger(LotteryAddBetService.class);

	// @Autowired // @Qualifier("myServiceClient") //
	// @Resource(name = "myServiceClient")
	// private BetServiceApi myServiceClient;

	@Autowired
	private LotteryOrderDao myOrder;

	@Autowired
	private AccountChargeDao myAccountCharge;

//	@Autowired
	private LotteryPlayConfigService lotteryPlayConfigService;

	@Autowired
	private LotteryOpenTodayService myOpenToday;

	@Autowired
	private FinanceTradeDetailService financeTradeDetailService;


	public String test1() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultData addBet2(LotteryOrder betData) {
		// TODO Auto-generated method stub
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("remoting-client.xml");
		// BetServiceApi helloService = (BetServiceApi)
		// context.getBean("myServiceClient");
		// BetServiceApi helloService = getBetServiceApi();
		// System.out.println("helloService.test1()=");
		// System.out.println(helloService.test1());

		System.out.println("myServiceClient.test1()");
		// System.out.println(myServiceClient.test1());

		return null;
	}

	public static LotteryOrder getOrder() {
		LotteryOrder bet1 = new LotteryOrder();
		bet1.preInsert();
		User user = new User();
		// user.setId("00user");
		user.setName("00username");

		bet1.setAccountId("034f37416db44fa4a8ab05d98da6fa7d");

		Office company = new Office();
		company.setCode("code");
		user.setCompany(company);
		// bet1.setUser(user);
		String lotteryCode = "CQSSC";
		bet1.setLotteryCode(lotteryCode);
		BigDecimal betAmount = new BigDecimal(1);
		bet1.setBetAmount(betAmount);
		String betIssueNo = "20171117";
		bet1.setBetIssueNo(betIssueNo);
		String betType = "";
		bet1.setBetType(betType);
		User currentUser = null;
		// bet1.setCurrentUser(currentUser);

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

		// bet1.setOrderNum("10000001");

		bet1.setBetDetail("detail");
		bet1.setBetRate(1);
		bet1.setStatus("0");

		return bet1;
	}

	@Transactional(readOnly = false)
	@Override
	public int addBet(LotteryOrder betData ) { // TODO

		System.out.println("service_addbet here");

		// LotteryOrder order = getOrder();
		// 生成订单
		// myOrder.insert(order);

		// 会员账户扣款
		// String thisAccountId = order.getAccountId();
		// BigDecimal amount = order.getBetAmount();
		// myAccountCharge.AccountChargeAmount(thisAccountId, amount );

		// -------------------------------------
		// myServiceClient.addBet(betData);

			// 生成订单
			myOrder.insert(betData);

			// 会员账户扣款
			String thisAccountId = betData.getId();
			myAccountCharge.accountChargeAmount(thisAccountId, betData.getBetAmount());
			
			
			//帐变
			FinanceTradeDetail trade = new FinanceTradeDetail();
				
			User user = betData.getUser();
			trade.setUser(user);
		    trade.setUserName(user.getName());
		    trade.setAccountId(user.getId());
		    trade.setCompanyId(user.getCompany().getId());
		    trade.setOfficeId(user.getOffice().getId());
			    	
		    trade.setBusiNo(betData.getOrderNo());
		    trade.setTradeType("0");
		    trade.setAmount( betData.getBetAmount());
		  
		    trade.getUser().setId("sys");
			
			financeTradeDetailService.save(trade);
			
		

		return 0;
	}

	@Override
	public ResultData openToday(String lotteryName, Integer num) {
		// TODO Auto-generated method stub
		return myOpenToday.openToday(lotteryName, num);
	}

	@Override
	public ResultData curOpen(String lotteryName) {
		// TODO Auto-generated method stub
		return myOpenToday.openCur(lotteryName);
	}

	public ResultData getPlayConfig(String lotteryName) {
		// TODO Auto-generated method stub
		return myOpenToday.getPlayConfig(lotteryName);
	}

	@Override
	public ResultData getOrders(String userId, String lotteryName, int num) {
		// TODO Auto-generated method stub
		return myOpenToday.getOrders(userId, lotteryName, num);
	}

	/**
	 * 撤销订单
	 * 
	 * @param orderIds
	 * @return
	 */
	@Transactional(readOnly = false)
	public ResultData cancelOrder(User user, List<String> orderIds) {
		// TODO Auto-generated method stub

		for (int i = 0; i < orderIds.size(); i++) {

			int ret = myOrder.cancelByOrderNo(user, new Date(), orderIds.get(i));
		}

		return ResultData.ok();
	}

}
