/**
 * 
 */
package com.game.hall.modules.bet.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.BetServiceApi;
import com.entity.Error;
import com.entity.ResultData;
import com.game.hall.common.utils.SpringContextHolder;
import com.game.hall.modules.bet.dao.AccountChargeDao;
import com.game.hall.modules.bet.dao.LotteryOrderDao;
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
	// @Autowired // @Qualifier("myServiceClient") //
	// @Resource(name = "myServiceClient")
	// private BetServiceApi myServiceClient;

	@Autowired
	private LotteryOrderDao myOrder;

	@Autowired
	AccountChargeDao myAccountCharge;

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
	//	System.out.println(myServiceClient.test1());

		return null;
	}

	@Transactional(readOnly = false)
	@Override
	public ResultData addBet(List<LotteryOrder> lsBetData) { // TODO

		// myServiceClient.addBet(betData);

		ResultData rd = ResultData.ResultDataOK();

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

			if (betData.getOrderNum().isEmpty()) {
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

			if (betData.getBetDetail().isEmpty()) {
				rd.setErrorCode(Error.errCodeBettingNumber);
				rd.setMessage(Error.errBettingNumber);
				return rd;
			}

			// 生成订单
			myOrder.insert(betData);

			// 会员账户扣款
			// myAccountCharge.AccountChargeAmount(betData.getId(), betData.getBetAmount());
		}
		return null;
	}

	@Override
	public ResultData openToday(String lotteryName, Integer num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData curOpen(String lotteryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData lotteryPlayConfig(String lotteryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData lotteryPlayConfig(String lotteryName, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
