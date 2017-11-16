/**
 * 
 */
package com.game.hall.modules.bet.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.hall.modules.bet.entity.BetData;
import com.game.hall.modules.bet.entity.LotteryTimeNum;
import com.game.hall.modules.bet.entity.ResultData;
import com.game.hall.modules.bet.service.LotteryAddBetService;
import com.game.hall.modules.bet.service.LotteryOpenTodayService;

/**
 * @author antonio 开奖信息
 */
@Controller
@RequestMapping("/bet/opentoday")
public class LotteryOpenTodayController {

	@Autowired
	private LotteryOpenTodayService lotteryTimeNumService;

	@Autowired
	private LotteryAddBetService lotteryAddBetService;

	@ResponseBody
	@RequestMapping(value = "/betData", method = RequestMethod.GET)
	public ResultData addBet(BetData betData) {

		// BetData betData;
		ResultData rd = lotteryAddBetService.bet(betData);
		System.out.println();

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/getopentoday", method = RequestMethod.GET)
	public List<LotteryTimeNum> getOpenToday(String name) {

		// List<LotteryTimeNum> openToday = lotteryTimeNumService.OpenToday();

		// String urlName = "http://localhost:8081/Hes";
		//
		// HessianProxyFactory factory = new HessianProxyFactory();
		// // 开启方法重载
		// factory.setOverloadEnabled(true);
		//
		// HessianApi hessionApi;
		// try {
		// hessionApi = (HessianApi) factory.create(HessianApi.class, urlName);
		// // 调用方法
		// System.out.println("call sayHello():" + hessionApi.test1());
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// URL upath = this.getClass().getResource("/");

		// System.out.println(upath);

		// ApplicationContext context = new
		// ClassPathXmlApplicationContext(upath+"..\\remoting-client.xml");
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("remoting-client.xml");

		// HessianApi hello = (HessianApi) context.getBean("myServiceClient");

		// BetData detData;
		ResultData rd = lotteryTimeNumService.OpenToday();
		System.out.println();

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/getcur", method = RequestMethod.GET)
	public List<LotteryTimeNum> getCur() {

		Date dt = new Date();

		List<LotteryTimeNum> openToday = lotteryTimeNumService.Cur(dt);

		return openToday;
	}

}
