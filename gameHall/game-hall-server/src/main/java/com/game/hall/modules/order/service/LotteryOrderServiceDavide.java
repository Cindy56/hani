package com.game.hall.modules.order.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.hall.modules.order.dao.LotteryOrderManDao;
import com.game.hall.modules.order.entity.DrawingRecord;
import com.game.hall.modules.order.entity.RechargeRecord;


@Service
public class LotteryOrderServiceDavide {

	@Autowired
	private LotteryOrderManDao lotteryOrderManDao;
	
	public List<Map<String,Object>> allLotteryOrder(String id) {
		return lotteryOrderManDao.allLotteryOrder(id);
	}

	public List<Map<String, Object>> financeTradeDetail(Map<String, Object> map) {
		return lotteryOrderManDao.financeTradeDetail(map);
	}

	public List<RechargeRecord> rechargeRecord(Map<String, Object> map) {
		return lotteryOrderManDao.rechargeRecord(map);
	}

	public List<DrawingRecord> drawingRecord(Map<String, Object> map) {
		
		return lotteryOrderManDao.drawingRecord(map);
	}

	public List<DrawingRecord> transferRecord(Map<String, Object> map) {
		return lotteryOrderManDao.transferRecord(map);
	}




}
