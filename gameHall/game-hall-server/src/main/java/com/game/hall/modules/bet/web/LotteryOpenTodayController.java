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

import com.game.hall.modules.bet.entity.LotteryTimeNum;
import com.game.hall.modules.bet.service.LotteryOpenTodayService;

/**
 * @author antonio
 * 开奖信息
 */
@Controller
@RequestMapping("/bet/opentoday")
public class LotteryOpenTodayController {

	@Autowired
	private LotteryOpenTodayService lotteryTimeNumService;

	@ResponseBody
	@RequestMapping(value = "/getopentoday", method = RequestMethod.GET)
	public List<LotteryTimeNum> getOpenToday(String name) {

		List<LotteryTimeNum> openToday = lotteryTimeNumService.OpenToday();

		return openToday;
	}

	@ResponseBody
	@RequestMapping(value = "/getcur", method = RequestMethod.GET)
	public List<LotteryTimeNum> getCur() {

		Date dt = new Date();

		List<LotteryTimeNum> openToday = lotteryTimeNumService.Cur(dt);

		return openToday;
	}

}
