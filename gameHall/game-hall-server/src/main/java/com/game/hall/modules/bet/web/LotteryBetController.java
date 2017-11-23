/**
 * 
 */
package com.game.hall.modules.bet.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ResultData;
import com.game.hall.modules.bet.service.LotteryAddBetService;
import com.game.hall.modules.sys.utils.UserUtils;
import com.game.modules.order.entity.LotteryOrder;
import com.game.modules.sys.entity.User;

/**
 * 投注
 * 
 * @author antonio
 */
@Controller
@RequestMapping("/bet/bet")
public class LotteryBetController {

	// @Autowired
	// private LotteryOpenTodayService lotteryTimeNumService;

	@Autowired
	private LotteryAddBetService lotteryAddBetService;

	@ResponseBody
	@RequestMapping(value = "/addbet", method = RequestMethod.GET)
	public ResultData addBet(List<LotteryOrder> betData) {

		// List<LotteryOrder> betData = null;
		System.out.println("1");

		try {
			// 前置校验
			boolean ret = false;
			ResultData rd = ResultData.ResultDataOK();

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("2");

		// BetData betData;

		return lotteryAddBetService.addBet(betData);
		// System.out.println();

	}

	
	@ResponseBody
	@RequestMapping(value = "/cancelorder", method = RequestMethod.POST)
	public ResultData cancelOrder(@RequestParam("orderIds")List<String> orderIds) {

		// List<LotteryOrder> betData = null;
		System.out.println("1");

		try {
			// 前置校验
			boolean ret = false;
			ResultData rd = ResultData.ResultDataOK();

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("2");
		
		// BetData betData;
		User user = UserUtils.getUser();
		user.preInsert();
		return lotteryAddBetService.cancelOrder(user,orderIds);
		// System.out.println();

	}

	// @ResponseBody
	// @RequestMapping(value = "/getopentoday", method = RequestMethod.GET)
	// public List<LotteryTimeNum> getOpenToday(String name) {
	//
	// ResultData rd = lotteryTimeNumService.OpenToday();
	// System.out.println();
	//
	// return null;
	// }
	//
	// @ResponseBody
	// @RequestMapping(value = "/getcur", method = RequestMethod.GET)
	// public List<LotteryTimeNum> getCur() {
	//
	// Date dt = new Date();
	//
	// List<LotteryTimeNum> openToday = lotteryTimeNumService.Cur(dt);
	//
	// return openToday;
	// }

}
