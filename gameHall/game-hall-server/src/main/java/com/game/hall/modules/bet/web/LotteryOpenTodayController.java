/**
 * 
 */
package com.game.hall.modules.bet.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ResultData;
import com.game.hall.modules.bet.service.LotteryAddBetService;
import com.game.hall.modules.sys.utils.UserUtils;
import com.game.modules.sys.entity.User;


/**
 * @author antonio 开奖信息
 */
@Controller
@RequestMapping("/bet/opentoday")
public class LotteryOpenTodayController {

	// @Autowired
	// private LotteryOpenTodayService lotteryTimeNumService;

	@Autowired
	private LotteryAddBetService lotteryAddBetService;

	/**
	 * 历史开奖
	 * @param lotteryName
	 * @param num
	 * @return
	 */
	 @ResponseBody
	 @RequestMapping(value = "/getopentoday", method = RequestMethod.GET)
	 public ResultData getOpenToday(String lotteryName, int num) {
	
	// ResultData rd = lotteryAddBetService.openToday(lotteryName, num);
	
	 return lotteryAddBetService.openToday(lotteryName, num);
	 }
	
	 /**
	  * 当前开奖
	  * @param lotteryName
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value = "/getcur", method = RequestMethod.GET)
	 public ResultData getCur(String lotteryName) {
	
	 Date dt = new Date();
	
	 ResultData openToday = lotteryAddBetService.curOpen(lotteryName);
	
	 return openToday;
	 }
	 
	 /**
	  * 玩法
	  * @param lotteryName
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value = "/getplayconfig", method = RequestMethod.GET)
	 public ResultData getPlayConfig(String lotteryName) {
	
	 Date dt = new Date();
	
	 ResultData openToday = lotteryAddBetService.getPlayConfig(lotteryName);
	
	 return openToday;
	 }
	 
	 /**
	  * 我的方案
	  * @param lotteryName
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value = "/getorders", method = RequestMethod.GET)
	 public ResultData getOrderList(String lotteryName,int num) {
	
	 Date dt = new Date();
	 User user = UserUtils.getUser();
	 
	 String userId = user.getId();
	 userId = "610494eafa624f30997eb0248f431e46";
	 ResultData openToday = lotteryAddBetService.getOrders(userId, lotteryName, num);
			 
	 return openToday;
	 }
	 

}
