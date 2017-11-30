package com.game.hall.modules.bet.dao;

import org.apache.ibatis.annotations.Param;

import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.finance.entity.FinanceTradeDetail;
import com.game.modules.member.entity.MemberAccount;
import com.game.modules.order.entity.LotteryOrder;

@MyBatisDao
public interface LotteryOrderManagerDao {
	public int testInOrder(@Param("order")LotteryOrder order, @Param("account")MemberAccount account, @Param("trade")FinanceTradeDetail trade);
}
