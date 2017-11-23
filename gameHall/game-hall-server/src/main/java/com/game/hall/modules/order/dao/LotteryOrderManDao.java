package com.game.hall.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.game.common.persistence.annotation.MyBatisDao;
import com.game.hall.modules.order.entity.DrawingRecord;
import com.game.hall.modules.order.entity.RechargeRecord;

@MyBatisDao
public interface LotteryOrderManDao {
	public List<Map<String,Object>> allLotteryOrder(String id);
	
	//根据用户id user_id查询该用户账变明细
	public List<Map<String, Object>> financeTradeDetail(Map<String, Object> map);
	//根据用户id user_id查询该用户所有充值记录
	public List<RechargeRecord> rechargeRecord(Map<String, Object> map);
	//根据用户id user_id查询该用户所有提现记录
	public List<DrawingRecord> drawingRecord(Map<String, Object> map);
	//根据用户id user_id查询该用户所有转账记录
	public List<DrawingRecord> transferRecord(Map<String, Object> map);
	
	
}
