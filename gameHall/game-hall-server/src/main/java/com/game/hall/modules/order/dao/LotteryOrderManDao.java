package com.game.hall.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.game.hall.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.order.entity.DrawingRecord;
import com.game.hall.modules.order.entity.LotteryOrder;
import com.game.hall.modules.order.entity.RechargeRecord;

@MyBatisDao
public interface LotteryOrderManDao {
	public List<Map<String,Object>> allLotteryOrder(String id);
	
	//根据用户id user_id查询该用户账变明细
	public List<Map<String, Object>> financeTradeDetail(String userId);
	//根据用户id user_id查询该用户所有充值记录
	public List<RechargeRecord> rechargeRecord(String userId);
	//根据用户id user_id查询该用户所有提现记录
	public List<DrawingRecord> drawingRecord(String userId);
	
	
}
