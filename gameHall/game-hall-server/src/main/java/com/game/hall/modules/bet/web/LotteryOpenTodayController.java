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

import com.entity.BetData;
import com.entity.ResultData;
import com.game.hall.modules.bet.entity.LotteryTimeNum;

import com.game.hall.modules.bet.service.LotteryAddBetService;
import com.game.hall.modules.bet.service.LotteryOpenTodayService;

/**
 * @author antonio 开奖信息
 */
@Controller
@RequestMapping("/bet/opentoday")
public class LotteryOpenTodayController {

//	@Autowired
//	private LotteryOpenTodayService lotteryTimeNumService;

	@Autowired
	private LotteryAddBetService lotteryAddBetService;

	@ResponseBody
	@RequestMapping(value = "/addbet", method = RequestMethod.GET)
	public ResultData addBet(BetData betData) {

		// BetData betData;
		ResultData rd = lotteryAddBetService.addBet(betData);
		System.out.println();

		return null;
	}

//	@ResponseBody
//	@RequestMapping(value = "/getopentoday", method = RequestMethod.GET)
//	public List<LotteryTimeNum> getOpenToday(String name) {
//
//		ResultData rd = lotteryTimeNumService.OpenToday();
//		System.out.println();
//
//		return null;
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "/getcur", method = RequestMethod.GET)
//	public List<LotteryTimeNum> getCur() {
//
//		Date dt = new Date();
//
//		List<LotteryTimeNum> openToday = lotteryTimeNumService.Cur(dt);
//
//		return openToday;
//	}

}
