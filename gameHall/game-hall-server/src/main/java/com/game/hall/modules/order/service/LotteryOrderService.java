package com.game.hall.modules.order.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.AccountMgrApi;

import com.entity.ResultData;
import com.game.hall.modules.member.dao.PersonalDataDao;
import com.game.hall.modules.member.entity.MemberAccount;
import com.game.hall.modules.memberAccountCard.entity.MemberAccountCard;
import com.game.hall.modules.order.dao.LotteryOrderManDao;
import com.game.hall.modules.order.entity.DrawingRecord;
import com.game.hall.modules.order.entity.LotteryOrder;
import com.game.hall.modules.order.entity.RechargeRecord;


@Service
public class LotteryOrderService {

	@Autowired
	private LotteryOrderManDao lotteryOrderManDao;
	
	public List<Map<String,Object>> allLotteryOrder(String id) {
		return lotteryOrderManDao.allLotteryOrder(id);
	}

	public List<Map<String, Object>> financeTradeDetail(String userId) {
		return lotteryOrderManDao.financeTradeDetail(userId);
	}

	public List<RechargeRecord> rechargeRecord(String userId) {
		return lotteryOrderManDao.rechargeRecord(userId);
	}

	public List<DrawingRecord> drawingRecord(String userId) {
		
		return lotteryOrderManDao.drawingRecord(userId);
	}




}
