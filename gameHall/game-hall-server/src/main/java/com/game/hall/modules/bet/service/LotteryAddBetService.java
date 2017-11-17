/**
 * 
 */
package com.game.hall.modules.bet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.api.BetServiceApi;
import com.entity.BetData;
import com.entity.ResultData;
import com.game.hall.common.utils.SpringContextHolder;

/**
 * @author antonio
 *
 */
@Service
public class LotteryAddBetService {
	@Autowired // @Qualifier("myServiceClient") //
	// @Resource(name = "myServiceClient")
	private BetServiceApi myServiceClient;

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

	public ResultData addBet(BetData betData) {
		// TODO Auto-generated method stub
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("remoting-client.xml");
		// BetServiceApi helloService = (BetServiceApi)
		// context.getBean("myServiceClient");
//		BetServiceApi helloService = getBetServiceApi();
//		System.out.println("helloService.test1()=");
//		System.out.println(helloService.test1());

		 System.out.println("myServiceClient.test1()");
		 System.out.println(myServiceClient.test1());

		return null;
	}

	public ResultData openToday(String lotteryName, Integer num) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultData curOpen(String lotteryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultData lotteryPlayConfig(String lotteryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultData lotteryPlayConfig(String lotteryName, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
