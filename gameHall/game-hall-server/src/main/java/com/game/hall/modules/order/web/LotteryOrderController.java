package com.game.hall.modules.order.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.ResultData;
import com.game.hall.modules.order.entity.DrawingRecord;
import com.game.hall.modules.order.entity.RechargeRecord;
import com.game.hall.modules.order.service.LotteryOrderServiceDavide;


@Controller
@RequestMapping("/lotteryOrder")
public class LotteryOrderController {

	@Autowired
	private LotteryOrderServiceDavide lotteryOrderService;
	
	
	/**
	 * 根据user_id查看该用户所有投注记录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/allLotteryOrder", method = RequestMethod.GET)
	public ResultData allLotteryOrder(String id,String orderNo,Date startTime,Date endTime) {
		List<Map<String,Object>> list = lotteryOrderService.allLotteryOrder(id);
		return ResultData.ok(list);
	}
	
	
	/**
	 * 根据user_id查看该用户账户明细
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/financeTradeDetail", method = RequestMethod.GET)
	public ResultData financeTradeDetail(String userId,String accountId,Date startTime,Date endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		//传入开始时间和结束时间
		//格式Date类型
		//开始时间
		map.put("startTime", "2000-00-00 00:00:00");
		//结束时间
		map.put("endTime", new Date());
		map.put("userId",userId);
		map.put("accountId",accountId);
		
		List<Map<String,Object>> list = lotteryOrderService.financeTradeDetail(map);
		return ResultData.ok(list);
		
	}

	
	/**
	 * 根据user_id查看该用户充值记录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/rechargeRecord", method = RequestMethod.GET)
	public ResultData rechargeRecord(String userId,String status,String serialNumber,String rechargeMode,Date startTime,Date endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		//传入开始时间和结束时间
		//格式Date类型
		map.put("userId", userId);
		map.put("status", status);
		map.put("serialNumber", serialNumber);
		map.put("rechargeMode", rechargeMode);
		//开始时间
		map.put("startTime", "2000-00-00 00:00:00");
		//结束时间
		map.put("endTime", new Date());
		
		List<RechargeRecord> list = lotteryOrderService.rechargeRecord(map);
		
		return ResultData.ok(list);
	}
	
	/**
	 * 根据user_id查看该用户提现记录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/drawingRecord", method = RequestMethod.GET)
	public ResultData drawingRecord(String userId,String transactionNumber,Date startTime,Date endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		//传入开始时间和结束时间
		//格式Date类型
		map.put("userId", userId);
		map.put("transactionNumber", transactionNumber);
		//开始时间
		map.put("startTime", "2000-00-00 00:00:00");
		//结束时间
		map.put("endTime", new Date());
		
		List<DrawingRecord> list = lotteryOrderService.drawingRecord(map);
	
		return ResultData.ok(list);
	}
	
	
	/**
	 * 根据user_id查看该用户转账记录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/transferRecord", method = RequestMethod.GET)
	public ResultData transferRecord(String userId,String type,String transactionNumber,Date startTime,Date endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		//传入开始时间和结束时间
		//格式Date类型
		map.put("userId", userId);
		map.put("transactionNumber", transactionNumber);
		map.put("type", type);
		//开始时间
		map.put("startTime", "2000-00-00 00:00:00");
		//结束时间
		map.put("endTime", new Date());
		
		List<DrawingRecord> list = lotteryOrderService.transferRecord(map);
		return ResultData.ok(list);
	}
	

}

