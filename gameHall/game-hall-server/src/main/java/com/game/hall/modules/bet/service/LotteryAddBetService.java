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
import com.entity.Error;
import com.entity.ResultData;
import com.game.hall.common.utils.SpringContextHolder;
import com.game.hall.modules.bet.dao.AccountChargeDao;
import com.game.hall.modules.bet.dao.LotteryOrderDao;
import com.game.hall.modules.bet.dao.LotteryPlayConfigDao;
import com.game.hall.modules.sys.entity.Office;
import com.game.hall.modules.sys.entity.User;
import com.game.manager.modules.order.entity.LotteryOrder;

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
	AccountChargeDao myAccountCharge;
	
	@Autowired
	LotteryPlayConfigDao myPlayConfig;
	
	@Autowired
	LotteryOpenTodayService myOpenToday;

	// public ResultData bet(BetData betData) {
	//
	// return heApi.addBet(betData);
	//
	// }

	private BetServiceApi getBetServiceApi() {
		return (BetServiceApi) SpringContextHolder.getBean("myServiceClient");
	}

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
		user.setId("00user");
		user.setName("00username");

		bet1.setAccountId("034f37416db44fa4a8ab05d98da6fa7d");

		Office company = new Office();
		company.setCode("code");
		user.setCompany(company);
		bet1.setUser(user);
		String lotteryCode = "CQSSC";
		bet1.setLotteryCode(lotteryCode);
		BigDecimal betAmount = new BigDecimal(1);
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
		String playModeCommissionRate = "0";
		bet1.setPlayModeCommissionRate(playModeCommissionRate);
		String playModeMoney = "1960";
		bet1.setPlayModeMoney(playModeMoney);
		String playModeMoneyType = "0";
		bet1.setPlayModeMoneyType(playModeMoneyType);

		bet1.setOrderNum("10000001");

		bet1.setBetDetail("detail");
		bet1.setBetRate(1);
		bet1.setStatus("0");

		Date createDate = new Date();
		bet1.setCreateDate(createDate);
		bet1.setCreateBy(user);
		bet1.setUpdateBy(user);

		return bet1;
	}

	@Transactional(readOnly = false)
	@Override
	public ResultData addBet(List<LotteryOrder> lsBetData) { // TODO

		System.out.println("service_addbet here");
		
	//	LotteryOrder order = getOrder();
		// 生成订单
	//	myOrder.insert(order);

		// 会员账户扣款
	//	String thisAccountId = order.getAccountId();
	//	BigDecimal amount = order.getBetAmount();
	//	myAccountCharge.AccountChargeAmount(thisAccountId, amount );

		// -------------------------------------
		// myServiceClient.addBet(betData);

		ResultData rd = ResultData.ResultDataOK();

		if (lsBetData == null) {
			LOG.debug("投注信息为空");
			return rd;
		}

		for (int i = 0; i < lsBetData.size(); i++) { // 前置校验 LotteryOrder betData =
			LotteryOrder betData = lsBetData.get(i);

			// if (betData.getIsNewRecord()compareTo(new BigDecimal(0)) <= 0) { //
			rd.setErrorCode(Error.errCodeBettingCount); //
			rd.setMessage(Error.errBettingCountInvalid); // return rd; // }

			if (betData.getBetIssueNo().isEmpty()) {
				rd.setErrorCode(Error.errCodeBettingIssuseNo);
				rd.setMessage(Error.errBettingIssuseNo);
				return rd;
			}

			if (betData.getPlayModeMoneyType().isEmpty()) {
				rd.setErrorCode(Error.errCodeBettingModel);
				rd.setMessage(Error.errBettingModel);
				return rd;
			}

			if (betData.getBetAmount().compareTo(new BigDecimal(0)) <= 0) {
				rd.setErrorCode(Error.errCodeBettingMoney);
				rd.setMessage(Error.errBettingMoney);
				return rd;
			}

			// 订单编号应该在此处生成
			if (betData.getOrderNum() != null && betData.getOrderNum().isEmpty()) {
				rd.setErrorCode(Error.errCodeBettingNumber);
				rd.setMessage(Error.errBettingNumber);
				return rd;
			}

			// if (betData.getBettingPoint().compareTo(new BigDecimal(0)) < 0) { //
			rd.setErrorCode(Error.errCodeBettingNumber); //
			rd.setMessage(Error.errBettingNumber); // return rd; // }

			if (betData.getLotteryCode().isEmpty()) {
				rd.setErrorCode(Error.errCodeBettingNumber);
				rd.setMessage(Error.errBettingNumber);
				return rd;
			}

			if (betData.getBetDetail() != null && betData.getBetDetail().isEmpty()) {
				rd.setErrorCode(Error.errCodeBettingNumber);
				rd.setMessage(Error.errBettingNumber);
				return rd;
			}

			/*
			 * LotteryOrder bet1 = new LotteryOrder(); User user = new User();
			 * bet1.setUser(user); String lotteryCode = "CQSSC";
			 * bet1.setLotteryCode(lotteryCode); BigDecimal betAmount = new BigDecimal(100);
			 * bet1.setBetAmount(betAmount); String betIssueNo = "20171117";
			 * bet1.setBetIssueNo(betIssueNo); String betType = "";
			 * bet1.setBetType(betType); User currentUser = null;
			 * bet1.setCurrentUser(currentUser); String id = ""; bet1.setId(id); String
			 * orderSource = "0"; bet1.setOrderSource(orderSource); String orderType = "1";
			 * bet1.setOrderType(orderType); String playModeCommissionRate = "0";
			 * bet1.setPlayModeCommissionRate(playModeCommissionRate); String playModeMoney
			 * = "1960"; bet1.setPlayModeMoney(playModeMoney); String playModeMoneyType =
			 * "0"; bet1.setPlayModeMoneyType(playModeMoneyType);
			 */

			// 生成订单
			myOrder.insert(betData);

			// 会员账户扣款
			String thisAccountId = betData.getId();
			myAccountCharge.AccountChargeAmount(thisAccountId, betData.getBetAmount());
		}
		
		return rd;
	}

	@Override
	public ResultData openToday(String lotteryName, Integer num) {
		// TODO Auto-generated method stub
		return myOpenToday.openToday( lotteryName,  num);
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
	public ResultData getOrders( String userId, String lotteryName, int num) {
		// TODO Auto-generated method stub
		return myOpenToday.getOrders( userId, lotteryName, num);
	}

}
